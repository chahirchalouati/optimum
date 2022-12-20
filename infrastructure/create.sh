#!/usr/bin/env sh
dirs=$(ls -d */)
for i in ${dirs[@]}; do
     touch "configurations/${i%%/}.yaml"
done

