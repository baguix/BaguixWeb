package com.baguix.web.model.db.core;

import com.baguix.web.model.enums.StateType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * 公共字段
 * Created by Scott on 2016/3/12.
 */

@Embeddable
public class ECommonField {
    //是否暂删
    private StateType status;
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;

    public ECommonField() {
    }


    // Setter && Getter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS")

    public StateType getStatus() {
        return status;
    }

    public void setStatus(StateType status) {
        this.status = status;
    }
    @Column(name = "RANK")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Column(name = "CTIME")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Column(name = "MTIME")
    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}
