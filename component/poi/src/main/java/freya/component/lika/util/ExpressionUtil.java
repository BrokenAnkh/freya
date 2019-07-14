package freya.component.lika.util;

import java.util.ArrayList;
import java.util.List;

public class ExpressionUtil {
    private static final char ENTRY_DELIMITER = ':';
    private static final char FORMULA_DELIMITER = '`';
    private static final char STRING_DELIMITER = '\'';
    private static final char EXPRESSION_DELIMITER = ',';
    private static final char EXPRESSION_START_DELIMITER = '{';
    private static final char EXPRESSION_END_DELIMITER = '}';

    private static final char ESCAPE_CHARACTER = '\\';

    public static void analyzeStr(String expression) {
        char temp = ' ';
        List<String> identityList = new ArrayList();
        List<String> valueList = new ArrayList();

        String executeStr = "";

        int startIndex = 0;
        char[] chars = expression.toCharArray();
        for (int i = 0; i < expression.length(); i++) {
            if (isDelimiter(chars[i], temp)) {
                String substring = expression.substring(startIndex, i);
                identityList.add(substring);
                startIndex = i + 1;
                executeStr += ("$" + identityList.size() + chars[i]);
            }
            // 缓存上一个字符串
            temp = chars[i];
        }

        if (startIndex != expression.length() + 1) {
            String substring = expression.substring(startIndex);
            identityList.add(expression.substring(startIndex));
            executeStr += ("$" + identityList.size());
        }

        for (String str : identityList) {
            System.out.println(str);
        }

        System.out.println(executeStr);
    }

    public static boolean isDelimiter(char current, char front) {
        boolean flag;

        switch (current) {
            case ENTRY_DELIMITER:
            case FORMULA_DELIMITER:
            case STRING_DELIMITER:
            case EXPRESSION_DELIMITER:
            case EXPRESSION_START_DELIMITER:
            case EXPRESSION_END_DELIMITER:
                flag = front != ESCAPE_CHARACTER;
                break;
            default:
                flag = false;
                break;
        }

        return flag;
    }


}
