package com.baguix.web.model.page.exam;

import java.util.Set;

/**
 * 判断题
 * Created by Scott on 2016/3/19.
 */
public class TureFalse {
    private Set<Option> options;

    // Setter && Getter
    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
