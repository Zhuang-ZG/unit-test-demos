package practice.zhuangzg.todo.client;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 15:43
 * @Description:
 */

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.bean.TodoItemIndexParameter;
import practice.zhuangzg.todo.core.bean.TodoParameter;
import practice.zhuangzg.todo.core.service.TodoItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@CommandLine.Command(name = "todo")
public class TodoCommand {
    private final TodoItemService service;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    public TodoCommand(final TodoItemService service) {
        this.service = service;
    }

    @CommandLine.Command(name = "add")
    public int add(@CommandLine.Parameters(index = "0") final String item) {
        if (StrUtil.isEmpty(item)) {
            throw new CommandLine.ParameterException(spec.commandLine(), "empty item is not allowed");
        }

        final TodoItem todoItem = this.service.addTodoItem(TodoParameter.of(item));
        log.info("{}, {}", todoItem.getIndex(), todoItem.getContent());
        log.info("Item <{}> added%n", todoItem.getIndex());

        return 0;
    }

    @CommandLine.Command(name = "done")
    public int markAsDone(@CommandLine.Parameters(index = "0") final int index) {
        if (index <= 0) {
            throw new CommandLine.ParameterException(spec.commandLine(), "index should be greater than 0");
        }

        final Optional<TodoItem> item = this.service.markTodoItemDone(TodoItemIndexParameter.of(index));
        if (item.isEmpty()) {
            throw new CommandLine.ParameterException(spec.commandLine(), "unknown todo item index");
        }

        final TodoItem actual = item.get();
        log.info("Item <{}> done%n", actual.getIndex());
        return 0;
    }

    @CommandLine.Command(name = "list")
    public int list(@CommandLine.Option(names = "--all") final boolean all) {
        final List<TodoItem> items = this.service.list(all);

        items.stream()
                .map(this::formatItem)
                .forEach(log::info);

        if (all) {
            final long doneCount = items.stream()
                    .filter(TodoItem::isDone)
                    .count();

            log.info(formatTotal(items.size(), doneCount));
            return 0;
        }

        log.info(formatTotal(items.size()));
        return 0;
    }

    private String formatTotal(final int size, final long doneCount) {
        return "Total: "
                + size + unit(size) + ", "
                + doneCount + unit(doneCount);
    }

    private String formatTotal(final int size) {
        return "Total: " + size + unit(size);
    }

    private String unit(final long count) {
        if (count > 1) {
            return " items";
        }
        return " item";
    }

    private String formatItem(final TodoItem todoItem) {
        if (todoItem.isDone()) {
            return String.format("%d. [done] %s", todoItem.getIndex(), todoItem.getContent());
        }

        return String.format("%d. %s", todoItem.getIndex(), todoItem.getContent());
    }
}

