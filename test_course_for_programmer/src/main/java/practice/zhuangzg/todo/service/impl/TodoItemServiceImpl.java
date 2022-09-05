package practice.zhuangzg.todo.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.zhuangzg.todo.bean.TodoItem;
import practice.zhuangzg.todo.bean.TodoItemIndexParameter;
import practice.zhuangzg.todo.bean.TodoParameter;
import practice.zhuangzg.todo.repository.TodoItemRepository;
import practice.zhuangzg.todo.service.TodoItemService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 13:57
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoItemServiceImpl implements TodoItemService {

    private TodoItemRepository repository;

    @Override
    public TodoItem addTodoItem(TodoParameter toDoParameter) {

        TodoItem todoItem = new TodoItem(toDoParameter.getContent());
        return this.repository.save(todoItem);
    }

    @Override
    public Optional<TodoItem> markTodoItemDone(final TodoItemIndexParameter index) {
        final List<TodoItem> all = repository.findAll();

        try {
            TodoItem todoItem = all.get(index.getIndex() - 1);
            if (Objects.nonNull(todoItem)) {
                todoItem.markDone();
            }
            return Optional.ofNullable(repository.save(todoItem));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<TodoItem> list(boolean all) {
        return repository.findAll().stream()
                .filter(i -> all || !i.isDone())
                .collect(Collectors.toList());
    }
}
