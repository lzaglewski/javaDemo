document.querySelectorAll('.notification').forEach((elem) => {
    const content = {
        message: elem.dataset.message || '',
        title: elem.dataset.title || '',
        icon: 'fa-solid fa-bell',
        url: elem.dataset.url || '',
        target: elem.dataset.target || '_self',
    };

    $.notify(content, {
        type: elem.dataset.type || 'info',
        placement: {
            from: elem.dataset.placementFrom || 'top',
            align: elem.dataset.placementAlign || 'center',
        },
        time: elem.dataset.time,
        delay: 0,
    });
});