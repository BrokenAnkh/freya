package freya.component.core.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectUtil {
    public static List<Method> listGetter(Class objClass) {
        List<Method> objGetters = Stream.of(Object.class.getMethods())
                .filter(method -> isAccessibleGetter(method))
                .collect(Collectors.toList());
        List<Method> getters = Stream.of(objClass.getMethods())
                .filter(method -> isAccessibleGetter(method) && !objGetters.contains(method))
                .collect(Collectors.toList());
        return getters;
    }

    public static boolean isAccessibleGetter(Method method) {
        return method != null && method.getName().startsWith("get") && method.getParameters().length == 0 && method.isAccessible() && method.getReturnType() != null;
    }

    public static Map<String, Method> bindGetter(Properties nameMap, Class clazz) {
        HashMap<String, Method> methodMap = new HashMap<>();
        List<Method> getters = listGetter(clazz);
        for (String str : nameMap.stringPropertyNames()) {
            getters.stream()
                    .filter(method -> nameMap.getProperty(str).equals(getPropertyName(method)))
                    .forEach(method -> methodMap.put(str, method));
        }
        return methodMap;
    }

    public static String getPropertyName(Method method) {
        return method == null ? "" : StringUtil.lowerCase(method.getName(), 0, 1);
    }
}
