#!/bin/sh

gitlab-runner register --non-interactive ${REGISTER_NON_INTERACTIVE} --url ${CI_SERVER_URL} --description ${DESCRIPTION} --tag-list ${RUNNER_TAG_LIST} --registration-token ${REGISTRATION_TOKEN} --executor ${RUNNER_EXECUTOR} --docker-image ${DOCKER_IMAGE} --docker-network-mode ${DOCKER_NETWORK_MODE} --docker-volumes ${DOCKER_VOLUMES} --docker-privileged ${DOCKER_PRIVILEGED}

gitlab-runner run
