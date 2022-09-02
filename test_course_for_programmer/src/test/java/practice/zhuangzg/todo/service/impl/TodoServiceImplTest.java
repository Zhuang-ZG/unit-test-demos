package practice.zhuangzg.todo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice.zhuangzg.todo.bean.TodoItem;
import practice.zhuangzg.todo.bean.TodoParameter;
import practice.zhuangzg.todo.repository.TodoRepository;
import practice.zhuangzg.todo.service.TodoService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;


/**
 * @author: ZhuangZG
 * @date 2022/9/2 14:05
 * @Description:
 */
@Slf4j
class TodoServiceImplTest {

    private TodoRepository repository;
    private TodoService service;

    @BeforeEach
    public void setUp() {
        repository = mock(TodoRepository.class);
        service = new TodoServiceImpl(repository);
    }

    @Test
    public void should_add_todo_item() {
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem item = service.addToDoItem(new TodoParameter("foo"));
        assertThat(item.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_throw_null_exception() {
        when(repository.save(any())).then(returnsFirstArg());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addToDoItem(null));
    }
}