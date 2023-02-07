#!/usr/bin/env sh
dirs=$(ls -d */)
for i in ${dirs[@]}; do
  touch ".github/workflows/deploy-${i%%/}.yaml"
 echo "
name: Build and Publish ${i%%/}

on:
  push:
    branches: [ develop ]
    paths:
      - ${i%%/}/**

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17 Temurin
        uses: actions/setup-java@v2
        with:
          java-version: 17
          distribution: temurin
          cache: 'maven'
      - name: Build with Maven
        run: mvn clean install -s $GITHUB_WORKSPACE/settings.xml  -pl ${i%%/}
      - name: Publish to Docker Hub
        uses: docker/build-push-action@v1
        with:
          username: ${{ secrets.DOCKER_HUB_USERNAME }}
          password: ${{ secrets.DOCKER_HUB_PASSWORD }}
          repository: chahirchalouati/gramify-ms
          tags: ${{github.run_number}}

 " >>  .github/workflows/deploy-${i%%/}.yaml

done
