package freya.component.echo.mapper;

import freya.component.echo.entity.GenericEmailAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Table ticket_email_account
 * @Author HQ
 * @Date 2019/5/31 11:41
 */
@Component
@Mapper
public interface EmailAccountMapper {

    /**
     * 查询所有的邮箱账户
     *
     * @return
     */
    @Results({
            @Result(column = "account_id", property = "accountId"),
            @Result(column = "account", property = "account"),
            @Result(column = "password", property = "password"),
            @Result(column = "send_protocol", property = "sendProtocol"),
            @Result(column = "send_host", property = "sendHost"),
            @Result(column = "send_port", property = "sendPort"),
            @Result(column = "send_auth_type", property = "sendAuthType"),
            @Result(column = "receive_protocol", property = "receiveProtocol"),
            @Result(column = "receive_host", property = "receiveHost"),
            @Result(column = "receive_port", property = "receivePort"),
            @Result(column = "receive_auth_type", property = "receiveAuthType"),
            @Result(column = "tenant_id", property = "tenantId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_by", property = "createBy"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "update_by", property = "updateBy")
    })
    @Select("select \n" +
            "    account_id, account, password, \n" +
            "    send_protocol, send_host, send_port, send_auth_type,\n" +
            "    receive_protocol, receive_host, receive_port, receive_auth_type, \n" +
            "    tenant_id, create_time, create_by, update_time, update_by\n" +
            "from ticket_email_account")
    List<GenericEmailAccountEntity> selectEmailAccountList();
}
