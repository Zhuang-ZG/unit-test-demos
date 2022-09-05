package practice.zhuangzg.todo.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.io.Serializable;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 13:55
 * @Description:
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TodoItem implements Serializable {
    private long index;
    private String content;
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
    }

    public void assignIndex(final long index) {
        this.index = index;
    }

    public void markDone() {
        this.done = true;
    }
}
