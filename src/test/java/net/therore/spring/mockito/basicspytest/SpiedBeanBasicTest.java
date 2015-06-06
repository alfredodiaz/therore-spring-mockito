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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 * @see <a href="https://github.com/alfredodiaz/therore-spring-mockito">https://github.com/alfredodiaz/therore-spring-mockito</a>
 **/
@ComponentScan
@EnableMockedBean
@EnableAutoConfiguration
@ActiveProfiles("unit-test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpiedBeanBasicTest.class)
public class SpiedBeanBasicTest {

    @SpiedBean
    private BasicSpyHelloWorldService basicSpyHelloWorldService;

    @Autowired
    private BasicSpyMiddleComponent basicSpyMiddleComponent;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        assertEquals("Hello Spring-Boot", basicSpyMiddleComponent.getHelloMessage());

        // THEN HelloWorldService is called only once
        verify(basicSpyHelloWorldService, times(1)).getHelloMessage();
    }

}
