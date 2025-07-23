package com.example.myApp.messaging;

import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;

public interface MessagingDispatcher {

    Mono<Object> requestResponse(String routingKey, String serviceName, Locale locale, Object payload, Map<String, Object> headers);

    Mono<Void> fireAndForget(String routingKey, String serviceName, Locale locale, Object payload);
}
