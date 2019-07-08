package freya.component.echo.mapper;

import freya.component.echo.entity.GenericEmailEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Table ticket_email
 * @Author HQ
 * @Date 2019/5/31 11:42
 */
@Mapper
@Component
public interface EmailMapper {
    @Insert("<script> \n" +
            "    insert into ticket_email (\n" +
            "        email_id,ticket_id,uid,`from`,recipient,cc_user,bcc_user,`subject`,content,attachment,send_time,tenant_id\n" +
            "    )values\n" +
            "    <foreach collection =\"list\" item=\"item\" index= \"index\" separator =\",\">\n" +
            "    (\n" +
            "        #{item.emailId},#{item.ticketId},#{item.uid},#{item.from},#{item.recipient},#{item.ccUser},#{item.bccUser},#{item.subject},#{item.content},#{item.attachment},#{item.sendTime},#{item.tenantId}\n" +
            "    )\n" +
            "    </foreach>\n" +
            "</script>")
    int insertEmailEntity(List<GenericEmailEntity> genericEmailEntityList);

    @Select("select email_id from ticket_email")
    List<String> selectEmailId();
}
