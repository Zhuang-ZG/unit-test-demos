package practice.zhuangzg.todo.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.bean.TodoItemIndexParameter;
import practice.zhuangzg.todo.core.bean.TodoParameter;
import practice.zhuangzg.todo.client.factory.ObjectFactory;
import practice.zhuangzg.todo.core.service.TodoItemService;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 15:53
 * @Description:
 */
class TodoCommandTest {

    @TempDir
    File tempDir;
    private TodoItemService service;
    private CommandLine commandLine;

    @BeforeEach
    void setUp() {
        final ObjectFactory factory = new ObjectFactory();
        final File repositoryFile = new File(tempDir, "repository.json");
        this.service = factory.createService(repositoryFile);
        commandLine = factory.createCommandLine(repositoryFile);
    }

    @Test
    public void should_add_todo_item() {
        int execute = commandLine.execute("add", "foo");
        assertThat(execute).isEqualTo(0);
        List<TodoItem> list = service.list(true);
        assertThat(list).hasSize(1);

    }

    @Test
    public void should_failed_to_add_empty_todo_item() {
        assertThat(commandLine.execute("add", ""))
                .isNotEqualTo(0);
    }

    @Test
    public void should_mark_todo_item_as_done() {
        service.addTodoItem(TodoParameter.of("foo"));
        assertThat(commandLine.execute("done", "1")).isEqualTo(0);

        List<TodoItem> list = service.list(true);
        assertThat(list.get(0).isDone()).isTrue();
    }

    @Test
    public void should_failed_to_mark_todo_item_for_illegal_index() {
        assertThat(commandLine.execute("done", "-1")).isNotEqualTo(0);
        assertThat(commandLine.execute("done", "0")).isNotEqualTo(0);
    }

    @Test
    public void should_failed_to_mark_unexist_todo_item() {
        service.addTodoItem(TodoParameter.of("foo"));
        assertThat(commandLine.execute("done", "2")).isNotEqualTo(0);
    }

    @Test
    public void should_find_all_todo_item() {
        service.addTodoItem(TodoParameter.of("foo0"));
        service.addTodoItem(TodoParameter.of("foo1"));
        service.addTodoItem(TodoParameter.of("foo2"));
        service.markTodoItemDone(TodoItemIndexParameter.of(1));
        assertThat(commandLine.execute("list", "--all")).isEqualTo(0);
    }

    @Test
    public void should_find_all_unfinished_todo_item() {
        service.addTodoItem(TodoParameter.of("foo0"));
        service.addTodoItem(TodoParameter.of("foo1"));
        service.addTodoItem(TodoParameter.of("foo2"));
        service.markTodoItemDone(TodoItemIndexParameter.of(1));
        assertThat(commandLine.execute("list")).isEqualTo(0);
    }
}