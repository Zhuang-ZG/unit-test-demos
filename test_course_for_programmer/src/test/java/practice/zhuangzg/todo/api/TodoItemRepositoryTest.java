package practice.zhuangzg.todo.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.repository.TodoItemRepository;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


/**
 * @author: ZhuangZG
 * @date 2022/9/6 8:37
 * @Description:
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoItemRepositoryTest {

    @Resource
    TodoItemRepository repository;

    @Test
    public void should_find_nothing_from_empty_repository() {
        List<TodoItem> todoItems = repository.findAll();
        assertThat(todoItems).hasSize(0);
    }

    @Test
    public void should_find_saved_todo_items() {
        repository.save(new TodoItem("foo1"));
        repository.save(new TodoItem("foo2"));
        final List<TodoItem> todoItems = repository.findAll();
        assertThat(todoItems).hasSize(2);
        TodoItem todoItem1 = todoItems.get(0);
        assertThat(todoItem1.getContent()).isEqualTo("foo1");
        TodoItem todoItem2 = todoItems.get(1);
        assertThat(todoItem2.getContent()).isEqualTo("foo2");
        assertThat(todoItem1.getIndex()+1).isEqualTo(todoItem2.getIndex());
    }

    @Test
    public void should_throw_exception_while_add_empty_todo_item() {
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> repository.save(new TodoItem(null)));
    }

    @Test
    public void should_update_todo_item_as_done() {
        repository.save(new TodoItem("foo1"));
        repository.save(new TodoItem("foo2"));
        List<TodoItem> todoItems = repository.findAll();
        TodoItem todoItem = todoItems.get(0);
        todoItem.markDone();

        repository.save(todoItem);
        todoItems = repository.findAll();
        todoItem = todoItems.get(0);

        assertThat(todoItems).hasSize(2);
        assertThat(todoItem.isDone()).isTrue();
    }




}