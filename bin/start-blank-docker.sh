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
	report_good "Defaults to ${POSTGRES}:${POSTGRES_VERSION}"
	exit 0
fi

if [ $# -ge 1 ]; then
	POSTGRES="$1"
fi
if [ $# -ge 2 ]; then
	POSTGRES_VERSION="$2"
fi

report_cmd "${HERE}/stop-running-docker.sh" || true
( report_cmd cd "${PROJECT_DIR}/image" && report_cmd docker build --tag "${DATASTORE_IMAGE}:0" --build-arg POSTGRES="${POSTGRES}" --build-arg POSTGRES_VERSION="${POSTGRES_VERSION}" . )
report_cmd docker run -d --rm --name="${DATASTORE_NAME}" -p "5432:${DATASTORE_PORT}" "${DATASTORE_IMAGE}:0"
sleep 2
report_cmd docker exec "${DATASTORE_NAME}" psql -h localhost -p 5432 -U postgres -c "CREATE DATABASE ${CAP_DATABASE_NAME}"
