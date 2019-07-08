package freya.component.echo.controller;

import freya.component.echo.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description Email接口
 * @Author HQ
 * @Date 2019/5/17 16:19
 */
@RestController
@RequestMapping("email")
public class EmailController {

    @GetMapping("send")
    public Result<String> test(@RequestParam String message) {
        return Result.success("你好,中国");
    }
}
