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
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MockedBeanRegistrar.class)
public @interface EnableMockedBean {
}
