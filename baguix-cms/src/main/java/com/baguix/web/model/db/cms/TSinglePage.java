/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/12/12.
 */
package com.baguix.web.model.db.cms;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * <b>单页文章</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Entity
@DiscriminatorValue("single")
public class TSinglePage extends TArticle {
    //支持5张缩略图
    private String thumbfile3;
    private String thumbfile4;


    @Lob
    @Column(name = "ARTICLE_THUMBFILE3", length = 2000)

    public String getThumbfile3() {
        return thumbfile3;
    }

    public void setThumbfile3(String thumbfile3) {
        this.thumbfile3 = thumbfile3;
    }
    @Lob
    @Column(name = "ARTICLE_THUMBFILE4", length = 2000)

    public String getThumbfile4() {
        return thumbfile4;
    }

    public void setThumbfile4(String thumbfile4) {
        this.thumbfile4 = thumbfile4;
    }
}
