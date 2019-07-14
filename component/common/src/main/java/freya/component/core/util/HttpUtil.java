package freya.component.core.util;

import java.util.Properties;

public class HttpUtil {
    /**
     * 解析请求参数
     *
     * @param httpUrl
     * @return
     */
    public static Properties parseUrlParam(String httpUrl) {
        Properties result = new Properties();
        // 分割参数
        if (httpUrl != null && httpUrl.contains("?")) {
            httpUrl.trim();
            String[] expressions = httpUrl.substring(httpUrl.indexOf('?') + 1).split("[&]");
            for (String expression : expressions) {
                String[] entry = expression.split("[=]");
                result.setProperty(entry[0], entry.length > 1 ? entry[1] : "");
            }
        }
        return result;
    }
}
