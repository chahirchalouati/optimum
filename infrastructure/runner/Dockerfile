# docker build -f Dockerfile -t crcl-gitlab-runner .
# docker run crcl-gitlab-runner
FROM gitlab/gitlab-runner:latest

ENV REGISTER_NON_INTERACTIVE='';
ENV CI_SERVER_URL='';
ENV RUNNER_NAME='';
ENV RUNNER_EXECUTOR='';
ENV RUNNER_TAG_LIST='';
ENV RUNNER_REPOSITORY_URL='';
ENV DOCKER_IMAGE='';
ENV DOCKER_PRIVILEGED='';
ENV DOCKER_VOLUMES='';
ENV DOCKER_NETWORK_MODE='';
ENV REGISTRATION_TOKEN='';
ENV DESCRIPTION='';

COPY runner_boostrap.bash /runner_boostrap.bash
RUN sed -i 's/\r$//' runner_boostrap.bash
RUN chmod u+x runner_boostrap.bash

ENTRYPOINT ["/bin/bash", "-c", "/runner_boostrap.bash"]
