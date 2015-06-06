package net.therore.spring.mockito.basicspytest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class BasicSpyHelloWorldService {

    @Value("${name:Spring-Boot}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}

