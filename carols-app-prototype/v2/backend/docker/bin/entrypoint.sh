#!/usr/bin/env bash
set -x

HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
ROOT_DIR="$(dirname "${HERE}")"
BIN_DIR="${ROOT_DIR}/bin"
LIB_DIR="${ROOT_DIR}/lib"
RUN_DIR="${ROOT_DIR}/run"

APPLICATION_NAME="${APPLICATION_NAME:-application}"
JAR="${LIB_DIR}/service.jar"

NOW="$(date '+%Y%m%d-%H%M%S')"

function mkfilename() {
	echo "${RUN_DIR}/${NOW}.${APPLICATION_NAME}.$*"
}

function runcmd() {
	echo "$*"
	"$@"
}

runcmd mkdir --parents "${BIN_DIR}" "${LIB_DIR}" "${RUN_DIR}"

VERBOSE_STARTUP=( "-XX:+PrintCommandLineFlags" "-XshowSettings" "-showversion" )

JMX_OPTS=(
	"-Dcom.sun.management.jmxremote.port=9010"
	"-Dcom.sun.management.jmxremote.rmi.port=9010"
	"-Dcom.sun.management.jmxremote.authenticate=false"
	"-Dcom.sun.management.jmxremote.ssl=false"
	"-Dcom.sun.management.jmxremote.local.only=false"
)

OOM_HEAP_DUMPS=(
	"-XX:+HeapDumpOnOutOfMemoryError"
	"-XX:HeapDumpPath=$(mkfilename oom-heap-dump).pid-%p.hprof"
)

OOM_KILL=( "-XX:OnOutOfMemoryError='jstack %p'" )
FATAL_ERROR_DEST=( "-XX:ErrorFile=$(mkfilename hs_err_pid).%p.log" )

# === Defaults ================================
# No defaults set for -Xms -Xmx -Xss: use MEMORY_OPTS
# No defaults for GC settings (yet?): use GC_OPTS
# No other defaults: use JAVA_OPTS

DEFAULT_JVM_OPTS=(
	"-server"
	"-Xshare:off"
	"-Dsun.net.inetaddr.ttl=${DNS_CACHE_TTL-1}"
	"-Dsun.net.inetaddr.negative.ttl=${DNS_CACHE_TTL-1}"
	"-Dfile.encoding=UTF-8"
	"-XX:+PreserveFramePointer"
)

mapfile MEMORY_OPTS < <(echo -n "${MEMORY_OPTS}")
mapfile GC_OPTS < <(echo -n "${GC_OPTS}")
mapfile JAVA_OPTS < <(echo -n "${JAVA_OPTS}")

runcmd java -version 2>&1

# === Launch Java ================================
trap ':' QUIT HUP INT TERM ABRT # Let these signals go to the JVM instead of killing us instead

cd "${RUN_DIR}" || exit 1

# === Support restarting java process via restart.sh ========
runcmd rm -vf "${RUN_DIR}/.restart_java"

# === determine if its a jar file or mainfile, and launch java ====
runcmd ls -l "$JAR"
runcmd java \
	"${DEFAULT_JVM_OPTS[@]}" \
	"${JMX_OPTS[@]}" \
	"${VERBOSE_STARTUP[@]}" \
	"${OOM_HEAP_DUMPS[@]}" \
	"${OOM_KILL[@]}" \
	"${FATAL_ERROR_DEST[@]}" \
	"${NEWRELIC[@]}" \
	"${GC_OPTS[@]}" \
	"${MEMORY_OPTS[@]}" \
	"${JAVA_OPTS[@]}" \
	-jar "$JAR" "$@"
JVM_EXIT_CODE=$?
echo

cd - >/dev/null || true

trap - TERM HUP INT QUIT ABRT

# === Restart as needed ================================
if [ -e "${RUN_DIR}/.restart_java" ]; then
	# Start entrypoint.sh all over to make sure dynamic vars are re-evaluated
	echo "Java process exited, but .restart_java exists.  Re-execing $0"
	exec "$0" "$@"
fi

echo "Java process exited (exit code: ${JVM_EXIT_CODE}) and no .restart_java file exists. Exiting entrypoint.sh. (Docker may respawn another container.)"
