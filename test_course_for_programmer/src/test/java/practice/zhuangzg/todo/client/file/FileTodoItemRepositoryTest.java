package practice.zhuangzg.todo.client.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import practice.zhuangzg.todo.client.file.FileTodoItemRepository;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.repository.TodoItemRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 12:10
 * @Description:
 */
class FileTodoItemRepositoryTest {

    @TempDir
    File tempDir;
    File tempFile;
    TodoItemRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("file", "", tempDir);
        this.repository = new FileTodoItemRepository(tempFile);
    }

    @Test
    public void should_find_nothing_from_empty_repository () {
        List<TodoItem> all = repository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    public void should_find_saved_items() {
        repository = new FileTodoItemRepository(tempFile);
        repository.save(new TodoItem("foo"));
        repository.save(new TodoItem("bar"));
        final List<TodoItem> items = repository.findAll();
        assertThat(items).hasSize(2);
        final TodoItem firstItem = items.get(0);
        assertThat(firstItem.getContent()).isEqualTo("foo");
        assertThat(firstItem.getIndex()).isEqualTo(1);
        final TodoItem secondItem = items.get(1);
        assertThat(secondItem.getContent()).isEqualTo("bar");
        assertThat(secondItem.getIndex()).isEqualTo(2);
    }

    @Test
    public void should_find_updated_todo_item() {
        repository.save(new TodoItem("A"));
        List<TodoItem> all = repository.findAll();
        TodoItem toUpdate = all.get(0);
        toUpdate.markDone();
        repository.save(toUpdate);
        List<TodoItem> all1 = repository.findAll();
        assertThat(all1.size()).isEqualTo(1);
    }


    @Test
    public void should_not_save_null_todo_item() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> repository.save(null));
    }
}