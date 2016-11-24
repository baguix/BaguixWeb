package com.baguix.web.model.page.exam;


import com.baguix.web.model.db.core.ECommonField;
import com.baguix.web.model.db.exam.TQuestionKnowledge;
import com.baguix.web.model.db.exam.TSelect;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 单选题
 * Created by Scott on 2016/3/19.
 */
public class Select extends AbsQuestion {
    // 选项
    private List<Option> option;
    private String optionstr;

    // Setter && Getter

    public List<Option> getOption() {
        return option;
    }

    public void setOption(List<Option> option) {
        this.option = option;
    }

    public String getOptionstr() {
        return optionstr;
    }

    public void setOptionstr(String optionstr) {
        this.optionstr = optionstr;
    }

    /**
     * 数据库转页面
     */
    public void db2page(TSelect t, Select p){
        super.db2page(t, p);
    }

    /**
     * 页面转数据库
     */
    public void page2db(Select p, TSelect t){

    }
}
