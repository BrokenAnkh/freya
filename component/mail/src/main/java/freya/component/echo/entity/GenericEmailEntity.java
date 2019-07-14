package freya.component.echo.entity;

import java.time.Instant;

public class GenericEmailEntity {
    private String emailId;
    private String ticketId;
    private String uid;
    private String from;
    private String recipient;
    private String ccUser;
    private String bccUser;
    private String subject;
    private String content;
    private String attachment;
    private Instant sendTime;
    private String tenantId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getCcUser() {
        return ccUser;
    }

    public void setCcUser(String ccUser) {
        this.ccUser = ccUser;
    }

    public String getBccUser() {
        return bccUser;
    }

    public void setBccUser(String bccUser) {
        this.bccUser = bccUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Instant getSendTime() {
        return sendTime;
    }

    public void setSendTime(Instant sendTime) {
        this.sendTime = sendTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "GenericEmailEntity{" +
                "uid='" + uid + '\'' +
                ", from='" + from + '\'' +
                ", recipient='" + recipient + '\'' +
                ", ccUser='" + ccUser + '\'' +
                ", bccUser='" + bccUser + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attachment='" + attachment + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
