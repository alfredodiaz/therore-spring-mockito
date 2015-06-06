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

package net.therore.spring.mockito;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * &#064;EnableMockedBean ensures that &#064;{@link net.therore.spring.mockito.MockedBean} annotations
 * will be properly processed.
 * <pre class="code">
 * &#064;ComponentScan()
 * &#064;EnableMockedBean
 * &#064;EnableAutoConfiguration
 * &#064;RunWith(SpringJUnit4ClassRunner.class)
 * &#064;SpringApplicationConfiguration(classes = MySampleServiceTest.class)
 * public class MySampleServiceTest {
 *     &#064;Autowired
 *     &#064;MockedBean
 *     private HelloWorldService helloWorldService;
 *
 *     &#064;Test
 *     public void helloWorldIsCalledOnlyOnce() throws Exception {
 *         // WHEN I launch SampleSimpleApplication
 *         SampleSimpleApplication.main(new String[0]);
 *         // THEN HelloWorldService is called only once
 *         verify(helloWorldService,times(1)).getHelloMessage();
 *     }
 *  }
 *  </pre>
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 * @see <a href="https://github.com/alfredodiaz/therore-spring-mockito">https://github.com/alfredodiaz/therore-spring-mockito</a>
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MockedBeanRegistrar.class)
public @interface EnableMockedBean {
}
