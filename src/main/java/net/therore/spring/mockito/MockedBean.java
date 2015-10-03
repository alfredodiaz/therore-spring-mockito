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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Mark a field as mock. It also registers the mock as a bean in the SpringContext.
 * This way the mock is injected in every bean that depends on it.
 * The class containing annotated fields has to be annotated with
 * &#064;{@link net.therore.spring.mockito.EnableMockedBean}.
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 * @see <a href="https://github.com/alfredodiaz/therore-spring-mockito">https://github.com/alfredodiaz/therore-spring-mockito</a>
 **/
@Autowired
@Documented
@Target(ElementType.FIELD)
@Import(MockedBeanRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MockedBean {

    /**
     * The name of this bean, or if plural, aliases for this bean. If left unspecified
     * the name of the bean is the name of the annotated method. If specified, the method
     * name is ignored.
     * @return the name(s) of the bean(s)
     */
    String[] value() default {};

}
