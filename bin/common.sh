# shellcheck shell=bash

export CAP_DATABASE_NAME="cap"
export DOCKER_IMAGE="cap/datastore"
export DOCKER_IMAGE_VERSION="latest"
export DOCKER_NAME="cap_datastore"
export DOCKER_PORT="5432"
export DOCKER_POSTGRES="postgres"
export DOCKER_POSTGRES_VERSION="11"

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
