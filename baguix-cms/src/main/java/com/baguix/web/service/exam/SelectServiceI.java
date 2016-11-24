package com.baguix.web.service.exam;

import com.baguix.web.model.db.exam.TSelect;
import com.baguix.web.model.page.exam.Option;
import com.baguix.web.model.page.exam.Select;
import com.baguix.web.service.BaseServiceI;

import java.util.List;

/**
 * Created by Scott on 2016/3/28.
 */
public interface SelectServiceI extends BaseServiceI<TSelect> {
    public Select add(Select question);
    public Select edit(Select question);
    public Select getSelect(String id);
    public List<Option> getOptions(String id);
    // 彻底删
    public void remove(String id);
    // 临时删
    public void del(String id);
    public List<Select> listAll(String kid);
    // 查看题库池中的一道题的题干
    public String viewQPContent(String id, int index);
}
