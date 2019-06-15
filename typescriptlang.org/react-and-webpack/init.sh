# shellcheck shell=bash
##
##	This file is intended to be sourced.  It will setup a development environment for this project.
##	This file is as idempotent as possible, so it should be able to be run multiple times without ill effect.  Subsequent runs should be very fast.
##	The install of taskfile (and any dependencies) is done here so it can be reused by any other tool that needs bootstrapping.
##

HERE="$(cd "$(dirname -- "${BASH_SOURCE[0]}")" && pwd -P)"

EXECUTABLE="${HERE}/node_modules/.bin/node"
DEPENDENCIES=(
	"node@^12.0.0"
	"tasksfile@^5.0.0"
)

RED="$(tput -T"${TERM:-dumb}" setaf 1 || true)"
GREEN="$(tput -T"${TERM:-dumb}" setaf 2 || true)"
WHITE="$(tput -T"${TERM:-dumb}" bold || true)$(tput -T"${TERM:-dumb}" setaf 7 || true)"
RESET="$(tput -T"${TERM:-dumb}" sgr0 || true)"
function _tell()
{
	echo "${WHITE} $* ${RESET}"
}
function _success()
{
	echo "${GREEN} $* ${RESET}"
}
function _error()
{
	echo "${RED} $* ${RESET}"
}
function _exit()
{
	code=$?
	shift
	_error "$@"
	exit $code
}

_CHANGED="false"

if [ ! -e "${EXECUTABLE}" ]; then
	_tell "Installing task runner..."
	_CHANGED="true"
	( cd "${HERE}" && npm install "${DEPENDENCIES[@]}" || _exit $? "Failed to install task runner..." )
fi

if [ "${_CHANGED}" == "true" ]; then
	# Don't advertise if we haven't changed anything, the dev env was already set up.
	_success "Development environment set up"
fi
