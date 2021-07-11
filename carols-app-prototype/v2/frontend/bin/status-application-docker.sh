#!/usr/bin/env bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
source "${HERE}/common.sh"

function app_running() {
	dockerize -wait http://localhost:8080/actuator/health -timeout "$@"
}

app_running 1s
EXIT_CODE=$?
if [ ${EXIT_CODE} -eq 0 ]; then
	"${APPLICATION_COMPOSE_CMD[@]}" ps
fi

echo
if [ ${EXIT_CODE} -eq 0 ]; then
	report_good "Running"
else
	report_bad "Not Running"
fi
exit ${EXIT_CODE}
