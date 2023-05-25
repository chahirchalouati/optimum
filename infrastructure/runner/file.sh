#!/bin/bash

# GitLab API endpoint and access token
GITLAB_API=https://gitlab.com/api/v4
ACCESS_TOKEN=glpat-J4HBYvuJg1q7iKBB8peH

# Project or group ID
PROJECT_ID=46264638

# Retrieve all runners
runners=$(curl --header "PRIVATE-TOKEN: $ACCESS_TOKEN" "$GITLAB_API/projects/$PROJECT_ID/runners")
runner_ids=$(echo "$runners" | jq -r '.[].id')
# Iterate over runner IDs
for runner_id in $runner_ids; do
    echo "Deleting runner ID: $runner_id"
    # Delete the runner
    curl --request DELETE --header "PRIVATE-TOKEN: $ACCESS_TOKEN" "$GITLAB_API/projects/$PROJECT_ID/runners/$runner_id"
    echo "Runner ID: $runner_id deleted"
done

echo "All runners have been deleted."
