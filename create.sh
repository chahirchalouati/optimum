#!/usr/bin/env sh
dirs=$(ls -d */)
for i in ${dirs[@]}; do
  touch "k8s/k8s-${i%%/}.yaml"
done
