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

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.therore.spring.mockito.logback.LogbackMockito.*;
import static net.therore.spring.mockito.logback.LogbackMockito.verify;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 */
public class LogbackRuleTest {

	@Rule
	public LogbackRule logbackRule = new LogbackRule();

	@Test
	public void happyPath() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("info message");

        verify(logContainsErrorWithException(), never());
	}

	@Test(expected = AssertionError.class)
	public void exceptionPath() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.error("error message", new RuntimeException());

        verify(logContainsErrorWithException(), never());
	}

	@Test(expected = AssertionError.class)
	public void combinedExceptionPath() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("info message");
		log.error("error message", new RuntimeException());

        verify(logContainsErrorWithException(), never());
	}

	@Test
	public void testContainingSpecificMessage() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("specific message");

        verify(logContainsText("specific message"), atLeastOnce());
	}

	@Test
	public void testContainingCombinedSpecificMessage() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("specific message");
		log.info("other message");

        verify(logContainsText("specific message"), only());
	}

	@Test(expected = AssertionError.class)
	public void testNotContainingSpecificMessage() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("specific message");

        verify(logContainsText("specific message"), never());
	}

	@Test(expected = AssertionError.class)
	public void testNotContainingCombinedSpecificMessage() {
		Logger log = LoggerFactory.getLogger(getClass());
		log.info("other message");
		log.info("specific message");

        verify(logContainsText("specific message"), never());
	}

}
