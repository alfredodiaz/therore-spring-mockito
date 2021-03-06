/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package net.therore.spring.mockito.xmlconfigurationtest;

import net.therore.spring.mockito.EnableMockedBean;
import net.therore.spring.mockito.MockedBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 * @see <a href="https://github.com/alfredodiaz/therore-spring-mockito">https://github.com/alfredodiaz/therore-spring-mockito</a>
 **/
@EnableMockedBean
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MockedBeanBasicTest.class)
@ImportResource("classpath:net/therore/spring/mockito/xmlconfigurationtest/applicationContext.xml")
public class MockedBeanBasicTest {

    @MockedBean
    private BasicHelloWorldService basicHelloWorldService;

    @Autowired
    private BasicMiddleComponent basicMiddleComponent;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        basicMiddleComponent.getHelloMessage();

        // THEN HelloWorldService is called only once
        verify(basicHelloWorldService, times(1)).getHelloMessage();
    }

}

