package com.baguix.web.model.db.exam;

import com.baguix.web.model.db.core.ECommonField;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Description：Database model: 试题
 *
 * @author Scott
 */

@Entity
//继承影射，每个实体类建立一个独立表
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

@DynamicInsert(true)
@DynamicUpdate(true)
public abstract class TAbsQuestion implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    // 试题编号
    private String qnumber;
    // 题干
    private String content;
    // 摘要
    private String summary;
    // 知识点
    private Set<TQuestionKnowledge> questionKnowledges;
    // 标签
    private String tags;
    // 难度
    private BigDecimal difficulty;
    // 解析
    private String analysis;
    // 公共字段
    private ECommonField commonfield;


    // Setter && Getter
    @Id
    @Column(name = "QUESTION_ID", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "QUESTION_NUMBER", length = 36)
    public String getQnumber() {
        return qnumber;
    }

    public void setQnumber(String qnumber) {
        this.qnumber = qnumber;
    }

    @Lob
    @Column(name = "QUESTION_CONTENT", length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Lob
    @Column(name = "QUESTION_SUMMARY", length = 200)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    public Set<TQuestionKnowledge> getQuestionKnowledges() {
        return questionKnowledges;
    }

    public void setQuestionKnowledges(Set<TQuestionKnowledge> questionKnowledges) {
        this.questionKnowledges = questionKnowledges;
    }

    @Lob
    @Column(name = "QUESTION_TAGS", length = 2000)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name = "QUESTION_DIFFICULTY")
    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    @Lob
    @Column(name = "QUESTION_ANALYSIS", length = 2000)
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @Embedded
    public ECommonField getCommonfield() {
        return commonfield;
    }

    public void setCommonfield(ECommonField commonfield) {
        this.commonfield = commonfield;
    }
}
