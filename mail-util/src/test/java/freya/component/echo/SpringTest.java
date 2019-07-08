package freya.component.echo;

import freya.component.echo.entity.TemplateEntity;
import freya.component.echo.mapper.TemplateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringTest {
    @Autowired
    TemplateMapper templateMapper;


    @Test
    public void test() throws IOException {

        TemplateEntity template = new TemplateEntity();
        template.setTemplateId("reyritfddiuo");
        template.setName("asdsadfda");

        File file = new File("C:\\Users\\lyric\\Desktop\\RedisClient.zip");
        long length = file.length();

        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] temp = new byte[1024];
        int i = 0;
        while ((i = in.read(temp)) != -1) {
            outputStream.write(temp,0,i);
        }

        template.setAttachment(outputStream.toByteArray());

        int i1 = templateMapper.insertTemplate(template);
    }
}
