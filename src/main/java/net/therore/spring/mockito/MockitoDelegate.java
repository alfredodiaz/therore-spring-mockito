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

import org.mockito.Mockito;


/**
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 * @see <a href="https://github.com/alfredodiaz/therore-spring-mockito">https://github.com/alfredodiaz/therore-spring-mockito</a>
 **/
public class MockitoDelegate {

    public static <T> T mock(Class<T> classToMock) {
        return Mockito.mock(classToMock);
    }

    public static <T> T spy(Class<T> classToMock) {
        return Mockito.spy(classToMock);
    }

}
