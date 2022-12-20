#!/usr/bin/env sh
echo "INITIALIZING CASSANDRA KEYSPACES"
cd "$(dirname "$0")" || exit
# shellcheck disable=SC2039
docker exec -it cassandra-container cqlsh -e "$(<commands.cql)" localhost
docker exec -it cassandra-container cqlsh -e "EXIT" localhost
docker exec -it cassandra-container nodetool status 127.0.0.1
echo "END INITIALIZATION"
