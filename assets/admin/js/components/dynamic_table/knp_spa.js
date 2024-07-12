function componentsDynamicTableKnpSpa() {
    const knpTableContainer = document.querySelector('#dynamic-knp-table');
    if (!knpTableContainer) {
        return;
    }

    const columnNameTranslations = {};
    for (const elem of knpTableContainer.querySelectorAll('.sortable')) {
        const propertyName = elem.dataset.propertyName;
        const translation = elem.title;

        columnNameTranslations[propertyName] = translation;
    }

    function linkSearchInput() {
        let knpSearchInput = document.getElementById('data-table-search');
        if (!knpSearchInput) {
            return;
        }

        let isInput = false;
        let inputTimeout = null;

        knpSearchInput.addEventListener('input', (e) => {
            if (isInput) {
                clearTimeout(inputTimeout);
            }
            isInput = true;

            inputTimeout = setTimeout(async () => {
                document.getElementById('search').value = knpSearchInput.value;

                isSearching = false;  // disabling flag to ensure that the last request will be executed
                await handleDataTableLiveSearch(e);
                isInput = false;
            }, 150);
        });

        knpSearchInput.addEventListener('keydown', (e) => {
            if (e.key === 'Enter') {
                document.querySelector('#filters-form').submit();
            }

            const wasArrowDownPressed = e.key === 'ArrowDown';
            if (wasArrowDownPressed) {
                document.querySelector('.auto-complete-result')?.focus();

                return;
            }

            const wasEnterPressed = e.key === 'Enter';
            if (!wasEnterPressed) {
                return;
            }

            knpSearchInput.blur(true);
        })
    }

    function spaPagination() {
        let links = document.querySelectorAll('.knp-pagination a.page-link');

        links.forEach((link) => {
            link.addEventListener('click', (e) => {
                e.preventDefault();

                const url = new URL(link.getAttribute('href'), window.location.origin);
                if (!url.pathname.endsWith('/page')) {
                    url.pathname += `/page`;
                }

                let fade = document.querySelector('#dynamic-knp-table .tear');
                fade.classList.remove('d-none');

                fetch(url.toString())
                    .then(data => data.text())
                    .then(data => {
                        fade.classList.add('d-none');
                        knpTableContainer.innerHTML = data;
                        spaPagination();
                    });

                window.history.pushState(null, '', link.getAttribute('href').replace('/page', ''));
            });
        });
    }

    let isSearching = false;
    let isFocus = false;

    function handleBlurSearchBox() {
        const results = document.querySelector('#data-table-search-results-container');
        if (!results) {
            return;
        }

        checkFocus();

        setTimeout(() => {
            if (isFocus) {
                return;
            }

            results.setAttribute('hidden', 'hidden');
        }, 0);
    }

    function checkFocus() {
        setTimeout(() => {
            if (!document.activeElement.closest('.search-box-container')) {
                isFocus = false;
            }
        }, 0);
    }

    async function handleDataTableLiveSearch(event) {
        if (isSearching) {
            return;
        }

        const resultsContainer = document.querySelector('#data-table-search-results-container');
        const results = resultsContainer?.querySelector('#data-table-search-results');
        const searchBox = document.querySelector('#data-table-search');
        if (!resultsContainer) {
            return;
        }

        isSearching = true;
        results.innerHTML = '';

        const target = event.target;
        const inputValue = target?.value;

        // we don't want showing hits or searching for hints if there doesn't exist provided value
        if (!inputValue) {
            resultsContainer.setAttribute('hidden', 'hidden');
            isSearching = false;

            return;
        }

        /**
         * Endpoint for autocomplete is different from endpoint for searching data.
         * We need to pass all filter params to endpoint for autocomplete.
         * Also, we need to pass a search value to searching autocomplete hints.
         */
        const elemAutocompleteUrl = knpTableContainer.getAttribute('data-automplete-url');
        if (!elemAutocompleteUrl) {
            return;
        }

        const autoCompleteBaseUrl = new URL(elemAutocompleteUrl, window.location.origin);
        const autoCompleteSearchParams = new URLSearchParams(autoCompleteBaseUrl.search);

        const endpointUrlWithFilters = new URL(window.location.href, window.location.origin);
        const endpointSearchParams = new URLSearchParams(endpointUrlWithFilters.search);

        for (const [key, value] of endpointSearchParams.entries()) {
            autoCompleteSearchParams.set(key, value);
        }
        autoCompleteSearchParams.set('search', inputValue);
        autoCompleteBaseUrl.search = autoCompleteSearchParams.toString();

        const urlToSearch = autoCompleteBaseUrl.toString();
        const response = await fetch(urlToSearch, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        const data = await response.json();

        // update info about displaying results
        resultsContainer.querySelector('#current-results-count').textContent = data.items.length;
        resultsContainer.querySelector('#total-results-count').textContent = data.totalCount;

        // ensure that results container is empty
        results.innerHTML = '';

        for (const item of data.items) {
            const value = item.value;

            // define matching searched text into result from response
            const selectedFrom = value.toLowerCase().indexOf(inputValue.toLowerCase());
            const selectedTo = inputValue.length + selectedFrom;
            const nonMatchedFirst = value.substring(0, selectedFrom);
            const matched = value.substring(value.indexOf(inputValue), selectedTo);
            const nonMatchedLast = value.substring(selectedTo);
            const detectedMatch = `${nonMatchedFirst}<strong class="text-info">${matched}</strong>${nonMatchedLast}`;

            // adds hint to results container
            results.innerHTML += `
                <p>
                    <button 
                        type="button" 
                        class="auto-complete-result btn text-start w-100 h-100 p-2" 
                        data-hint="${item.value}"
                    >
                        <span class="badge text-bg-secondary">
                            ${columnNameTranslations[item.propertyName]}
                        </span>
                        <span>
                            ${detectedMatch}
                        </span>
                    </button>
                </p>
            `.trim();
        }

        const autoCompleteResults = results.querySelectorAll('.auto-complete-result');
        for (const [index, elem] of autoCompleteResults.entries()) {
            elem.addEventListener('focus', () => {
                isFocus = true;
            });
            elem.addEventListener('blur', handleBlurSearchBox);

            // handling keydown for accessibility
            elem.addEventListener('keydown', (event) => {
                const key = event.key;
                const wasPressedArrowUp = key === 'ArrowUp';
                const wasPressedArrowDown = key === 'ArrowDown';

                if (wasPressedArrowUp) {
                    if (index === 0) {
                        searchBox.focus();
                    }
                    if (index > 0) {
                        autoCompleteResults[index - 1].focus();
                    }
                }
                if (wasPressedArrowDown) {
                    if (index < autoCompleteResults.length - 1) {
                        autoCompleteResults[index + 1].focus();
                    }
                }
            });

            // hint has been selected, so we want to filter data table
            elem.addEventListener('click', () => {
                isFocus = false;  // changing flag to hide element with results
                elem.blur();  // force blur to ensure that we will lost focus on focused element
                target.value = elem.dataset.hint;  // applying actual hint to search input
                results.innerHTML = '';  // clearing search results after selecting searched value

                document.getElementById('search').value = elem.dataset.hint;
                document.querySelector('#filters-form').submit();
            });
        }

        resultsContainer.removeAttribute('hidden');
        isSearching = false;
    }

    linkSearchInput();
    spaPagination();
}

componentsDynamicTableKnpSpa();