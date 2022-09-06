package practice.zhuangzg.todo.api.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import practice.zhuangzg.todo.api.bean.BaseResponse;

import javax.annotation.Resource;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 11:10
 * @Description:
 */
@RestControllerAdvice
public class ResponseAop implements ResponseBodyAdvice<Object> {

    @Resource
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return objectMapper.writeValueAsString(BaseResponse.success(body));
        }
        if (body instanceof BaseResponse) {
            return body;
        }
        return BaseResponse.success(body);
    }
}
