package com.homihq.db2rest.d1.config;


import com.homihq.db2rest.core.Dialect;
import com.homihq.db2rest.d1.D1Dialect;
import com.homihq.db2rest.jdbc.sql.UpdateCreatorTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Configuration
@ConditionalOnBean(D1Dialect.class)
public class D1SqlCreatorConfiguration {


    @Bean
    public UpdateCreatorTemplate updateCreatorTemplate(SpringTemplateEngine templateEngine, Dialect dialect) {
        return new UpdateCreatorTemplate(templateEngine, dialect);
    }




}
