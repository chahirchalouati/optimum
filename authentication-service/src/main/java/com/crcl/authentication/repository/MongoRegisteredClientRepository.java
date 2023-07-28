package com.crcl.authentication.repository;

import com.crcl.authentication.domain.GramifyClient;
import com.crcl.authentication.mappers.ClientMapper;
import com.crcl.core.exceptions.ClientNotFoundException;
import com.crcl.core.exceptions.EntityNotFoundException;
import com.crcl.core.utils.generic.Enhancer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@AllArgsConstructor
@Slf4j
public class MongoRegisteredClientRepository implements RegisteredClientRepository {

    private final MongoClientRepository repository;
    private final ClientMapper clientMapper;
    private final Enhancer<GramifyClient> clientSettingsEnhancer;

    @Override
    public void save(RegisteredClient registeredClient) {
        repository.save(clientMapper.toClient(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        return repository.findById(id)
                .map(clientSettingsEnhancer::enhance)
                .map(clientMapper::toRegisteredClient)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find client with id " + id));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return repository.findByClientId(clientId)
                .map(clientSettingsEnhancer::enhance)
                .map(clientMapper::toRegisteredClient)
                .orElseThrow(() -> new ClientNotFoundException("Unable to find client with clientId " + clientId));
    }


}
