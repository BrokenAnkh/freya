package freya.component.echo.mapper;

import freya.component.echo.entity.GenericTicketEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TicketMapper {
    @Results(id = "genericTicketEntity",
            value = {
                    @Result(column = "ticket_id", property = "ticketId"),
                    @Result(column = "ticket_number", property = "ticketNumber"),
                    @Result(column = "tenant_id", property = "tenantId")
            })
    @Select("select ticket_id,ticket_number,tenant_id from ticket_ticket")
    List<GenericTicketEntity> selectTicketList();
}
