/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/6.
 */
package com.baguix.web.common.cache;

import com.baguix.web.model.page.manctrl.ImageInfo;

/**
 * <b>图片处理信息缓存器</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class ImageInfoCache implements CacheI {
    @Override
    public void loadData() {
        ImageInfo ii = new ImageInfo();
        SysData.imageInfo = ii.fromXMLFile();
    }
}
