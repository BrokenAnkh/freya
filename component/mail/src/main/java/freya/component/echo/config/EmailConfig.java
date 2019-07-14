package freya.component.echo.config;

//@Configuration
//public class EmailConfig {
//    @Autowired
//    EmailAccountMapper emailAccountMapper;
//
//    @Bean("emailSessionMap")
//    public Map<String, EmailSessionTemplate> sessionMap() {
//        List<GenericEmailAccountEntity> accountList = emailAccountMapper.selectEmailAccountList();
//        HashMap<String, EmailSessionTemplate> sessionMap = new HashMap<>();
//        accountList.stream().forEach(account -> {
//            EmailSessionTemplate sessionTemplate = EmailConfigUtil.createMailSessionTemplate(account);
//            sessionMap.put(account.getAccount(), sessionTemplate);
//        });
//        return sessionMap;
//    }
//}
