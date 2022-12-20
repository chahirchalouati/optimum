FROM cassandra:4.0
COPY jmx_exporter.yml /etc/cassandra/
RUN wget -O /lib/jmx_prometheus_javaagent-0.13.0.jar https://search.maven.org/remotecontent?filepath=io/prometheus/jmx/jmx_prometheus_javaagent/0.13.0/jmx_prometheus_javaagent-0.13.0.jar
RUN echo 'JVM_OPTS="$JVM_OPTS -javaagent:/lib/jmx_prometheus_javaagent-0.13.0.jar=7070:/etc/cassandra/jmx_exporter.yml"' >> /etc/cassandra/cassandra-env.sh
EXPOSE 9042 7070
CMD ["cassandra", "-f"]