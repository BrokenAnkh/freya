package freya.component.echo.pojo.type;

import freya.component.echo.pojo.basic.BasicEnum;

/**
 * @Description 邮件内容的类型
 * @Author HQ
 * @Date 2019/6/6 16:09
 */
public enum PartType implements BasicEnum {
    // 文本类型
    TEXT("text", 1),
    // 包含在文本内,与文本关联的附件
    RELATED("related", 1),
    // 附件
    ATTACHMENT("attachment", 2);

    private final String name;
    private final Integer value;

    PartType(String name, Integer value) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
