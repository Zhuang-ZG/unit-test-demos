package practice.zhuangzg.todo.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZhuangZG
 * @date 2022/9/7 10:51
 * @Description:
 */
@Configuration
public class TodoItemApiConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
