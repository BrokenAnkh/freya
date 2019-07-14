package freya.component.echo.pojo.type;

/**
 * @Description 安全协议验证类型
 * @Author HQ
 * @Date 2019/6/6 16:09
 */
public enum AuthType implements BasicEnum {
    // 无校验类型
    NONE("None", 1),
    // SSL校验类型
    SSL("SSL", 2),
    // TLS校验类型
    TLS("TLS", 3);

    private final String name;
    private final Integer value;

    AuthType(String name, Integer value) {
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
