package practice.zhuangzg.todo.api.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import practice.zhuangzg.todo.api.bean.BaseResponse;
import practice.zhuangzg.todo.core.bean.TodoItem;
import practice.zhuangzg.todo.core.repository.TodoItemRepository;

import javax.annotation.Resource;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: ZhuangZG
 * @date 2022/9/6 12:55
 * @Description:
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TodoItemControllerTest {

    @Resource
    private MockMvc mockMvc;
    @Resource
    private TodoItemRepository repository;
    @Resource
    private ObjectMapper mapper;
    @Test
    public void should_add_todo_item() throws Exception {
        String param = "{\"content\":\"foo\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/todo-items")
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(repository.findAll())
                .anyMatch(c -> c.getContent().equals("foo"));
    }

    @Test
    public void should_failed_to_add_empty_todo_item() throws Exception {
        String param = "{\"content\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/todo-items")
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_mark_todo_item_as_done() throws Exception {
        TodoItem item = new TodoItem("foo");
        repository.save(item);
        String param = "{\"done\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/" + item.getIndex())
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(repository.findAll())
                .anyMatch(c -> c.getContent().equals("foo") && item.isDone());
    }

    @Test
    public void should_failed_to_mark_todo_item_as_done_with_wrong_content() throws Exception {
        TodoItem item = new TodoItem("foo");
        repository.save(item);
        String param = "{\"done\":\"false\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/" + item.getIndex())
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_failed_to_mark_todo_item_as_done_with_zero_index() throws Exception {
        TodoItem item = new TodoItem("foo");
        repository.save(item);
        String param = "{\"done\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/" + 0)
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_failed_to_mark_todo_item_as_done_with_negative_index() throws Exception {
        TodoItem item = new TodoItem("foo");
        repository.save(item);
        String param = "{\"done\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/" + -1)
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_failed_to_mark_todo_item_as_done_with_unknown_index() throws Exception {
        TodoItem item = new TodoItem("foo");
        repository.save(item);
        String param = "{\"done\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/" + item.getIndex() + 1)
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_list_all_todo_items_with_done() throws Exception {
        repository.save(new TodoItem("foo1"));
        repository.save(updateItem("foo2"));
        repository.save(new TodoItem("foo3"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/todo-items?all=true")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        BaseResponse<Object> stringBaseResponse = mapper.readValue(responseContent, new com.fasterxml.jackson.core.type.TypeReference<BaseResponse<Object>>() {
        });
        Object data = stringBaseResponse.getData();

        List<TodoItem> todoItems = mapper.readValue(JSONUtil.toJsonStr(data), new com.fasterxml.jackson.core.type.TypeReference<List<TodoItem>>() {
        });
        assertThat(todoItems).hasSize(3);
    }

    @Test
    public void should_list_all_todo_items_without_done() throws Exception {
        repository.save(new TodoItem("foo1"));
        repository.save(updateItem("foo2"));
        repository.save(new TodoItem("foo3"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/todo-items")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        BaseResponse<Object> stringBaseResponse = mapper.readValue(responseContent, new com.fasterxml.jackson.core.type.TypeReference<BaseResponse<Object>>() {
        });
        Object data = stringBaseResponse.getData();

        List<TodoItem> todoItems = mapper.readValue(JSONUtil.toJsonStr(data), new com.fasterxml.jackson.core.type.TypeReference<List<TodoItem>>() {
        });
        assertThat(todoItems).hasSize(2);
    }


    private TodoItem updateItem(String content) {
        TodoItem item = new TodoItem(content);
        item.markDone();
        return item;
    }
}