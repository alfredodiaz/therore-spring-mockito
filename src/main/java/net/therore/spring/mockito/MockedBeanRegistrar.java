package net.therore.spring.mockito;

import org.mockito.Mockito;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;

public class MockedBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Field[] fields = ((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getDeclaredFields();
        for (Field field : fields) {
            MockedBean annotation = field.getAnnotation(MockedBean.class);
            if (annotation!=null) {
                Class clazz = field.getType();
                String[] names = annotation.value();
                BeanDefinition definition = BeanDefinitionBuilder
                        .rootBeanDefinition(Mockito.class)
                        .setFactoryMethod("mock")
                        .addConstructorArgValue(clazz.getName())
                        .getBeanDefinition();

                if (names.length == 0)
                    names = new String[]{ClassUtils.getShortNameAsProperty(clazz)};

                for (String name : names) {
                    registry.registerBeanDefinition(name, definition);
                }
            }
        }
   }

}