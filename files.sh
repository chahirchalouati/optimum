#!/bin/bash

# Directory containing the directories
directory="./"
# Loop through each directory
for dir in "$directory"/*; do
  if [ -d "$dir" ]; then
    # Extract the directory name
    dir_name=$(basename "$dir")

    # Create a file with the directory name and write the content
    file_path=".gitlab-ci-$dir_name.yml"

    # Add additional content to the file using a Here Document
    cat <<EOF > "$file_path"
# Update n.0
.changes:
  only:
    changes:
      - \$MODULE_NAME/**
      - .gitlab-ci-$dir_name.yml
      - .gitlab-ci-common.yml
      - .gitlab-ci.yml

$dir_name-build:
  variables:
    MODULE_NAME: $dir_name
  extends:
    - .build

$dir_name-docker:
  variables:
    MODULE_NAME: $dir_name
  extends:
    - .docker

$dir_name-deploy:
  variables:
    MODULE_NAME: $dir_name
  extends:
    - .deploy

EOF

    echo "Created file: $file_path"
  fi
done
