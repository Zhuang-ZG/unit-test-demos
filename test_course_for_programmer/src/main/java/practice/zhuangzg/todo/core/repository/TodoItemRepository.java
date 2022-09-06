package practice.zhuangzg.todo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import practice.zhuangzg.todo.core.bean.TodoItem;

import java.util.List;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 14:00
 * @Description:
 */
public interface TodoItemRepository extends Repository<TodoItem, Long> {

    TodoItem save(TodoItem item) ;

    List<TodoItem> findAll();
}
