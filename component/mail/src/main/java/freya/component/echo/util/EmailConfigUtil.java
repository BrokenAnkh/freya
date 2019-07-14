package freya.component.echo.util;

import freya.component.echo.entity.GenericEmailAccountEntity;
import freya.component.echo.pojo.EmailSessionProps;
import freya.component.echo.pojo.EmailSessionTemplate;
import freya.component.echo.pojo.type.AuthType;
import freya.component.echo.pojo.type.ProtocolType;

import javax.mail.*;
import java.util.Properties;

public class EmailConfigUtil {
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    /**
     * 获取 Session 实例
     */
    public static Session getSession(Properties props) {
        return Session.getInstance(props, null);
    }

    public static Session getSession(Properties props, Boolean debug) {
        Session instance = Session.getInstance(props, null);
//        instance.setDebug(debug);
        return instance;
    }


    public static Session getSession(Properties props, Authenticator auth) {
        return Session.getInstance(props, auth);
    }

    public static Session getSession(Properties props, Authenticator auth, Boolean debug) {
        Session instance = Session.getInstance(props, auth);
//        instance.setDebug(debug);
        return instance;
    }

    /**
     * 获取 Store 或 Transport 实例
     */
    public static Store getStore(Session session) throws NoSuchProviderException {
        return session.getStore();
    }

    public static Store getStore(Session session, URLName urlName) throws NoSuchProviderException {
        return session.getStore(urlName);
    }

    public static Transport getTransport(Session session) throws NoSuchProviderException {
        return session.getTransport();
    }

    public static Transport getTransport(Session session, URLName urlName) throws NoSuchProviderException {
        return session.getTransport(urlName);
    }

    /**
     * 配置方法
     */
    public static boolean validSessionConfig(EmailSessionProps sessionConfig) {
        if (sessionConfig.getProtocol() == null) {
            return false;
        } else if (sessionConfig.getHost() == null) {
            return false;
        } else if (sessionConfig.getPort() == null) {
            return false;
        } else if (sessionConfig.getUsername() == null) {
            return false;
        } else if (sessionConfig.getPassword() == null) {
            return false;
        } else if (sessionConfig.getProtocolType() == null) {
            return false;
        } else if (sessionConfig.getAuthType() == null) {
            return false;
        }
        return true;
    }

    public static Properties configMailProps(EmailSessionProps sessionConfig) {
        Properties props = new Properties();
        configMailProps(sessionConfig, props);
        return props;
    }

    public static void configMailProps(EmailSessionProps sessionConfig, Properties props) {
        configProtocolProps(sessionConfig, props);
        configGeneralMailProps(sessionConfig, props);
        configAuthProps(sessionConfig, props);
    }

    private static void configProtocolProps(EmailSessionProps sessionConfig, Properties props) {
        String prefix = "mail." + sessionConfig.getProtocolType().getName().toLowerCase();
        // 配置邮箱协议
        props.setProperty(prefix + ".protocol", sessionConfig.getProtocol());
    }

    private static void configGeneralMailProps(EmailSessionProps sessionConfig, Properties props) {
        String prefix = "mail." + sessionConfig.getProtocol();
        // 服务器配置
        props.put(prefix + ".host", sessionConfig.getHost());
        props.put(prefix + ".port", sessionConfig.getPort());
        // 登录用户
        props.put(prefix + ".user", sessionConfig.getUsername());
        props.put(prefix + ".password", sessionConfig.getPassword());
        // 超时设置
        if (sessionConfig.getTimeout() != null) {
            props.put(prefix + ".timeout", sessionConfig.getTimeout());
        }
        if (sessionConfig.getWriteTimeout() != null) {
            props.put(prefix + ".writetimeout", sessionConfig.getWriteTimeout());
        }
        if (sessionConfig.getStatusCacheTimeout() != null) {
            props.put(prefix + ".statuscachetimeout", sessionConfig.getStatusCacheTimeout());
        }
        // 连接池设置
        if (sessionConfig.getConnectionPoolSize() != null) {
            props.put(prefix + ".connectionpoolsize", sessionConfig.getConnectionPoolSize());
        }
        if (sessionConfig.getConnectionPoolTimeout() != null) {
            props.put(prefix + ".connectionpooltimeout", sessionConfig.getConnectionPoolTimeout());
        }
    }


    private static void configAuthProps(EmailSessionProps sessionConfig, Properties props) {
        String prefix = "mail." + sessionConfig.getProtocol();
        // 配置Auth
        props.put(prefix + ".auth", true);
        // 根据校验类型配置props
        if (AuthType.SSL.equals(sessionConfig.getAuthType())) {
            configSslProps(sessionConfig, props);
            return;
        } else if (AuthType.TLS.equals(sessionConfig.getAuthType())) {
            configStarttlsProps(sessionConfig, props);
            return;
        }
    }

    private static void configSslProps(EmailSessionProps sessionConfig, Properties props) {
        String prefix = "mail." + sessionConfig.getProtocol();
        // 配置SSL
        props.put(prefix + ".ssl.enable", true);
        props.put(prefix + ".ssl.trust", "*");
        props.put(prefix + ".socketFactory.class", SSL_FACTORY);
        props.put(prefix + ".socketFactory.fallback", "false");
    }

    private static void configStarttlsProps(EmailSessionProps sessionConfig, Properties props) {
        String prefix = "mail." + sessionConfig.getProtocol();
        // 配置TLS
        props.put(prefix + ".starttls.enable", true);
    }

    /**
     * 类生成方法
     *
     * @param sessionConfig
     * @return
     */
    public static URLName createUrlName(EmailSessionProps sessionConfig) {
        String username = sessionConfig.getUsername();
        String user = username.substring(0, username.indexOf("@"));
        return new URLName(sessionConfig.getProtocol(), sessionConfig.getHost(), sessionConfig.getPort(), null, user, sessionConfig.getPassword());
    }

    public static Authenticator createAuth(EmailSessionProps sessionConfig) {
        return new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sessionConfig.getUsername(), sessionConfig.getPassword());
            }
        };
    }

    public static EmailSessionTemplate createMailSessionTemplate(GenericEmailAccountEntity entity) {
        // 配置 Store
        AuthType storeAuthType = BasicEnumUtil.typeOf(AuthType.class, entity.getReceiveAuthType());
        EmailSessionProps storeConfig = new EmailSessionProps();
        storeConfig.setProtocolType(ProtocolType.STORE);
        storeConfig.setProtocol(entity.getReceiveProtocol());
        storeConfig.setHost(entity.getReceiveHost());
        storeConfig.setPort(entity.getReceivePort());
        storeConfig.setUsername(entity.getAccount());
        storeConfig.setPassword(entity.getPassword());
        storeConfig.setAuthType(storeAuthType);
        // 配置 Transport
        AuthType transportAuthType = BasicEnumUtil.typeOf(AuthType.class, entity.getSendAuthType());
        EmailSessionProps transportConfig = new EmailSessionProps();
        transportConfig.setProtocolType(ProtocolType.TRANSPORT);
        transportConfig.setProtocol(entity.getSendProtocol());
        transportConfig.setHost(entity.getSendHost());
        transportConfig.setPort(entity.getSendPort());
        transportConfig.setUsername(entity.getAccount());
        transportConfig.setPassword(entity.getPassword());
        transportConfig.setAuthType(transportAuthType);
        // 返回Session模板类
        return new EmailSessionTemplate(transportConfig, storeConfig);
    }
}
