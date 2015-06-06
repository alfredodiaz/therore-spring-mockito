package net.therore.spring.mockito.qualifiertest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("active")
@Profile("!unit-test")
public class QualifierHelloWorldService {

    @Value("${name:Spring-Boot Active}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}

