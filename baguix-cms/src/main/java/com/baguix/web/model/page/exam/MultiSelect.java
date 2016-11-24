package com.baguix.web.model.page.exam;


import java.util.Set;

/**
 * 多选题
 * Created by Scott on 2016/3/19.
 */
public class MultiSelect extends AbsQuestion {
    // 选项
    private Set<Option> option;

    // Setter && Getter
    public Set<Option> getOption() {
        return option;
    }

    public void setOption(Set<Option> option) {
        this.option = option;
    }
}
