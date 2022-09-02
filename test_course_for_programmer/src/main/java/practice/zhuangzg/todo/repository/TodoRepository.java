package practice.zhuangzg.todo.repository;

import practice.zhuangzg.todo.bean.TodoItem;

import java.util.List;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 14:00
 * @Description:
 */
public interface TodoRepository {

    public TodoItem save(TodoItem item) ;

}
