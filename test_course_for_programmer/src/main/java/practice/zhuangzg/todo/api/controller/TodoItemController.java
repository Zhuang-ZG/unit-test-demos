package practice.zhuangzg.todo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.zhuangzg.todo.api.bean.AddTodoItemRequest;
import practice.zhuangzg.todo.api.bean.MarkTodoItemAsDoneRequest;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.bean.TodoParameter;
import practice.zhuangzg.todo.core.exceptions.TodoException;
import practice.zhuangzg.todo.core.service.TodoItemService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 10:47
 * @Description:
 */
@RestController
@RequestMapping("/todo-items")
@Valid
public class TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public void addItems(@Valid @RequestBody final AddTodoItemRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new TodoException(result.getAllErrors().get(0).getDefaultMessage());
        }
        String content = request.getContent();
        todoItemService.addTodoItem(TodoParameter.of(content));
    }

    @PutMapping(value = "/{id}")
    public void updateItemsAsDone(@PathVariable final int id, @Valid @RequestBody MarkTodoItemAsDoneRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new TodoException(result.getAllErrors().get(0).getDefaultMessage());
        }

        if (id <= 0) {
            throw new TodoException("index must greater than 0 ");
        }
        if (!request.isDone()) {
            throw new TodoException("parameter done is invalid");
        }

        Optional<TodoItem> todoItem = todoItemService.list(false).stream()
                .filter(c -> c.getIndex() == id)
                .findFirst();
        if (todoItem.isPresent()) {
            todoItem.get().markDone();
        } else {
            throw new TodoException("index is valid");
        }
    }

    @GetMapping
    public List<TodoItem> list(@RequestParam(value = "all", defaultValue = "false") final boolean all) {
        return todoItemService.list(all);
    }
}
