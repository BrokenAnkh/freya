package freya.component.echo.entity;

public class GenericSendEmailEntity {
    private String from;
    private String[] recipient;
    private String[] ccUser;
    private String[] bccUser;
    private String subject;
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getRecipient() {
        return recipient;
    }

    public void setRecipient(String[] recipient) {
        this.recipient = recipient;
    }

    public String[] getCcUser() {
        return ccUser;
    }

    public void setCcUser(String[] ccUser) {
        this.ccUser = ccUser;
    }

    public String[] getBccUser() {
        return bccUser;
    }

    public void setBccUser(String[] bccUser) {
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
}
