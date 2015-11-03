/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/6.
 */
package com.baguix.web.common.cache;

import com.baguix.web.model.page.manctrl.SiteInfo;

/**
 * <b>网站信息缓存器</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class SiteInfoCache implements CacheI {
    @Override
    public void loadData() {
        SiteInfo si = new SiteInfo();
        SysData.siteInfo = si.fromXMLFile();
    }
}
