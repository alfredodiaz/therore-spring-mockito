package net.therore.spring.mockito.qualifiertest;

import net.therore.spring.mockito.EnableMockedBean;
import net.therore.spring.mockito.MockedBean;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringApplicationConfiguration(classes = MockedBeanQualifierTest.class)
public class MockedBeanQualifierTest {

    @MockedBean
    @Qualifier("active")
    private HelloWorldService helloWorldService;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        // WHEN I launch SampleSimpleApplication
        SampleSimpleApplication.main(new String[0]);

        // THEN HelloWorldService is called only once
        verify(helloWorldService,times(1)).getHelloMessage();
    }

}
