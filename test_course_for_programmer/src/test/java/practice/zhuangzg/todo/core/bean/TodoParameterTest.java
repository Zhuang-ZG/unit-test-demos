package practice.zhuangzg.todo.core.bean;

import org.junit.jupiter.api.Test;
import practice.zhuangzg.todo.core.bean.TodoParameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 11:21
 * @Description:
 */
class TodoParameterTest {

    @Test
    void should_create_todo_item_parameter() {
        final TodoParameter content = TodoParameter.of("content");
        assertThat(content.getContent()).isEqualTo("content");
    }

    @Test
    void should_throw_exception_for_empty_content() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TodoParameter.of(""));
    }

    @Test
    void should_throw_exception_for_null_content() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TodoParameter.of(null));
    }
}