#!/usr/bin/env bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
source "${HERE}/common.sh"

"${APPLICATION_COMPOSE_CMD[@]}" ps
"${APPLICATION_COMPOSE_CMD[@]}" down --remove-orphans --volumes "$@"
"${APPLICATION_COMPOSE_CMD[@]}" rm --force --stop -v
"${APPLICATION_COMPOSE_CMD[@]}" ps
