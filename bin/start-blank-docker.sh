#!/usr/bin/env bash

HERE="$(cd "$(dirname "$(readlink -f "${BASH_SOURCE[0]}" 2>/dev/null || greadlink -f "${BASH_SOURCE[0]}" 2>/dev/null)")" && pwd -P)"
PROJECT_DIR="$(dirname "${HERE}")"
source "${PROJECT_DIR}/bin/common.sh"

if [ $# -gt 2 ]; then
	report_bad "$(basename -- "$0") expects docker image name and version/tag only"
	report_bad "$(basename -- "$0") [docker-image] [version/tag]"
	exit 127
fi

if [ "$1" == "--help" ] || [ "$1" == "-h" ] || [ "$1" == "-?" ]; then
	report_good "$(basename -- "$0") [docker-image] [version/tag]"
	report_good "Start a blank docker container of the given image and version/tag.  Create the 'cap' datastore."
	report_good "Defaults to ${DOCKER_POSTGRES}:${DOCKER_POSTGRES_VERSION}"
	exit 0
fi

if [ $# -ge 1 ]; then
	DOCKER_POSTGRES="$1"
fi
if [ $# -ge 2 ]; then
	DOCKER_POSTGRES_VERSION="$2"
fi

docker run -d --rm --name="${DOCKER_NAME}" -p "5432:${DOCKER_PORT}" "${DOCKER_POSTGRES}:${DOCKER_POSTGRES_VERSION}"
docker exec "${DOCKER_NAME}" psql -h localhost -p 5432 -U postgres -c "CREATE DATABASE ${CAP_DATABASE_NAME}"
