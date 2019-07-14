package freya.component.echo.entity;

public class TemplateEntity {
    private String templateId;
    private String name;
    private byte[] attachment;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] file) {
        this.attachment = file;
    }
}
