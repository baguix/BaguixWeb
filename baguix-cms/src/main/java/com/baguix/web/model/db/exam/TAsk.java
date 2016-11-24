package com.baguix.web.model.db.exam;

import com.baguix.web.model.enums.StateType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description：Database model: 问题项
 * 填空题则是: 1空、2空、3空
 * 问答题则是: 1小题，2小题，3小题
 * @author Scott
 */

@Entity
@Table(name = "SS_EXAM_ASK", schema = "")

//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="javaClassName")
@DynamicInsert(true)
@DynamicUpdate(true)

public class TAsk implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    //内容
    private String content;
    //答案
    private String selected;

    // Setter && Getter
    @Id
    @Column(name = "QUESTION_ID", unique = true, nullable = false, length = 36)
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
    @Column(name = "OPTION_selected", length = 2000)
    public String getselected() {
        return selected;
    }

    public void setselected(String selected) {
        this.selected = selected;
    }
}
