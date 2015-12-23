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
 * <b>新闻</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Entity
@DiscriminatorValue("news")
public class TNews extends TArticle {
    //支持10张缩略图
    private String thumbfile3;
    private String thumbfile4;
    private String thumbfile5;
    private String thumbfile6;
    private String thumbfile7;
    private String thumbfile8;
    private String thumbfile9;

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
    @Lob
    @Column(name = "ARTICLE_THUMBFILE5", length = 2000)

    public String getThumbfile5() {
        return thumbfile5;
    }

    public void setThumbfile5(String thumbfile5) {
        this.thumbfile5 = thumbfile5;
    }
    @Lob
    @Column(name = "ARTICLE_THUMBFILE6", length = 2000)

    public String getThumbfile6() {
        return thumbfile6;
    }

    public void setThumbfile6(String thumbfile6) {
        this.thumbfile6 = thumbfile6;
    }
    @Lob
    @Column(name = "ARTICLE_THUMBFILE7", length = 2000)

    public String getThumbfile7() {
        return thumbfile7;
    }

    public void setThumbfile7(String thumbfile7) {
        this.thumbfile7 = thumbfile7;
    }
    @Lob
    @Column(name = "ARTICLE_THUMBFILE8", length = 2000)

    public String getThumbfile8() {
        return thumbfile8;
    }

    public void setThumbfile8(String thumbfile8) {
        this.thumbfile8 = thumbfile8;
    }
    @Lob
    @Column(name = "ARTICLE_THUMBFILE9", length = 2000)

    public String getThumbfile9() {
        return thumbfile9;
    }

    public void setThumbfile9(String thumbfile9) {
        this.thumbfile9 = thumbfile9;
    }
}
