package net.therore.spring.mockito.basictest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BasicMiddleComponent {

    @Autowired
    @Qualifier("active")
    private BasicHelloWorldService basicHelloWorldService;

    public String getHelloMessage() {
        return basicHelloWorldService.getHelloMessage();
    }

}

