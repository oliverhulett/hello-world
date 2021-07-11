# shellcheck shell=bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
PROJECT_DIR="$(dirname "${HERE}")"

export APPLICATION_IMAGE="cap-backend"
export APPLICATION_VERSION="0.1.0"
export APPLICATION_NAME="cap-backend"

export DATASTORE_COMPOSE_FILE="${PROJECT_DIR}/docker/docker-compose.datastore.yml"
export DATASTORE_COMPOSE_PROJECT="cap-datastore"
export DATASTORE_COMPOSE_CMD=( "${HERE}/docker-compose.sh" "-f" "${DATASTORE_COMPOSE_FILE}" "-p" "${DATASTORE_COMPOSE_PROJECT}" )

export APPLICATION_COMPOSE_FILE="${PROJECT_DIR}/docker/docker-compose.application.yml"
export APPLICATION_COMPOSE_PROJECT="cap-backend"
export APPLICATION_COMPOSE_CMD=( "${HERE}/docker-compose.sh" "-f" "${APPLICATION_COMPOSE_FILE}" "-f" "${DATASTORE_COMPOSE_FILE}" "-p" "${APPLICATION_COMPOSE_PROJECT}" )

RED="$(tput -T"${TERM:-dumb}" setaf 1 || true)"
GREEN="$(tput -T"${TERM:-dumb}" setaf 2 || true)"
CYAN="$(tput -T"${TERM:-dumb}" setaf 6 || true)"
WHITE="$(tput -T"${TERM:-dumb}" bold || true)$(tput -T"${TERM:-dumb}" setaf 7 || true)"
RESET="$(tput -T"${TERM:-dumb}" sgr0 || true)"

function report_neutral()
{
	echo -e "${CYAN}" "$*" "${RESET}"
}

function report_good()
{
	echo -e "${GREEN}" "$*" "${RESET}"
}

function report_bad()
{
	echo -e "${RED}" "$*" "${RESET}"
}

function report_cmd()
{
	echo -e "${WHITE}""\$ $*""${RESET}"
	"$@"
}

function die()
{
	report_bad "$*"
	exit 1
}

if ! command readlink -f "${BASH_SOURCE[0]}" >/dev/null 2>/dev/null && [ ! -e /usr/local/bin/greadlink ]; then
	die "You're on a Mac that doesn't have coreutils installed.  You're gonna have a bad time.  Try 'brew install coreutils'"
fi
