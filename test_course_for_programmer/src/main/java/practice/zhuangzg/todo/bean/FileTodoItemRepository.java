package practice.zhuangzg.todo.bean;

import cn.hutool.core.io.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.zhuangzg.todo.repository.TodoItemRepository;
import practice.zhuangzg.todo.util.JsonUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 12:10
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
public class FileTodoItemRepository implements TodoItemRepository {

    @Getter
    private File file;

    @Override
    public TodoItem save(TodoItem todoItem) {
        if (Objects.isNull(todoItem)) {
            throw new IllegalArgumentException("todo item should not be null");
        }
        List<TodoItem> todoItems = this.findAll();
        final List<TodoItem> newContent = toSaveContent(todoItem, todoItems);

        JsonUtil.putObjects(file, newContent);
        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {
        if (this.file.length()==0) {
            return new ArrayList<>();
        }
        return JsonUtil.getObjects(file);
    }

    private List<TodoItem> toSaveContent(final TodoItem newItem, final List<TodoItem> items) {
        if (newItem.getIndex() == 0) {
            long newIndex = items.size() + 1;
            newItem.assignIndex(newIndex);

            items.add(newItem);
            return items;
        }

        return items.stream()
                .map(item -> updateItem(newItem, item))
                .collect(Collectors.toList());
    }

    private TodoItem updateItem(final TodoItem newItem, final TodoItem item) {
        if (item.getIndex() == newItem.getIndex()) {
            return newItem;
        }

        return item;
    }
}
