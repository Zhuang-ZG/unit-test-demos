package practice.zhuangzg.todo.client.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.exceptions.TodoException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 14:07
 * @Description:
 */
public class JsonUtil {

    private final static TypeFactory TYPE_FACTORY = TypeFactory.defaultInstance();
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static List<TodoItem> getObjects(File file) {
        final CollectionType type = TYPE_FACTORY.constructCollectionType(ArrayList.class, TodoItem.class);
        try {
            return OBJECT_MAPPER.readValue(file, type);
        } catch (Exception e) {
            throw new TodoException("failed to read objects", e);
        }
    }

    public static void putObjects(final File file,final List<TodoItem> todoItems) {
        try {
            OBJECT_MAPPER.writeValue(file, todoItems);
        } catch (Exception e) {
            throw new TodoException("failed to write objects", e);
        }
    }
}
