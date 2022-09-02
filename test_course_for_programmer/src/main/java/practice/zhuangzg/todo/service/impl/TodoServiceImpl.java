package practice.zhuangzg.todo.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.zhuangzg.todo.bean.TodoItem;
import practice.zhuangzg.todo.bean.TodoParameter;
import practice.zhuangzg.todo.repository.TodoRepository;
import practice.zhuangzg.todo.service.TodoService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 13:57
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoServiceImpl implements TodoService {

    private TodoRepository repository;

    @Override
    public TodoItem addToDoItem(TodoParameter toDoParameter) {
        if (Objects.isNull(toDoParameter)) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }

        TodoItem todoItem = new TodoItem(toDoParameter.getContent());
        return this.repository.save(todoItem);
    }

}
