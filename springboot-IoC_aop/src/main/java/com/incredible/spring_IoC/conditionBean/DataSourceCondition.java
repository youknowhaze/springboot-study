package com.incredible.spring_IoC.conditionBean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DataSourceCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 取出环境变量
        Environment env = conditionContext.getEnvironment();
        return  env.containsProperty("spring.dataSource.driver") &&
                env.containsProperty("spring.dataSource.url") &&
                env.containsProperty("spring.dataSource.username") &&
                env.containsProperty("spring.dataSource.password");

    }
}
