package practice.zhuangzg.todo.bean;

import lombok.Getter;

/**
 * @author: ZhuangZG
 * @date 2022/9/3 11:08
 * @Description:
 */
public class TodoItemIndexParameter {

    @Getter
    private Integer index;

    private TodoItemIndexParameter(final Integer index) {
        this.index = index;
    }

    public static TodoItemIndexParameter of(final int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index should greater than zero.");
        }
        return new TodoItemIndexParameter(index);
    }
}
