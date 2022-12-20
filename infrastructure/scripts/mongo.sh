#!/usr/bin/env sh
# shellcheck disable=SC2160
while true ; do
   curl --request GET -sL \
        --url 'http://localhost:7003/audits'
done