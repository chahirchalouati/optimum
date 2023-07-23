#!/bin/bash

gitlab-runner register \
 --non-interactive ${REGISTER_NON_INTERACTIVE} \
 --url ${CI_SERVER_URL} \
 --registration-token ${REGISTRATION_TOKEN} \
 --executor ${RUNNER_EXECUTOR} \
 --docker-image ${DOCKER_IMAGE} \
 --description ${DESCRIPTION} \
 --tag-list ${RUNNER_TAG_LIST} \
 --docker-volumes ${DOCKER_VOLUME}

gitlab-runner run
