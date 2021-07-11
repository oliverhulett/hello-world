#!/usr/bin/env bash

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
source "${HERE}/common.sh"

"${APPLICATION_COMPOSE_CMD[@]}" logs --tail 5 --follow --timestamps
