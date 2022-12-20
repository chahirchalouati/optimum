FROM java:8u20
ARG JMETER_VERSION="5.5"
COPY apache-jmeter-${JMETER_VERSION}.tgz ./
RUN tar xzf ./apache-jmeter-${JMETER_VERSION}.tgz  \
    && rm ./apache-jmeter-${JMETER_VERSION}.tgz \
&& ln -s ./apache-jmeter-${JMETER_VERSION}/bin/jmeter /usr/local/bin
COPY user.properties apache-jmeter-${JMETER_VERSION}/bin/user.properties
CMD ./jmeter