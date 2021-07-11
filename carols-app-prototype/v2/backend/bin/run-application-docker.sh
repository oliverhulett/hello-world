#!/usr/bin/env bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
source "${HERE}/common.sh"

function app_running() {
	dockerize -wait http://localhost:8080/actuator/health -timeout "$@"
}

"${APPLICATION_COMPOSE_CMD[@]}" up --remove-orphans --detach "$@"

CNT=3
while [ ${CNT} != 0 ] && ! app_running 5s; do
	CNT=$((CNT - 1))
done

if app_running 1s; then
	"${APPLICATION_COMPOSE_CMD[@]}" ps
	"${APPLICATION_COMPOSE_CMD[@]}" logs --tail 5 --timestamps
fi
