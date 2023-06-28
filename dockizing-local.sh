#!/bin/bash
directory="./"
for dir in "$directory"/*; do
  if [ -d "$dir" ]; then

    dir_name=$(basename "$dir")
    if [ -f $dir_name/Dockerfile ]; then
      echo "Start building docker image for $dir_name"
      $dir_name/mvnw -DskipTests=true clean install -pl $dir_name
      docker build -f $dir_name/Dockerfile -t registry.gitlab.com/chehhhir/gramify-ms/$dir_name ./$dir_name
      docker push registry.gitlab.com/chehhhir/gramify-ms/$dir_name:latest
      echo "End building docker image for $dir_name"
    fi
    echo "$file_path"
  fi
done
# docker build -f authorization-server/Dockerfile -t registry.gitlab.com/chehhhir/gramify-ms/authorization-server ./authorization-server
