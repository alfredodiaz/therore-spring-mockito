package net.therore.spring.mockito.basicspytest;

import net.therore.spring.mockito.EnableMockedBean;
import net.therore.spring.mockito.SpiedBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ComponentScan
@EnableMockedBean
@EnableAutoConfiguration
@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpiedBeanBasicTest.class)
public class SpiedBeanBasicTest {

    @SpiedBean
    private BasicHSpyHelloWorldService basicHSpyHelloWorldService;

    @Autowired
    private BasicSpyMiddleComponent basicSpyMiddleComponent;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        basicSpyMiddleComponent.getHelloMessage();

        // THEN HelloWorldService is called only once
        verify(basicHSpyHelloWorldService, times(1)).getHelloMessage();
    }

}
