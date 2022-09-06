package practice.zhuangzg.todo.api.bean;

import lombok.*;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 11:09
 * @Description:
 */
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> BaseResponse<T> success(T data){
        return BaseResponse.<T>builder()
                .code(ResponseInfoEnum.SUCCESS.getCode())
                .message(ResponseInfoEnum.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> fail(final int code, final String message) {
        return BaseResponse.<T>builder()
                .code(code).message(message).build();
    }
}

