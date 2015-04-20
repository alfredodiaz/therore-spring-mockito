package net.therore.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Mark a field as mock. It also registers the mock as a bean in the SpringContext.
 * This way the mock is injected in every bean that depends on.
 * The class containing annotated fields has to be annotated with
 * &#064;{@link net.therore.spring.mockito.EnableMockedBean}.
 */
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
     */
    String[] value() default {};

}
