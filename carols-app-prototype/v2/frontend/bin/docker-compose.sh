#!/usr/bin/env bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
source "${HERE}/common.sh"

report_cmd docker-compose "$@"
