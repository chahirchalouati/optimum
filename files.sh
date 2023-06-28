#!/bin/bash

# Directory containing the directories
directory="./"
# Loop through each directory
for dir in "$directory"/*; do
  if [ -d "$dir" ]; then
    # Extract the directory name
    dir_name=$(basename "$dir")

    rm -f "$dir_name/.gitlab-ci-$dir_name.yml"
    # Create a file with the directory name and write the content
    file_path="$dir_name/.gitlab-ci.yml"

    # Add additional content to the file using a Here Document
    echo $dir_name
  fi
done
