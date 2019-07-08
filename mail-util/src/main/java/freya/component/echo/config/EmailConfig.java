package freya.component.echo.config;

import freya.component.echo.entity.GenericEmailAccountEntity;
import freya.component.echo.mapper.EmailAccountMapper;
import freya.component.echo.pojo.EmailSessionTemplate;
import freya.component.echo.util.EmailConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class EmailConfig {
    @Autowired
    EmailAccountMapper emailAccountMapper;

    @Bean("emailSessionMap")
    public Map<String, EmailSessionTemplate> sessionMap() {
        List<GenericEmailAccountEntity> accountList = emailAccountMapper.selectEmailAccountList();
        HashMap<String, EmailSessionTemplate> sessionMap = new HashMap<>();
        accountList.stream().forEach(account -> {
            EmailSessionTemplate sessionTemplate = EmailConfigUtil.createMailSessionTemplate(account);
            sessionMap.put(account.getAccount(), sessionTemplate);
        });
        return sessionMap;
    }
}
