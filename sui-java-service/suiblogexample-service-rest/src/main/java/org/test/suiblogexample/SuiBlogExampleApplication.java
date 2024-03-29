package org.test.suiblogexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableSwagger2
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@EntityScan(basePackages = {
        "org.test.suiblogexample.sui.contract"
})
@EnableScheduling
//@EnableAutoConfiguration
public class SuiBlogExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SuiBlogExampleApplication.class, args);
        //ApplicationContext.current = new SpringApplicationContext(ctx);
        ctx.publishEvent(new ContextStartedEvent(ctx));
    }

}
