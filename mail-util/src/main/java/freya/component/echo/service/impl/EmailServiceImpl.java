package freya.component.echo.service.impl;

import freya.component.echo.entity.GenericEmailEntity;
import freya.component.echo.entity.GenericSendEmailEntity;
import freya.component.echo.entity.GenericTicketEntity;
import freya.component.echo.mapper.EmailMapper;
import freya.component.echo.mapper.TicketMapper;
import freya.component.echo.pojo.EmailSessionTemplate;
import freya.component.echo.util.EmailEntityUtil;
import freya.component.echo.util.EmailOperateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl {
    @Autowired
    Map<String, EmailSessionTemplate> sessionMap;
    @Autowired
    EmailMapper emailMapper;
    @Autowired
    TicketMapper ticketMapper;

    public void sendEmail(GenericSendEmailEntity entity) {
        // 校验 entity
        if (EmailEntityUtil.validEmailEntity(entity)) {
            // TODO: 返回信息:邮件信息异常
            return;
        }
        // 获取 sessionTemplate
        EmailSessionTemplate sessionTemplate = sessionMap.get(entity.getFrom());
        if (sessionTemplate == null) {
            // TODO: 返回信息:无权使用这个邮箱
            return;
        }
        // 发送 邮件
        try {
            EmailOperateUtil.sendMessage(entity, sessionTemplate);
        } catch (MessagingException e) {
            // TODO: 返回信息:发送邮件异常
            e.printStackTrace();
        }
    }

    public void receiveEmail(String emailAccount) throws MessagingException, IOException {
        // 测试 接收邮件
        EmailSessionTemplate mailSessionTemplate = sessionMap.get(emailAccount);
        // 连接store
        Store store = mailSessionTemplate.getStore();
        store.connect();
        Folder inbox = null;
        try {
            // 准备参数
            List<String> existsUidList = emailMapper.selectEmailId();
            List<GenericTicketEntity> ticketList = ticketMapper.selectTicketList();
            // 打开文件夹
            inbox = store.getFolder(EmailOperateUtil.RECEIVE_FOLDER);
            inbox.open(Folder.READ_ONLY);
            // 获取空邮件对象
            Message[] messages = inbox.getMessages();
            // 缓存
            List<GenericEmailEntity> temp = new ArrayList<>();
            // 遍历
            for (int i = 0; i < messages.length; i++) {
                // 筛选UID
                String uid = EmailOperateUtil.filterMessageUid(messages[i], inbox, existsUidList);
                if (uid != null) {
                    // 筛选邮件的Ticket
                    GenericTicketEntity ticket = EmailOperateUtil.filterTicket(messages[i], ticketList);
                    if (ticket != null) {
                        temp.add(EmailOperateUtil.convert(messages[i], uid, ticket));
                        // 存储数据
                        if (temp.size() == 200) {
                            emailMapper.insertEmailEntity(temp);
                            temp = new ArrayList<>();
                        }
                    }
                }
                // 清空数据,避免内存堆积
                messages[i] = null;
            }
            if (!temp.isEmpty()) {
                emailMapper.insertEmailEntity(temp);
            }
        } finally {
            if (inbox != null) {
                inbox.close();
            }
            store.close();
        }
    }
}
