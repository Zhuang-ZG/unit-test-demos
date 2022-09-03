package practice.zhuangzg.todo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice.zhuangzg.todo.bean.TodoItem;
import practice.zhuangzg.todo.bean.TodoItemIndexParameter;
import practice.zhuangzg.todo.bean.TodoParameter;
import practice.zhuangzg.todo.repository.TodoItemRepository;
import practice.zhuangzg.todo.service.TodoItemService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
class TodoItemServiceImplTest {

    private TodoItemRepository repository;
    private TodoItemService service;

    @BeforeEach
    public void setUp() {
        repository = mock(TodoItemRepository.class);
        service = new TodoItemItemServiceImpl(repository);
    }

    @Test
    public void should_add_todo_item() {
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem item = service.addToDoItem(TodoParameter.of("foo"));
        assertThat(item.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_throw_null_exception() {
        when(repository.save(any())).then(returnsFirstArg());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addToDoItem(TodoParameter.of(null)));
    }

    @Test
    public void should_mark_todo_item_as_done() {
        when(repository.save(any())).then(returnsFirstArg());
        when(repository.findAll()).thenReturn(List.of(new TodoItem("foo")));
        Optional<TodoItem> todoItem = service.markTodoItemDone(TodoItemIndexParameter.of(1));
        assertThat(todoItem).isPresent();
        final TodoItem actual = todoItem.get();
        assertThat(actual.isDone()).isTrue();
    }

    @Test
    public void should_throw_exception_for_negative_index() {
        when(repository.findAll())
                .thenReturn(List.of(new TodoItem("foo")));
        when(repository.save(any())).then(returnsFirstArg());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.markTodoItemDone(TodoItemIndexParameter.of(-1)));
    }

    @Test
    public void should_not_mark_as_done_for_out_of_scope_index() {
        when(repository.findAll())
                .thenReturn(List.of(new TodoItem("foo")));
        when(repository.save(any())).then(returnsFirstArg());
        final Optional<TodoItem> todoItem = service.markTodoItemDone(TodoItemIndexParameter.of(2));
        assertThat(todoItem).isEmpty();
    }

    @Test
    public void should_list_all() {
        when(repository.findAll())
                .thenReturn(List.of(new TodoItem("foo")));
        List<TodoItem> todoItems = service.listAll(true);
        assertThat(todoItems).hasSize(1);
    }

    @Test
    public void should_not_list_without_item() {
        when(repository.findAll())
                .thenReturn(Collections.emptyList());
        List<TodoItem> todoItems = service.listAll(true);
        assertThat(todoItems).hasSize(0);
    }

    @Test
    public void should_list_all_without_done() {
        final TodoItem a = new TodoItem("A");
        a.markDone();
        final TodoItem b = new TodoItem("B");
        when(repository.findAll())
                .thenReturn(List.of(a, b));
        List<TodoItem> todoItems = service.listAll(false);
        assertThat(todoItems).hasSize(1);
    }

    @Test
    public void should_not_list_without_done_item() {
        final TodoItem doneItem = new TodoItem("foo");
        doneItem.markDone();
        when(repository.findAll()).thenReturn(List.of(doneItem));

        List<TodoItem> items = service.listAll(false);
        assertThat(items).hasSize(0);
    }

}