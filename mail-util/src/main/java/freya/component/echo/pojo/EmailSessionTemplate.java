package freya.component.echo.pojo;

import freya.component.echo.util.EmailConfigUtil;

import javax.mail.*;

public class EmailSessionTemplate {
    public EmailSessionProps transportConfig;
    public EmailSessionProps storeConfig;

    public Session transportSession;
    public Session storeSession;

    public Folder cacheFolder;

    private boolean isInit = false;

    /**
     * 构建方法
     *
     * @param transportConfig
     * @param storeConfig
     */
    public EmailSessionTemplate(EmailSessionProps transportConfig, EmailSessionProps storeConfig) {
        this.transportConfig = transportConfig;
        this.storeConfig = storeConfig;
        // 校验配置
        if (EmailConfigUtil.validSessionConfig(transportConfig) && EmailConfigUtil.validSessionConfig(storeConfig)) {
            init();
        }
    }

    /**
     * 初始化方法
     */
    public void init() {
        transportConfig.init();
        storeConfig.init();
        this.transportSession = EmailConfigUtil.getSession(transportConfig.getProps(), transportConfig.getAuth(), true);
        this.storeSession = EmailConfigUtil.getSession(storeConfig.getProps(), storeConfig.getAuth(), true);
    }

    public Store getStore() throws NoSuchProviderException {
        if (!isInit) {
            init();
            this.isInit = true;
        }
        return EmailConfigUtil.getStore(storeSession, storeConfig.getUrlName());
    }

    public Transport getTransport() throws NoSuchProviderException {
        if (!isInit) {
            init();
            this.isInit = true;
        }
        return EmailConfigUtil.getTransport(transportSession, transportConfig.getUrlName());
    }

    public Session getStoreSession() {
        if (!isInit) {
            init();
            this.isInit = true;
        }
        return this.storeSession;
    }

    public Session getTransportSession() {
        if (!isInit) {
            init();
            this.isInit = true;
        }
        return this.transportSession;
    }

    public EmailSessionProps getStoreConfig() {
        return this.storeConfig;
    }

    public EmailSessionProps getTransportConfig() {
        return this.transportConfig;
    }

    public Folder getCacheFolder() {
        return cacheFolder;
    }

    public void setCacheFolder(Folder cacheFolder) {
        this.cacheFolder = cacheFolder;
    }
}
