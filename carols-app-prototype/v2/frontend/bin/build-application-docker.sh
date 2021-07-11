#!/usr/bin/env bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
source "${HERE}/common.sh"

report_cmd cd "${PROJECT_DIR}" || die "Could not change into project directory: ${PROJECT_DIR}"
report_cmd docker build \
	--build-arg VERSION="${APPLICATION_VERSION}" \
	-t "${APPLICATION_IMAGE}:${APPLICATION_VERSION}" -t "${APPLICATION_IMAGE}:latest" \
	-f "${PROJECT_DIR}/docker/Dockerfile" .
