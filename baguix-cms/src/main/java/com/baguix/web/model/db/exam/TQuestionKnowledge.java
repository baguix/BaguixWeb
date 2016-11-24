package com.baguix.web.model.db.exam;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Description：Database model: 知识点
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_EXAM_QUESTIONKNOWLEDGE", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)
public class TQuestionKnowledge implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 48L;
    //标识符
    private String id;
    private TAbsQuestion question;
    private TKnowledge knowledge;

    //Setter && Getter
    @Id
    @Column(name = "QUESTIONKNOWLEDGE_ID", unique = true, length = 36)
    public String getId() {
        if (this.id != null) {
            return this.id;
        }
        return UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    public TAbsQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TAbsQuestion question) {
        this.question = question;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KNOWLEDGE_ID")
    public TKnowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(TKnowledge knowledge) {
        this.knowledge = knowledge;
    }
}
