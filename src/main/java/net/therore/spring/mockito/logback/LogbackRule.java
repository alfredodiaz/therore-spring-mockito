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

package net.therore.spring.mockito.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 */
public class LogbackRule implements TestRule {

    private final Logger rootLogger = ((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME));

    @Getter(AccessLevel.PACKAGE)
    static private ThreadLocal<Appender<ILoggingEvent>> appenderMock = new ThreadLocal<Appender<ILoggingEvent>>();

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				before();
				try {
					base.evaluate();
				} finally {
					after();
				}
			}
		};
	}

	private void before() {
        Appender<ILoggingEvent> mock = mock(Appender.class);
        appenderMock.set(mock);
		when(mock.getName()).thenReturn("MOCK");
		rootLogger.addAppender(mock);
	}

	private void after() {
		rootLogger.detachAppender(appenderMock.get());
        appenderMock.remove();
	}

}

