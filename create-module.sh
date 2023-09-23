#!/usr/bin/env sh

err_exit() {
    echo "$@" >&2
    exit 1
}

cd "$(dirname "$0")" || err_exit "Failed to cd into $(dirname "$0")"

name="$1"

[ -z "$name" ] && {
  printf "Enter module name: "
  read -r name
}

dirName="modules/$name/src/main/java/com/jeff_media/cesspool"
mkdir -p "$dirName" || err_exit "Failed to create directory $dirName"

cat <<EOF>> "modules/${name}/build.gradle.kts"
plugins {
    id("cesspool-lib-module")
}
EOF