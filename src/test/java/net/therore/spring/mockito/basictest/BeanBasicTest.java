package net.therore.spring.mockito.basictest;

import net.therore.spring.mockito.EnableMockedBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ComponentScan
@EnableMockedBean
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BeanBasicTest.class)
public class BeanBasicTest {

    @Autowired
    private QualifierMiddleComponent qualifierMiddleComponent;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {
        assertEquals("Hello Spring-Boot Active", qualifierMiddleComponent.getHelloMessage());
    }

}
