#!/bin/bash
directory="./"
for dir in "$directory"/*; do
  if [ -d "$dir" ]; then

    dir_name=$(basename "$dir")

    if [ -f "$dir_name/Dockerfile" ]; then
    echo "Start building docker image for $dir<_name"
     # shellcheck disable=SC2164
     cd $dir_name
     docker build  . -t registry.gitlab.com/chehhhir/gramify-ms/$dir_name:1.0.1
      cd ..
    fi
  fi
done
