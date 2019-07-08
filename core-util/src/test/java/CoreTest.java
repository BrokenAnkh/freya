import freya.component.core.util.HttpUtil;
import freya.component.core.util.StringUtil;

public class CoreTest {
    public static void main(String... args) {
        System.out.println(HttpUtil.parseUrlParam("http://sdsd?sd&&sd&sd"));
        System.out.println("sds343d".substring(4, 7));
        System.out.println(StringUtil.subString("12121",1,0));
        System.out.println(HttpUtil.parseUrlParam("http://sdsd?sd&&sd&sd"));
    }
}
