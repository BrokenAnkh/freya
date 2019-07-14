package freya.component.echo.pojo;

import freya.component.echo.pojo.type.AuthType;
import freya.component.echo.pojo.type.ProtocolType;
import freya.component.echo.util.EmailConfigUtil;

import javax.mail.Authenticator;
import javax.mail.URLName;
import java.util.Properties;

public class EmailSessionProps {
    /**
     * 协议类型(transport,store)
     */
    private ProtocolType protocolType = ProtocolType.STORE;
    /**
     * 服务器地址配置
     */
    private String protocol;
    private String host;
    private Integer port;
    /**
     * 验证信息
     */
    private String username;
    private String password;
    /**
     * 验证类型
     */
    private AuthType authType = AuthType.NONE;
    /**
     * 超时设置
     */
    private Integer timeout = 100000;
    private Integer writeTimeout = 100000;
    private Integer statusCacheTimeout = 100000;
    /**
     * 连接池设置
     */
    private Integer connectionPoolSize = 3;
    private Integer connectionPoolTimeout = 100000;

    private URLName urlName;
    private Authenticator auth;
    private Properties props;

    /**
     * 初始化配置类
     */
    public void init() {
        this.urlName = this.urlName == null ? EmailConfigUtil.createUrlName(this) : this.urlName;
        this.auth = this.auth == null ? EmailConfigUtil.createAuth(this) : this.auth;
        this.props = this.props == null ? new Properties() : this.props;
        // 初始化配置
        EmailConfigUtil.configMailProps(this, props);
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Integer writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public Integer getStatusCacheTimeout() {
        return statusCacheTimeout;
    }

    public void setStatusCacheTimeout(Integer statusCacheTimeout) {
        this.statusCacheTimeout = statusCacheTimeout;
    }

    public Integer getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public Integer getConnectionPoolTimeout() {
        return connectionPoolTimeout;
    }

    public void setConnectionPoolTimeout(Integer connectionPoolTimeout) {
        this.connectionPoolTimeout = connectionPoolTimeout;
    }

    public URLName getUrlName() {
        return urlName;
    }

    public void setUrlName(URLName urlName) {
        this.urlName = urlName;
    }

    public Authenticator getAuth() {
        return auth;
    }

    public void setAuth(Authenticator auth) {
        this.auth = auth;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}
