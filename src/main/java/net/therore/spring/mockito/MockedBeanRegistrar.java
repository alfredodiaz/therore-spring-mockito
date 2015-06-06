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

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Primary;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.lang.reflect.Field;

/**
 * @author <a href="mailto:alfredo.diaz@therore.net">Alfredo Diaz</a>
 * @see <a href="https://github.com/alfredodiaz/therore-spring-mockito">https://github.com/alfredodiaz/therore-spring-mockito</a>
 **/
public class MockedBeanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Field[] fields = ((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getDeclaredFields();
        for (Field field : fields) {
            MockedBean mockedBean = field.getAnnotation(MockedBean.class);
            if (mockedBean!=null)
                registerMockedBean(registry, field, mockedBean);

            SpiedBean spiedBean = field.getAnnotation(SpiedBean.class);
            if (spiedBean!=null)
                registerSpiedBean(registry, field, spiedBean);

        }
   }

    private void registerMockedBean(BeanDefinitionRegistry registry, Field field, MockedBean annotation) {
        Class clazz = field.getType();
        String[] names = annotation.value();

        RootBeanDefinition definition = (RootBeanDefinition) BeanDefinitionBuilder
                .rootBeanDefinition(MockitoDelegate.class)
                .setFactoryMethod("mock")
                .addConstructorArgValue(clazz.getName())
                .getBeanDefinition();

        Qualifier qualifier = field.getAnnotation(Qualifier.class);
        if (qualifier==null)
            qualifier = field.getDeclaringClass().getAnnotation(Qualifier.class);

        if (qualifier!=null) {
            if (Primary.class.equals(qualifier)) {
                definition.setPrimary(true);
            }
            definition.addQualifier(new AutowireCandidateQualifier(Qualifier.class, qualifier.value()));
        }

        if (names.length == 0)
            names = new String[]{field.getName()};

        for (String name : names) {
            try {registry.removeBeanDefinition(name);} catch (NoSuchBeanDefinitionException e) {}
            registry.registerBeanDefinition(name, definition);
        }
    }

    private void registerSpiedBean(BeanDefinitionRegistry registry, Field field, SpiedBean annotation) {
        Class clazz = field.getType();
        String name = annotation.value();

        if (name.isEmpty())
            name = field.getName();

        BeanDefinition realDefinition = registry.getBeanDefinition(name);
        realDefinition.setAutowireCandidate(false);
        String realBeanName = "_real_" + name;
        registry.registerBeanDefinition(realBeanName, realDefinition);

        RootBeanDefinition definition = (RootBeanDefinition) BeanDefinitionBuilder
                .rootBeanDefinition(MockitoDelegate.class)
                .setFactoryMethod("spy")
                .addConstructorArgValue(clazz.getName())
                .getBeanDefinition();

        definition.setPrimary(true);

        Qualifier qualifier = field.getAnnotation(Qualifier.class);
        if (qualifier==null)
            qualifier = field.getDeclaringClass().getAnnotation(Qualifier.class);

        if (qualifier!=null)
            definition.addQualifier(new AutowireCandidateQualifier(Qualifier.class, qualifier.value()));

        registry.registerBeanDefinition(name, definition);
    }


}