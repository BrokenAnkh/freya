package freya.component.echo.pojo;


/**
 * @Description 统一返回结果封装类
 * @Author HQ
 * @Date 2019/5/17 18:16
 */
public class Result<T> {
    private final static Integer SUCCESS = 0;
    private final static Integer WARNING = 1000;
    private final static String BLANK_MESSAGE = "";

    private Integer code;
    private T data;
    private String msg;

    public static <T> Result<T> success(T data) {
        return new Result<>(Result.SUCCESS, data, Result.BLANK_MESSAGE);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(Result.SUCCESS, data, Result.BLANK_MESSAGE);
    }

    public static <T> Result<T> warn(String msg) {
        return new Result<>(Result.WARNING, null, msg);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, null, msg);
    }


    public Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
