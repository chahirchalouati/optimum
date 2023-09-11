#!/bin/bash
directory="./"
for dir in "$directory"/*; do
  if [ -d "$dir" ]; then

    dir_name=$(basename "$dir")
    #    if [ -f "$dir_name/src/main/resources/logback-spring.xml" ]; then
    #      echo "$dir_name/src/main/resources/logback-spring.xml"
    #      rm -r "$dir_name/src/main/resources/logback-spring.xml"
    #    fi
    echo "$dir_name"
  fi
done
# docker build -f authorization-server/Dockerfile -t registry.gitlab.com/chehhhir/gramify-ms/authorization-server ./authorization-server
