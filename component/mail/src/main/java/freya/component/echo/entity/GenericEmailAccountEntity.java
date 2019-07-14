package freya.component.echo.entity;

import java.time.Instant;

public class GenericEmailAccountEntity {
    private String accountId;
    private String account;
    private String password;
    private String sendProtocol;
    private String sendHost;
    private Integer sendPort;
    private Integer sendAuthType;
    private String receiveProtocol;
    private String receiveHost;
    private Integer receivePort;
    private Integer receiveAuthType;
    private String tenantId;
    private Instant createTime;
    private String createBy;
    private Instant updateTime;
    private String updateBy;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSendProtocol() {
        return sendProtocol;
    }

    public void setSendProtocol(String sendProtocol) {
        this.sendProtocol = sendProtocol == null ? null : sendProtocol.trim();
    }

    public String getSendHost() {
        return sendHost;
    }

    public void setSendHost(String sendHost) {
        this.sendHost = sendHost == null ? null : sendHost.trim();
    }

    public Integer getSendPort() {
        return sendPort;
    }

    public void setSendPort(Integer sendPort) {
        this.sendPort = sendPort;
    }

    public Integer getSendAuthType() {
        return sendAuthType;
    }

    public void setSendAuthType(Integer sendAuthType) {
        this.sendAuthType = sendAuthType;
    }

    public String getReceiveProtocol() {
        return receiveProtocol;
    }

    public void setReceiveProtocol(String receiveProtocol) {
        this.receiveProtocol = receiveProtocol == null ? null : receiveProtocol.trim();
    }

    public String getReceiveHost() {
        return receiveHost;
    }

    public void setReceiveHost(String receiveHost) {
        this.receiveHost = receiveHost == null ? null : receiveHost.trim();
    }

    public Integer getReceivePort() {
        return receivePort;
    }

    public void setReceivePort(Integer receivePort) {
        this.receivePort = receivePort;
    }

    public Integer getReceiveAuthType() {
        return receiveAuthType;
    }

    public void setReceiveAuthType(Integer receiveAuthType) {
        this.receiveAuthType = receiveAuthType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}