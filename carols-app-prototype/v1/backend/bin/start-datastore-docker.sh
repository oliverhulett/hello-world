#!/usr/bin/env bash

HERE="$(cd "$(dirname "$(readlink -f "${BASH_SOURCE[0]}" 2>/dev/null || greadlink -f "${BASH_SOURCE[0]}" 2>/dev/null)")" && pwd -P)"
PROJECT_DIR="$(dirname "${HERE}")"
source "${PROJECT_DIR}/bin/common.sh"

if [ $# -gt 1 ]; then
	report_bad "$(basename -- "$0") expects docker version/tag only"
	report_bad "$(basename -- "$0") [version/tag]"
	exit 127
fi

if [ "$1" == "--help" ] || [ "$1" == "-h" ] || [ "$1" == "-?" ]; then
	report_good "$(basename -- "$0") [version/tag]"
	report_good "Start the latest cap datastore docker container of version/tag.  Create the 'cap' datastore."
	report_good "Defaults to ${DATASTORE_IMAGE_VERSION}"
	exit 0
fi

if [ $# -ge 1 ]; then
	DATASTORE_IMAGE_VERSION="$1"
fi

report_cmd "${HERE}/stop-datastore-docker.sh"
report_cmd docker run -d --rm --name="${DATASTORE_NAME}" -p "5432:${DATASTORE_PORT}" "${DATASTORE_IMAGE}:${DATASTORE_IMAGE_VERSION}"
