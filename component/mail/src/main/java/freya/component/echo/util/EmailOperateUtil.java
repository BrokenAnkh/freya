package freya.component.echo.util;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Folder;
import freya.component.echo.entity.GenericEmailEntity;
import freya.component.echo.entity.GenericSendEmailEntity;
import freya.component.echo.entity.GenericTicketEntity;
import freya.component.echo.pojo.EmailSessionTemplate;
import freya.component.echo.pojo.type.PartType;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class EmailOperateUtil {
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    public static final String TICKET_FOLDER = "BOP_TICKET";
    public static final String RECEIVE_FOLDER = "INBOX";


    public static InternetAddress[] toInternetAddresses(String[] mailAccounts) throws AddressException {
        List<InternetAddress> result = new ArrayList<>();
        if (mailAccounts == null) {
            return null;
        }
        for (String str : mailAccounts) {
            if (str != null) {
                result.add(new InternetAddress(str));
            }
        }
        return result.toArray(new InternetAddress[result.size()]);
    }

    public static boolean sendMessage(GenericSendEmailEntity entity, EmailSessionTemplate sessionTemplate) throws MessagingException {
        // 创建邮件
        MimeMessage message = new MimeMessage(sessionTemplate.getTransportSession());
        // 设置邮件的基本信息
        message.setFrom(new InternetAddress(entity.getFrom()));
        message.addRecipients(Message.RecipientType.TO, toInternetAddresses(entity.getRecipient()));
        message.setRecipients(Message.RecipientType.CC, toInternetAddresses(entity.getCcUser()));
        message.setRecipients(Message.RecipientType.BCC, toInternetAddresses(entity.getBccUser()));
        // 设置subject
        message.setSubject(entity.getSubject());
        //正文
        MimeBodyPart body = new MimeBodyPart();
        body.setContent(entity.getContent(), CONTENT_TYPE);
        MimeMultipart content = new MimeMultipart();
        content.addBodyPart(body);
        message.setContent(content);
        // 保存更改
        message.saveChanges();
        try {
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean sendMessage(GenericSendEmailEntity entity, Multipart content, EmailSessionTemplate sessionTemplate) throws MessagingException {
        // 创建邮件
        MimeMessage message = new MimeMessage(sessionTemplate.getTransportSession());
        // 设置邮件的基本信息
        message.setFrom(new InternetAddress(entity.getFrom()));
        message.addRecipients(Message.RecipientType.TO, toInternetAddresses(entity.getRecipient()));
        message.setRecipients(Message.RecipientType.CC, toInternetAddresses(entity.getCcUser()));
        message.setRecipients(Message.RecipientType.BCC, toInternetAddresses(entity.getBccUser()));
        // 设置subject
        message.setSubject(entity.getSubject());
        //正文
        message.setContent(content);
        // 保存更改
        message.saveChanges();
        try {
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 拉取最新邮件
     */

    public static String fetchMessageUid(Message message, Folder folder) throws MessagingException {
        // 初始化参数,获取uid
        if (folder instanceof POP3Folder) {
            return ((POP3Folder) folder).getUID(message);
        } else if (folder instanceof IMAPFolder) {
            return Long.toString(((IMAPFolder) folder).getUID(message));
        } else {
            return null;
        }
    }

    /**
     * 数据过滤方法
     */

    public static String filterMessageUid(Message msg, Folder folder, List<String> existsUidList) throws MessagingException {
        // 获取uid
        String uid = fetchMessageUid(msg, folder);
        // 筛选已经存在的邮件
        if (uid != null) {
            if (existsUidList != null) {
                boolean isContain = false;
                for (String existsUid : existsUidList) {
                    if (uid.equals(existsUid)) {
                        isContain = true;
                        break;
                    }
                }
                // 删掉已经存在的uid
                if (isContain) {
                    existsUidList.remove(existsUidList);
                    return null;
                } else {
                    return uid;
                }
            }
        } else {
            String messageID = ((MimeMessage) msg).getMessageID();
            uid = fetchMessageUid(msg, folder);
        }
        return null;
    }

    public static GenericTicketEntity filterTicket(Message msg, List<GenericTicketEntity> ticketList) throws MessagingException {
        // 在已知工单里面
        if (ticketList != null) {
            for (GenericTicketEntity ticket : ticketList) {
                if (msg.getSubject().contains(ticket.getTicketNumber())) {
                    return ticket;
                }
            }
        }
        return null;
    }

    /**
     * 邮件数据转换方法
     */

    public static GenericEmailEntity convert(Message message, String uid, GenericTicketEntity ticket) throws MessagingException, IOException {
        GenericEmailEntity emailEntity = new GenericEmailEntity();
        emailEntity.setEmailId(UUID.randomUUID().toString().toLowerCase());
        emailEntity.setTicketId(ticket.getTicketId());
        emailEntity.setUid(uid);
        emailEntity.setFrom(convert(message.getFrom()));
        emailEntity.setRecipient(convert(message.getRecipients(Message.RecipientType.TO)));
        emailEntity.setCcUser(convert(message.getRecipients(Message.RecipientType.CC)));
        emailEntity.setBccUser(convert(message.getRecipients(Message.RecipientType.BCC)));
        emailEntity.setSubject(message.getSubject());
        emailEntity.setSendTime(message.getSentDate().toInstant());
        emailEntity.setTenantId(ticket.getTenantId());
        return emailEntity;
    }

    private static String convert(Address[] addresses) {
        if (addresses == null) {
            return null;
        }
        List<String> addressList = new ArrayList<>();
        for (Address address : addresses) {
            addressList.add(((InternetAddress) address).toUnicodeString());
        }
        return String.join(",", addressList);
    }

    public static String parseContentId(Part part) throws MessagingException {
        String[] headers = part.getHeader("Content-ID");
        if (headers != null && headers.length > 0) {
            String contentId = headers[0];
            if (contentId.startsWith("<") && contentId.endsWith(">")) {
                return "cid:" + contentId.substring(1, contentId.length() - 1);
            } else {
                return "cid:" + contentId;
            }
        }
        return null;
    }


    public static Map<PartType, List<Part>> filter(Message msg) throws MessagingException, IOException {
        HashMap<PartType, List<Part>> collect = new HashMap<>();
        for (PartType type : PartType.values()) {
            collect.put(type, new ArrayList<>());
        }
        filter(msg, false, collect);
        return collect;
    }

    public static void filter(Part part, boolean isRelated, Map<PartType, List<Part>> collect) throws MessagingException, IOException {
        if (part.isMimeType("message/rfc822")) {
            filter((Part) part.getContent(), isRelated, collect);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            //解析multipart类型
            if (part.isMimeType("multipart/related")) {
                for (int i = 0; i < multipart.getCount(); i++) {
                    filter(multipart.getBodyPart(i), true, collect);
                }
            } else {
                for (int i = 0; i < multipart.getCount(); i++) {
                    filter(multipart.getBodyPart(i), isRelated, collect);
                }
            }
        } else {
            PartType parseType = parseType(part, isRelated);
            if (collect.get(parseType) == null) {
                collect.put(parseType, new ArrayList<>());
            }
            collect.get(parseType).add(part);
        }
    }

    public static PartType parseType(Part part, boolean isRelated) throws MessagingException {
        // 判断依据
        String disposition = part.getDisposition();
        String contentType = part.getContentType();
        // 根据disposition判断
        if (disposition != null) {
            if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
                return PartType.ATTACHMENT;
            } else if (disposition.equalsIgnoreCase(Part.INLINE)) {
                return PartType.RELATED;
            }
        }
        // 根据contentType判断
        if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
            return isRelated ? PartType.RELATED : PartType.ATTACHMENT;
        }
        // 根据mimeType判断
        if (part.isMimeType("text/*")) {
            return PartType.TEXT;
        }
        return null;
    }

    /**
     * 字符串解码
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        if (str == null || "".equals(str)) {
            return "";
        } else {
            return MimeUtility.decodeText(str);
        }
    }
}
