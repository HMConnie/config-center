package cn.connie.config.center;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class CustomPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    public CustomPlaceholderConfigurer() throws IOException {
        Properties properties=System.getProperties();
        if (properties != null && !properties.isEmpty()) {
            loadProperties(properties);
        }
    }
}
