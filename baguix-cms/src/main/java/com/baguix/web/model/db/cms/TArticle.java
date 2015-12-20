/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.db.cms;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


/**
 * Description：Database model: 文章
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_ARTICLE", schema = "")
//继承影射，每个实体类建立一个独立表
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "ARTICLE_TYPE",
        length = 50,
        discriminatorType = DiscriminatorType.STRING
)

//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="javaClassName")
@DynamicInsert(true)
@DynamicUpdate(true)

public abstract class TArticle implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 48L;
    //标识符
    private String id;

    //============================自定义字段============================
    //栏目字符串
    private String categoryid;
    //栏目ID列表(35,44,10)
    private String categoryids;
    //栏目名称列表，方便展示(新闻中心-公司活动)
    private String categoryname;
    //来源
    private String source;
    //作者
    private String writer;
    //文章标题
    private String title;
    //标题颜色
    private String titlecolor;
    //外部链接
    private String url;
    //关键词
    private String keyword;
    //摘要
    private String abstracts;
    //内容
    private String content;
    //上传图片列表
    private String upimgstr;
    //上传图片原名列表
    private String img;
    //缩略图文件名
    private String thumbfile;
    //附件名列表
    private String filename;
    //附件文件位置列表
    private String file;
    //分页字数
    private long pagewordnum;
    //阅读数
    private long readnum;
    //是否显示
    private boolean state;
    //属性
    private String proper;
    //============================公共字段============================
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;

    //无参数的构造器
    public TArticle() {
        this.ctime = new Date();
    }

    //Setter && Getter
    @Id
    @Column(name = "ARTICLE_ID", unique = true, nullable = false, length = 36)
    public String getId() {
        if (this.id != null) {
            return this.id;
        }
        return UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    @Lob
    @Column(name = "ARTICLE_CATEGORYID", length = 2000)
    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }


    @Lob
    @Column(name = "ARTICLE_CATEGORYIDS", length = 2000)
    public String getCategoryids() {
        return categoryids;
    }

    @Lob
    @Column(name = "ARTICLE_CATEGORYNAME", length = 2000)
    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public void setCategoryids(String categoryids) {
        this.categoryids = categoryids;
    }

    @Column(name = "ARTICLE_SOURCE", length = 100)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    @Column(name = "ARTICLE_WRITER", length = 100)

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


    @Column(name = "ARTICLE_TITLE", length = 200)

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "ARTICLE_TITLECOLOR", length = 15)

    public String getTitlecolor() {
        return titlecolor;
    }

    public void setTitlecolor(String titlecolor) {
        this.titlecolor = titlecolor;
    }


    @Column(name = "ARTICLE_URL", length = 200)

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Lob
    @Column(name = "ARTICLE_KEYWORD", length = 2000)

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    @Column(name = "ARTICLE_ABSTRACTS", length = 200)

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    @Lob
    @Column(name = "ARTICLE_CONTENT", length = 10000)

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Lob
    @Column(name = "ARTICLE_UPIMGSTR", length = 2000)

    public String getUpimgstr() {
        return upimgstr;
    }

    public void setUpimgstr(String upimgstr) {
        this.upimgstr = upimgstr;
    }

    @Lob
    @Column(name = "ARTICLE_IMG", length = 2000)

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Lob
    @Column(name = "ARTICLE_THUMBFILE", length = 2000)

    public String getThumbfile() {
        return thumbfile;
    }

    public void setThumbfile(String thumbfile) {
        this.thumbfile = thumbfile;
    }

    @Lob
    @Column(name = "ARTICLE_FILENAME", length = 2000)

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Lob
    @Column(name = "ARTICLE_FILE", length = 2000)

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    @Column(name = "ARTICLE_PAGEWORDNUM")

    public long getPagewordnum() {
        return pagewordnum;
    }

    public void setPagewordnum(long pagewordnum) {
        this.pagewordnum = pagewordnum;
    }


    @Column(name = "ARTICLE_READNUM")

    public long getReadnum() {
        return readnum;
    }

    public void setReadnum(long readnum) {
        this.readnum = readnum;
    }


    @Column(name = "ARTICLE_STATE")
    @org.hibernate.annotations.Type(type = "yes_no")
    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Lob
    @Column(name = "ARTICLE_PROPERTIES", length = 2000)
    public String getProper() {
        return proper;
    }

    public void setProper(String proper) {
        this.proper = proper;
    }

    @Column(name = "ARTICLE_RANK", length = 20)
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ARTICLE_CTIME")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPDICT_MTIME")
    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}

