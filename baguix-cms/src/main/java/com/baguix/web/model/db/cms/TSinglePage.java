/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/12/12.
 */
package com.baguix.web.model.db.cms;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * <b>单页文章</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Entity
@DiscriminatorValue("single")
public class TSinglePage extends TArticle {

}
