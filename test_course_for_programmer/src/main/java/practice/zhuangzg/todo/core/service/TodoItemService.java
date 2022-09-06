package practice.zhuangzg.todo.core.service;

import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.bean.TodoItemIndexParameter;
import practice.zhuangzg.todo.core.bean.TodoParameter;

import java.util.List;
import java.util.Optional;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 13:56
 * @Description:
 */
public interface TodoItemService {

    /**
     * add new todoItem
     * @param toDoParameter
     * @return
     */
    TodoItem addTodoItem(TodoParameter toDoParameter);

    Optional<TodoItem> markTodoItemDone(TodoItemIndexParameter index);

    List<TodoItem> list(boolean all);
}
