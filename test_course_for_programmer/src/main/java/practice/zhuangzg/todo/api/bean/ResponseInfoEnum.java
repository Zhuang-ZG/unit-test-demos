package practice.zhuangzg.todo.api.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dom4j.io.SAXReader;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 11:12
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ResponseInfoEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    PARAM_INVALID(400, "请求失败"),
    ;

    private final Integer code;
    private final String message;
}
