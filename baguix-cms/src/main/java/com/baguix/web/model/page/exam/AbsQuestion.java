package com.baguix.web.model.page.exam;

import com.baguix.web.model.db.exam.TAbsQuestion;
import com.baguix.web.model.db.exam.TSelect;
import com.baguix.web.model.enums.StateType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by Scott on 2016/3/19.
 */
public abstract class AbsQuestion {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;

    // 题干
    private String content;
    // 摘要
    private String summary;
    // 知识点
    private Set<Knowledge> knowledges;
    // 知识点字符串
    private String knowledgestr;
    // 标签
    private String tags;
    // 难度
    private BigDecimal difficulty;
    // 解析
    private String analysis;

    //是否暂删
    private StateType status;
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;

    // 查询
    // 知识点ID
    private String kid;

    // Setter && Getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setKnowledgestr(String knowledgestr) {
        this.knowledgestr = knowledgestr;
    }

    public void setKnowledges(Set<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }

    public Set<Knowledge> getKnowledges() {
        return knowledges;
    }

    public String getKnowledgestr() {
        return knowledgestr;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public BigDecimal getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigDecimal difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public StateType getStatus() {
        return status;
    }

    public void setStatus(StateType status) {
        this.status = status;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    /**
     * 数据库转页面
     */
    public void db2page(TAbsQuestion t, AbsQuestion p) {
        p.setId(t.getId());
        p.setContent(t.getContent());
        p.setSummary(t.getSummary());
        p.setTags(t.getTags());
        p.setDifficulty(t.getDifficulty());
        p.setAnalysis(t.getAnalysis());
        // 知识点
        // 公共字段
        p.setCtime(t.getCommonfield().getCtime());
        p.setMtime(t.getCommonfield().getMtime());
        p.setStatus(t.getCommonfield().getStatus());
        p.setRank(t.getCommonfield().getRank());
    }

    /**
     * 页面转数据库
     */
    public void page2db(AbsQuestion p, TAbsQuestion t) {

    }
}
