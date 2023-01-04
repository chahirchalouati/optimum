package com.crcl.authentication.migration;

import com.crcl.authentication.configuration.props.SecurityProperties;
import com.crcl.authentication.repository.MongoClientRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;


@ChangeLog
public class ClientChangeLog {
    @ChangeSet(order = "001", id = "save_post_client", author = "@chahir_chalouati", runAlways = true)
    public void saveClients(MongoClientRepository clientRepository, SecurityProperties securityProperties) {
        securityProperties.getRegistrations().forEach((s, registration) -> System.out.println(registration));
    }
}
