package com.baguix.web.model.db.exam;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description：Database model: 判断题或选择题选项
 * 抽象的内容
 * 选择题则是: A、B、C、D等选项
 * 判断题则是: 是、否选项
 * @author Scott
 */

@Entity
@Table(name = "SS_EXAM_OPTION", schema = "")

//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="javaClassName")
@DynamicInsert(true)
@DynamicUpdate(true)

public class TOption implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    // 选项文字/内容
    private String content;
    // 选项纯文本摘要
    private String summary;
    // 是否答案
    private boolean selected;

    // Setter && Getter
    @Id
    @Column(name = "OPTION_ID", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Lob
    @Column(name = "OPTION_CONTENT", length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Lob
    @Column(name = "OPTION_SUMMARY", length = 200)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(name = "OPTION_SELECTED")
    @org.hibernate.annotations.Type(type="yes_no")
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
