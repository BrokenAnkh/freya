package freya.component.echo.pojo.type;

import freya.component.echo.pojo.basic.BasicEnum;

public enum ProtocolType implements BasicEnum {
    // 发送类型协议
    TRANSPORT("transport", 1),
    // 接收类型协议
    STORE("store", 2);

    private final String name;
    private final Integer value;

    ProtocolType(String name, Integer value) {
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
