# Docker Image Documentation: gitlab-runner-custom
## Overview
The gitlab-runner-custom Docker image is based on the gitlab/gitlab-runner image with additional customization options. It provides a configurable environment for running GitLab CI/CD jobs using GitLab Runner.

## Usage
Building the Image
To build the gitlab-runner-custom image, use the following command:

```shell 
docker build -t gitlab-runner-custom .
```

Running the Container
To run a container using the gitlab-runner-custom image, provide the necessary environment variables and mount any required volumes. Here's an example:

```shell 
docker run -d \
-e REGISTER_NON_INTERACTIVE='' \
-e CI_SERVER_URL='' \
-e RUNNER_NAME='' \
-e RUNNER_EXECUTOR='' \
-e RUNNER_TAG_LIST='' \
-e RUNNER_REPOSITORY_URL='' \
-e DOCKER_IMAGE='' \
-e DOCKER_PRIVILEGED='' \
-e DOCKER_VOLUMES='' \
-e DOCKER_NETWORK_MODE='' \
-e REGISTRATION_TOKEN='' \
-e DESCRIPTION='' \
-v /path/to/workspace:/workspace \
gitlab-runner-custom
```

Replace the environment variables (ENV_VARIABLE) with your desired values. Mount any required volumes (/path/to/workspace) to the appropriate container paths.

Environment Variables
The gitlab-runner-custom image supports the following environment variables:

* REGISTER_NON_INTERACTIVE: Set this variable to true to enable non-interactive registration of the runner.
* CI_SERVER_URL: The URL of the GitLab CI/CD server.
* RUNNER_NAME: The name of the runner.
* RUNNER_EXECUTOR: The executor used by the runner (e.g., docker, shell, kubernetes).
* RUNNER_TAG_LIST: The comma-separated list of tags associated with the runner.
* RUNNER_REPOSITORY_URL: The URL of the GitLab Runner repository.
* DOCKER_IMAGE: The Docker image to be used for running job containers.
* DOCKER_PRIVILEGED: Set this variable to true to enable privileged mode for Docker containers.
* DOCKER_VOLUMES: The comma-separated list of Docker volumes to be mounted in job containers.
* DOCKER_NETWORK_MODE: The network mode to be used for Docker containers (e.g., bridge, host).
* REGISTRATION_TOKEN: The registration token for the runner.
* DESCRIPTION: A description for the runner.
### Entrypoint
The entrypoint of the gitlab-runner-custom image is set to /bin/sh, and the startup script /startup-script.sh is executed.

## Customization
You can customize the behavior of the gitlab-runner-custom image by modifying the startup-script.sh file. This file is copied to the image during the build process and executed as the entrypoint.

## License
This Docker image is distributed under the MIT License.

Feel free to modify the documentation to fit your specific requirements. Provide additional instructions or explanations as necessary.
