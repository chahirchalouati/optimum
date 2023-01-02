#!/usr/bin/env sh
eval "../../mvnw -B --file ../../pom.xml -s ../../settings.xml  -DskipTests=true clean install"
docker-compose -f ../docker/docker-compose-dev.yaml -p microservices up -d --build
#while :; do
#  result=$(curl http://localhost:9090/api/v1/query?query=up | jq '.data.result[ ].metric | select(.job=="cassandra" and .__name__=="up")')
#      echo "$result"
#  if [ -n "$result" ]; then
#
#    sh cassandra_init.sh
#    break
#  fi
#  if [ "$ATTEMPT" -eq 5 ]; then
#    break
#  fi
#  sleep 5
#  # shellcheck disable=SC2039
#  (ATTEMPT++)
#done
