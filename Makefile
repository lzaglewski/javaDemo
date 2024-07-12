# Executables (local)
DOCKER_COMP = docker-compose

# Docker containers
ORACLE_CONT = $(DOCKER_COMP) exec oracle
NODE_CONT = $(DOCKER_COMP) exec node

# Misc
.DEFAULT_GOAL = help
.PHONY        = help build up start down logs sh composer vendor sf cc

## —— 🎵 🐳 The Symfony-docker Makefile 🐳 🎵 ——————————————————————————————————
help: ## Outputs this help screen
	@grep -E '(^[a-zA-Z0-9_-]+:.*?##.*$$)|(^##)' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}{printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}' | sed -e 's/\[32m##/[33m/'

## —— Docker 🐳 ————————————————————————————————————————————————————————————————
build: ## Builds the Docker images
	@$(DOCKER_COMP) build --pull --no-cache

up: ## Start the docker hub in detached mode (no logs)
	@$(DOCKER_COMP) up --detach

down: ## Stop the docker hub
	@$(DOCKER_COMP) down --remove-orphans

stop:
	@$(DOCKER_COMP) stop

logs: ## Show live logs
	@$(DOCKER_COMP) logs --tail=0 --follow

oracle: ## Connect to the PHP FPM container
	@$(ORACLE_CONT) bash

node: ## Connect to the PHP FPM container
	@$(NODE_CONT) bash

## —— Project ————————————————————————————————————————————————————————————————
webtool: ## Watching assets
	docker-compose exec node sh -c "\
    		npm run watch; \
    	"

webtool_build: ## Watching assets
	docker-compose exec node sh -c "\
    		npm run build; \
    	"
