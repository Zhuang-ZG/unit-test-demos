package practice.zhuangzg.todo.core.exceptions;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 12:35
 * @Description:
 */
public class TodoException extends RuntimeException {

    public TodoException(String message) {
        super(message);
    }

    public TodoException(String message, Throwable cause) {
        super(message, cause);
    }
}
