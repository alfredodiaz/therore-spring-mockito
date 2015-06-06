package net.therore.spring.mockito.basicspytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicSpyMiddleComponent {

    @Autowired
    private BasicHSpyHelloWorldService basicHSpyHelloWorldService;

    public String getHelloMessage() {
        return basicHSpyHelloWorldService.getHelloMessage();
    }

}

