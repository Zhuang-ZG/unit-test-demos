package practice.zhuangzg.todo.core.bean;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * @author: ZhuangZG
 * @date 2022/9/2 13:58
 * @Description:
 */
public final class TodoParameter {

    @Getter
    private String content;

    private TodoParameter(final String content) {
        this.content = content;
    }

    public static TodoParameter of(final String content) {
        if (StrUtil.isEmpty(content)) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }
        return new TodoParameter(content);
    }
}
