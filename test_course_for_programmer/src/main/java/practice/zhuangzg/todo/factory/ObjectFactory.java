package practice.zhuangzg.todo.factory;

import picocli.CommandLine;
import practice.zhuangzg.todo.bean.FileTodoItemRepository;
import practice.zhuangzg.todo.bean.TodoCommand;
import practice.zhuangzg.todo.repository.TodoItemRepository;
import practice.zhuangzg.todo.service.TodoItemService;
import practice.zhuangzg.todo.service.impl.TodoItemServiceImpl;

import java.io.File;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 15:38
 * @Description:
 */
public class ObjectFactory {
    public CommandLine createCommandLine(final File repositoryFile) {
        return new CommandLine(createTodoCommand(repositoryFile));
    }

    private TodoCommand createTodoCommand(final File repositoryFile) {
        final TodoItemService service = createService(repositoryFile);
        return new TodoCommand(service);
    }

    public TodoItemService createService(final File repositoryFile) {
        final TodoItemRepository repository = new FileTodoItemRepository(repositoryFile);
        return new TodoItemServiceImpl(repository);
    }
}
