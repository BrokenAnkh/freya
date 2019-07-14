package freya.component.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.spring.web.plugins.Docket;

@ConfigurationProperties("swagger")
public class SwaggerProperties {
    private Boolean enable = true;
    private String title = "test";
    private String version = "1.0.0";
    private String group = Docket.DEFAULT_GROUP_NAME;
    private String description = "write your description";

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
