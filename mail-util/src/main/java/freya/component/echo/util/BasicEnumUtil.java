package freya.component.echo.util;

import freya.component.echo.pojo.basic.BasicEnum;

public class BasicEnumUtil {
    public static <T extends BasicEnum, Enum> T typeOf(Class<T> enumType, Object value) {
        // 获取枚举类的实例
        T[] enums = enumType.getEnumConstants();
        // 获取名称一致的实例
        if (enums != null) {
            for (T ele : enums) {
                if (ele.getValue().equals(value)) {
                    return ele;


                }
            }
            return null;
        } else {
            return null;
        }
    }
}
