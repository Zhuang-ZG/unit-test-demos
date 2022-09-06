package practice.zhuangzg.todo.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import practice.zhuangzg.todo.api.bean.BaseResponse;
import practice.zhuangzg.todo.api.bean.ResponseInfoEnum;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 11:24
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return BaseResponse.fail(ResponseInfoEnum.PARAM_INVALID.getCode(), e.getMessage());
    }

}
