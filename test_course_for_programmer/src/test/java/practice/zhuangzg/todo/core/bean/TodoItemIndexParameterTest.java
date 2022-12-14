package practice.zhuangzg.todo.core.bean;

import org.junit.jupiter.api.Test;
import practice.zhuangzg.todo.core.bean.TodoItemIndexParameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 11:12
 * @Description:
 */
class TodoItemIndexParameterTest {

    @Test
    public void should_create_todo_item_index_parameter() {
        final TodoItemIndexParameter indexParameter = TodoItemIndexParameter.of(1);
        assertThat(indexParameter.getIndex()).isEqualTo(1);
    }

    @Test
    public void should_throw_exception_for_negative_index() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->TodoItemIndexParameter.of(-1));
    }
}