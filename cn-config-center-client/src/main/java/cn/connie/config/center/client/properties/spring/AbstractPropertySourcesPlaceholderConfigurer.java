package cn.connie.config.center.client.properties.spring;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import cn.connie.config.center.client.GlobalConfigration;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import cn.connie.config.center.common.CommonConstant;

public abstract class AbstractPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer
        implements IPropertySourcesPlaceholderConfigurer {

    protected String application;

    protected String propertiesName;

    protected String  customConfigcenterUrlKey;

    private static final String DEFAULT_URL=System.getenv(CommonConstant.CONFIG_CENTER_URL);

    public AbstractPropertySourcesPlaceholderConfigurer() throws IOException {
        Properties properties=System.getProperties();
        if (properties != null && !properties.isEmpty()) {
            loadProperties(properties);
        }
    }

    public void setCustomConfigcenterUrlKey(String customConfigcenterUrlKey) {
        this.customConfigcenterUrlKey = customConfigcenterUrlKey;
    }

    public void setApplication(String application) {
        this.application=application;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName=propertiesName;
    }

    public void init() throws Exception {
        if (StringUtils.isNotBlank(propertiesName)) {
            if (StringUtils.isBlank(application)) {
                throw new IllegalArgumentException("miss argument 'application'");
            }
            reloadProperties();
            afterInit();
        }
    }

    public abstract void afterInit() throws Exception;

    public void reloadProperties() throws Exception {
        Properties prop=new Properties();
        InputStream in=null;
        try {
            if(customConfigcenterUrlKey!=null){
                String defaultUrl =System.getenv(customConfigcenterUrlKey);
                in=new URL(defaultUrl + propertiesName + ".properties").openStream();
            }else {
                in = new URL(DEFAULT_URL + propertiesName + ".properties").openStream();
            }
            prop.load(in);
            GlobalConfigration.reload(prop);
            this.setProperties(prop);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch(Exception e) {
                }
            }
        }
    }
}
