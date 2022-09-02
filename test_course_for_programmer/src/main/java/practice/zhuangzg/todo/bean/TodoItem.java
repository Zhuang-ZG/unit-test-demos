package practice.zhuangzg.todo.bean;

import lombok.*;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 13:55
 * @Description:
 */
@Getter
@EqualsAndHashCode
public class TodoItem {
    private long index;
    private final String content;
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
