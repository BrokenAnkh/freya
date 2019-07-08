package freya.component.echo.mapper;

import freya.component.echo.entity.TemplateEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TemplateMapper {
    @Insert("insert into batch_template (template_id,name,attachment) value (#{templateId},#{name},#{attachment})")
    int insertTemplate(TemplateEntity templateEntity);
}
