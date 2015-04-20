package net.therore.spring.mockito;

import net.therore.spring.mockito.service.HelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ComponentScan
@EnableMockedBean
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MySampleServiceTest.class)
public class MySampleServiceTest {

    @MockedBean
    private HelloWorldService helloWorldService;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        // WHEN I launch SampleSimpleApplication
        SampleSimpleApplication.main(new String[0]);

        // THEN HelloWorldService is called only once
        verify(helloWorldService,times(1)).getHelloMessage();
    }

}
