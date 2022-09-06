package practice.zhuangzg.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: ZhuangZG
 * @date 2022/9/5 20:51
 * @Description:
 */
@SpringBootApplication
@EntityScan("practice.zhuangzg.todo.core.bean")
@EnableJpaRepositories({"practice.zhuangzg.todo.core.repository"})
@ComponentScan(basePackages = {"practice.zhuangzg.todo"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
