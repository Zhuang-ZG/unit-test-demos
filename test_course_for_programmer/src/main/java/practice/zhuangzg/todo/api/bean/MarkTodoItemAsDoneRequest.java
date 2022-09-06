package practice.zhuangzg.todo.api.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 10:53
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
public class MarkTodoItemAsDoneRequest {
    @Getter
    @NotNull
    private boolean done;

}
