package com.crcl.comment.configuration;

import com.crcl.core.configuration.properties.WebSocketProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Import(WebSocketProperties.class)
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketProperties webSocketProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(webSocketProperties.getEndPoint()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(webSocketProperties.getPrefixes());
        registry.enableStompBrokerRelay(webSocketProperties.getStompBrokerRelay())
                .setRelayHost(webSocketProperties.getRelayHost())
                .setRelayPort(webSocketProperties.getRelayPort())
                .setClientLogin(webSocketProperties.getClientLogin())
                .setClientPasscode(webSocketProperties.getClientPasscode());
    }

}