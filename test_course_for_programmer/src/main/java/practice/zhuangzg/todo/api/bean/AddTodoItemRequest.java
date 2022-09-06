package practice.zhuangzg.todo.api.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 10:48
 * @Description:
 */
@NoArgsConstructor
public class AddTodoItemRequest {

    @Getter
    @NotBlank
    private String content;

    public AddTodoItemRequest(String content) {
        this.content = content;
    }
}
