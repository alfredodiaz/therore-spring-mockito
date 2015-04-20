# Spring Integration Tests With Mocks

This project is a fork of [springmockedtests](https://github.com/knes1/springmockedtests) and uses the same method for
injecting beans. Thank you a lot, [Kresimir](https://github.com/knes1).

The goal is to have a mechanism to inject a mock in the Spring Context so it can replace the original ones.
I have used annotations in a bit different way.

Here's the test class:

```java
@ComponentScan
@EnableMockedBean
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MySampleServiceTest.class)
public class MySampleServiceTest {

    @MockedBean
    private HelloWorldService helloWorldService;

    @Test
    public void helloWorldIsCalledOnlyOnce() throws Exception {

        // WHEN I launch SampleSimpleApplication
        SampleSimpleApplication.main(new String[0]);

        // THEN HelloWorldService is called only once
        verify(helloWorldService,times(1)).getHelloMessage();
    }

}
```

The class have to be annotated with `@EnableMockedBean`and every "*mocked bean*" with `MockedBean` To work properly.

In this example a mock of type `HelloWorldService` is injected in the context and autowired in the field
`helloWorldService`.


Include the following dependency in your pom for using it.

``` xml
<dependency>
    <groupId>net.therore.spring.mockito</groupId>
    <artifactId>therore-spring-mockito</artifactId>
</dependency>
```
