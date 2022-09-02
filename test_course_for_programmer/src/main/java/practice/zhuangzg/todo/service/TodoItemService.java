package practice.zhuangzg.todo.service;

import practice.zhuangzg.todo.bean.TodoItem;
import practice.zhuangzg.todo.bean.TodoParameter;

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
    TodoItem addToDoItem(TodoParameter toDoParameter);

    Optional<TodoItem> markTodoItemDone(int index);

    List<TodoItem> listAll(boolean all);
}