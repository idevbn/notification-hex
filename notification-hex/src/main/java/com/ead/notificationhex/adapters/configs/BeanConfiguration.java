package com.ead.notificationhex.adapters.configs;

import com.ead.notificationhex.NotificationHexApplication;
import com.ead.notificationhex.core.ports.NotificationPersistencePort;
import com.ead.notificationhex.core.ports.services.NotificationServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NotificationHexApplication.class)
public class BeanConfiguration {

    @Bean
    public NotificationServicePortImpl notificationServicePort(
            final NotificationPersistencePort persistence
    ) {
            return new NotificationServicePortImpl(persistence);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
