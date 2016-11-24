package com.baguix.web.model.db.exam;

import com.baguix.web.model.db.core.ECommonField;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Description：Database model: 单选题
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_EXAM_SELECT", schema = "")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="javaClassName")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TSelect extends TAbsQuestion implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 选项
    private List<TOption> option;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name="QUESTION_ID", insertable = false )
    public List<TOption> getOption() {
        return option;
    }

    public void setOption(List<TOption> option) {
        this.option = option;
    }
}
