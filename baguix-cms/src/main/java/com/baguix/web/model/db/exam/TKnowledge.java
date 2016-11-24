package com.baguix.web.model.db.exam;

import com.baguix.web.model.db.core.ECommonField;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Description：Database model: 知识点
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_EXAM_KNOWLEDGE", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TKnowledge implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;
    // 标识符
    private String id;
    // 标题(如果用easyui的树形tree组件，将标题定义为text比较方便处理)
    private String text;
    // 知识点内容
    private String content;
    // 父亲
    private TKnowledge parent;
    // 儿子
    private Set<TKnowledge> children = new HashSet<TKnowledge>(0);
    // 级别
    private int level;
    // 公共字段
    private ECommonField commonfield;

    // 题目和知识点多对多
    private Set<TQuestionKnowledge> questionKnowledges;

    //Setter && Getter
    @Id
    @Column(name = "KNOWLEDGE_ID", unique = true, length = 36)
    public String getId() {
        if (this.id != null) {
            return this.id;
        }
        return UUID.randomUUID().toString();
    }


    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "KNOWLEDGE_TEXT", length = 200)


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "KNOWLEDGE_CONTENT", length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KNOWLEDGE_PID")
    public TKnowledge getParent() {
        return parent;
    }

    public void setParent(TKnowledge parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    public Set<TKnowledge> getChildren() {
        return children;
    }

    public void setChildren(Set<TKnowledge> children) {
        this.children = children;
    }

    @Column(name = "KNOWLEDGE_LEVEL")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Embedded
    public ECommonField getCommonfield() {
        return commonfield;
    }

    public void setCommonfield(ECommonField commonfield) {
        this.commonfield = commonfield;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "knowledge")
    public Set<TQuestionKnowledge> getQuestionKnowledges() {
        return questionKnowledges;
    }

    public void setQuestionKnowledges(Set<TQuestionKnowledge> questionKnowledges) {
        this.questionKnowledges = questionKnowledges;
    }
}
