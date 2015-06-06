package net.therore.spring.mockito.basictest;

import net.therore.spring.mockito.EnableMockedBean;
import net.therore.spring.mockito.MockedBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@SpringApplicationConfiguration(classes = MockedBeanBasicTest.class)
public class MockedBeanBasicTest {

    @MockedBean
    @Qualifier("active")
    private QualifierHelloWorldService qualifierHelloWorldService;

    @Autowired
    private QualifierMiddleComponent qualifierMiddleComponent;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        qualifierMiddleComponent.getHelloMessage();

        // THEN HelloWorldService is called only once
        verify(qualifierHelloWorldService, times(1)).getHelloMessage();
    }

}
