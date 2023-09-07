#!/usr/bin/env sh

err_exit() {
    echo "$@" >&2
    exit 1
}

cd "$(dirname "$0")" || err_exit "Failed to cd into $(dirname "$0")"

[ -z $1 ] && {
  read -p "Enter module name: " name
}

name="$1"
mkdir -p "$name/src/main/java/com/jeff_media/cesspool" || err_exit "Failed to create directory $name/src/main/java/com/jeff_media/cesspool"

cat <<EOF>> settings.gradle.kts

include("$name")
EOF

cat <<EOF>> "${name}/build.gradle.kts"
plugins {
    id("cesspool-lib-module")
}

dependencies {
    api(project(":shared"))
}
EOF
