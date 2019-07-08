package freya.component.lika;

import com.alibaba.fastjson.JSON;
import freya.component.lika.pojo.Expression;
import freya.component.lika.util.ExpressionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

public class SpringTest {
    @Test
    public void insert() {
        String sd = "$name{constraint:list(`=kl`,'',12)}";
        ExpressionUtil.analyzeStr(sd);
    }

    @Test
    public void test() {
        // json 映射类名
        String jsonStr = "{\n" +
                "\t\"账号\":\"account\",\n" +
                "\t\"密码\":\"password\",\n" +
                "\t\"角色\":\"roleName\",\n" +
                "\t\"状态\":\"enable\",\n" +
                "\t\"公司\":\"companyName\",\n" +
                "\t\"部门\":\"department\",\n" +
                "\t\"职务\":\"positionName\",\n" +
                "\t\"入职日期\":\"hireDate\",\n" +
                "\t\"合同到期日期\":\"expireDate\",\n" +
                "\t\"直属上级\":\"parentUser\",\n" +
                "\t\"姓名\":\"name\",\n" +
                "\t\"性别\":\"gender\",\n" +
                "\t\"身份证号\":\"identityCard\",\n" +
                "\t\"电话号码\":\"phone\",\n" +
                "\t\"民族\":\"nationality\",\n" +
                "\t\"婚姻状态\":\"maritalStatus\",\n" +
                "\t\"政治面貌\":\"politicalStatus\",\n" +
                "\t\"WBS\":\"wbs\",\n" +
                "\t\"成本中心\":\"costCenter\",\n" +
                "\t\"奖金\":\"bonus\",\n" +
                "\t\"月薪\":\"monthSalary\",\n" +
                "\t\"社保基数\":\"socialInsuranceBase\",\n" +
                "\t\"Billable\":\"billable\",\n" +
                "\t\"备注\":\"comment\"\n" +
                "}";

        Properties properties = JSON.parseObject(jsonStr, Properties.class);
        for (String key : properties.stringPropertyNames()) {
            System.out.println(properties.get(key));
        }
//        Map<String, Method> stringMethodMap = bindGetter(properties, Expression.class);

        return;
    }
}
