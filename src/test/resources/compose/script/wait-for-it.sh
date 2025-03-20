#!/bin/bash
set -e

host="$1"
shift
cmd="$@"

until curl -s "$host" ; do
  >&2 echo "$host Server is unavailable - sleeping"
  sleep 2
done

>&2 echo "$host Server is up - executing command"
exec $cmd