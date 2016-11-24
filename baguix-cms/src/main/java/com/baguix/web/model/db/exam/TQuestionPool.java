package com.baguix.web.model.db.exam;

import com.baguix.web.model.db.core.ECommonField;
import com.baguix.web.model.enums.QuestionType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Description：Database model: 试题池（选项等都调乱融入试题内容）
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_EXAM_QUESTIONPOOL", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TQuestionPool implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    // 试题ID
    private String qid;
    // 试题内容（题干+选项（问题））
    private String content;
    // 答案
    private String answer;
    // 知识点
    private String Knowledges;
    // 解析
    private String analysis;
    // 索引号（根据答案项排列生成1、ABCD，2、ACDB，3、ADCB ...）
    private int index;
    // 题型
    private QuestionType type;

    // Setter && Getter
    @Id
    @Column(name = "QUESTIONPOOL_ID", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "QUESTION_ID", length = 36)
    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    @Lob
    @Column(name = "QUESTIONPOOL_CONTENT", length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Lob
    @Column(name = "QUESTIONPOOL_ANSWER", length = 2000)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Lob
    @Column(name = "QUESTIONPOOL_KNOWLEDGES", length = 2000)
    public String getKnowledges() {
        return Knowledges;
    }

    public void setKnowledges(String knowledges) {
        Knowledges = knowledges;
    }

    @Lob
    @Column(name = "QUESTIONPOOL_ANALYSIS", length = 2000)
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }


    @Column(name = "QUESTIONPOOL_INDEX")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TYPE")
    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
