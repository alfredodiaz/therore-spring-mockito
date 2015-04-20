package net.therore.spring.mockito.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}

