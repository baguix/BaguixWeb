package com.baguix.web.service.util.impl;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.db.core.TDictClass;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.model.enums.StateType;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.service.core.DictServiceI;
import com.baguix.web.service.util.InitServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("initDictService")
public class InitDictServiceImpl implements InitServiceI {
    @Autowired
    private BaseDaoI<TDictClass> dictClassDao;

    @Autowired
    private BaseDaoI<TDict> dictDao;
    @Autowired
    private BaseDaoI<TDictItem> dictItemDao;
    @Autowired
    private DictServiceI dictService;


    @Override
    synchronized public void repair() {
        initYN();
        initAriProperty();
        initArtSrcWriData();
    }

    @Override
    public void cache() {
        //字典表进入系统缓存
        dictInMemory();
    }

    @Override
    public void reload() {

    }

    //文章属性
    private void initYN() {
        TDictClass sysDC= new TDictClass();
        sysDC.setTitle("系统字典");
        sysDC.setComment("本系统中普遍使用的字典，不能随意删除，删除后可能会导致系统失效。");
        sysDC.setRank(10);
        dictClassDao.saveOrUpdate(sysDC);

        //是否
        TDict yn1 = new TDict();
        yn1.setId("yesno");
        yn1.setName("yesno");
        yn1.setTitle("是否");
        yn1.setComment("是否选项，是为默认");
        yn1.setType("RadioList");
        yn1.setCtime(new Date());
        yn1.setRank(10);
        yn1.setState(StateType.SHOW);
        yn1.setDictClass(sysDC);
        dictDao.saveOrUpdate(yn1);

        TDictItem y1 = new TDictItem();
        y1.setDict(yn1);
        y1.setId("yes1");
        y1.setTitle("是");
        y1.setValue("true");
        y1.setDef(true);
        y1.setRank(10);
        y1.setCtime(new Date());
        dictItemDao.saveOrUpdate(y1);

        TDictItem n1 = new TDictItem();
        n1.setDict(yn1);
        n1.setId("no1");
        n1.setTitle("否");
        n1.setValue("false");
        n1.setDef(false);
        n1.setRank(20);
        n1.setCtime(new Date());
        dictItemDao.saveOrUpdate(n1);

        //否是
        TDict yn2 = new TDict();
        yn2.setId("noyes");
        yn2.setName("noyes");
        yn2.setTitle("否是");
        yn2.setComment("否是选项，否为默认");
        yn2.setType("RadioList");
        yn2.setCtime(new Date());
        yn2.setRank(10);
        yn2.setState(StateType.SHOW);
        yn2.setDictClass(sysDC);
        dictDao.saveOrUpdate(yn2);

        TDictItem n2 = new TDictItem();
        n2.setDict(yn2);
        n2.setId("no2");
        n2.setTitle("否");
        n2.setDef(true);
        n2.setValue("false");
        n2.setRank(20);
        n2.setCtime(new Date());
        dictItemDao.saveOrUpdate(n2);

        TDictItem y2 = new TDictItem();
        y2.setDict(yn2);
        y2.setId("yes2");
        y2.setTitle("是");
        y2.setValue("true");
        y2.setDef(false);
        y2.setRank(10);
        y2.setCtime(new Date());
        dictItemDao.saveOrUpdate(y2);

        TDictClass bgDC= new TDictClass();
        bgDC.setTitle("后台字典");
        bgDC.setComment("后台使用的字典。");
        bgDC.setRank(20);
        dictClassDao.saveOrUpdate(bgDC);
        TDictClass frontDC= new TDictClass();
        frontDC.setTitle("前台字典");
        frontDC.setComment("前台使用的字典。可以新增用于前台页面模版制作，但删除后可能会导致某些已经做好的前台功能失效。");
        frontDC.setRank(30);
        dictClassDao.saveOrUpdate(frontDC);



        //图片水印状态
        TDict watermark = new TDict();
        watermark.setId("watermark");
        watermark.setName("watermark");
        watermark.setTitle("图片水印");
        watermark.setComment("图片添加水印：0-无，1-文字水印，2-图片水印");
        watermark.setType("SingleCombobox");
        watermark.setCtime(new Date());
        watermark.setRank(30);
        watermark.setState(StateType.SHOW);
        watermark.setDictClass(bgDC);
        dictDao.saveOrUpdate(watermark);

        TDictItem nowatermark = new TDictItem();
        nowatermark.setDict(watermark);
        nowatermark.setId("nowatermark");
        nowatermark.setTitle("无水印");
        nowatermark.setValue("0");
        nowatermark.setRank(10);
        nowatermark.setCtime(new Date());
        dictItemDao.saveOrUpdate(nowatermark);

        TDictItem twatermark = new TDictItem();
        twatermark.setDict(watermark);
        twatermark.setId("twatermark");
        twatermark.setTitle("文字水印");
        twatermark.setValue("1");
        twatermark.setRank(20);
        twatermark.setCtime(new Date());
        dictItemDao.saveOrUpdate(twatermark);

        TDictItem iwatermark = new TDictItem();
        iwatermark.setDict(watermark);
        iwatermark.setId("iwatermark");
        iwatermark.setTitle("图片水印");
        iwatermark.setValue("2");
        iwatermark.setRank(30);
        iwatermark.setCtime(new Date());
        dictItemDao.saveOrUpdate(iwatermark);

        //图片水印位置
        TDict watermarkpos = new TDict();
        watermarkpos.setId("watermarkpos");
        watermarkpos.setName("watermarkpos");
        watermarkpos.setTitle("水印位置");
        watermarkpos.setComment("左上角：northwest、左下角：southwest、右上角：northeast、右下角：southeast、中间：center");
        watermarkpos.setType("RadioList");
        watermarkpos.setCtime(new Date());
        watermarkpos.setRank(40);
        watermarkpos.setState(StateType.SHOW);
        watermarkpos.setDictClass(bgDC);
        dictDao.saveOrUpdate(watermarkpos);

        TDictItem northeast = new TDictItem();
        northeast.setDict(watermarkpos);
        northeast.setId("wmnortheast");
        northeast.setTitle("右上角");
        northeast.setValue("northeast");
        northeast.setRank(10);
        northeast.setCtime(new Date());
        dictItemDao.saveOrUpdate(northeast);

        TDictItem southeast = new TDictItem();
        southeast.setDict(watermarkpos);
        southeast.setId("wmsoutheast");
        southeast.setTitle("右下角");
        southeast.setValue("southeast");
        southeast.setRank(20);
        southeast.setDef(true);
        southeast.setCtime(new Date());
        dictItemDao.saveOrUpdate(southeast);


        TDictItem northwest = new TDictItem();
        northwest.setDict(watermarkpos);
        northwest.setId("wmnorthwest");
        northwest.setTitle("左上角");
        northwest.setValue("northwest");
        northwest.setRank(30);
        northwest.setCtime(new Date());
        dictItemDao.saveOrUpdate(northwest);

        TDictItem southwest = new TDictItem();
        southwest.setDict(watermarkpos);
        southwest.setId("wmsouthwest");
        southwest.setTitle("左下角");
        southwest.setValue("southwest");
        southwest.setRank(40);
        southwest.setCtime(new Date());
        dictItemDao.saveOrUpdate(southwest);

        TDictItem center = new TDictItem();
        center.setDict(watermarkpos);
        center.setId("wmcenter");
        center.setTitle("中间");
        center.setValue("center");
        center.setRank(50);
        center.setCtime(new Date());
        dictItemDao.saveOrUpdate(center);

        //字典类型
        TDict dicttype = new TDict();
        dicttype.setId("dicttype");
        dicttype.setName("dicttype");
        dicttype.setTitle("字典类型");
        dicttype.setComment("字典类型");
        dicttype.setType("SingleCombobox");
        dicttype.setCtime(new Date());
        dicttype.setRank(30);
        dicttype.setState(StateType.SHOW);
        dicttype.setDictClass(bgDC);
        dictDao.saveOrUpdate(dicttype);

        TDictItem radiolist = new TDictItem();
        radiolist.setDict(dicttype);
        radiolist.setId("radiolist");
        radiolist.setTitle("单选列表");
        radiolist.setValue("RadioList");
        radiolist.setRank(10);
        radiolist.setCtime(new Date());
        dictItemDao.saveOrUpdate(radiolist);

        TDictItem checkboxlist = new TDictItem();
        checkboxlist.setDict(dicttype);
        checkboxlist.setId("checkboxlist");
        checkboxlist.setTitle("多选列表");
        checkboxlist.setValue("CheckboxList");
        checkboxlist.setRank(20);
        checkboxlist.setCtime(new Date());
        dictItemDao.saveOrUpdate(checkboxlist);

        TDictItem singlecombobox = new TDictItem();
        singlecombobox.setDict(dicttype);
        singlecombobox.setId("singlecombobox");
        singlecombobox.setTitle("单选组合框");
        singlecombobox.setValue("SingleCombobox");
        singlecombobox.setRank(30);
        singlecombobox.setCtime(new Date());
        dictItemDao.saveOrUpdate(singlecombobox);

        TDictItem multicombobox = new TDictItem();
        multicombobox.setDict(dicttype);
        multicombobox.setId("multicombobox");
        multicombobox.setTitle("多选组合框");
        multicombobox.setValue("MultiCombobox");
        multicombobox.setRank(40);
        multicombobox.setCtime(new Date());
        dictItemDao.saveOrUpdate(multicombobox);

        TDictItem singlecombotree = new TDictItem();
        singlecombotree.setDict(dicttype);
        singlecombotree.setId("singlecombotree");
        singlecombotree.setTitle("单选树型组合框");
        singlecombotree.setValue("SingleComboTree");
        singlecombotree.setRank(50);
        singlecombotree.setCtime(new Date());
        dictItemDao.saveOrUpdate(singlecombotree);

        TDictItem multicombotree = new TDictItem();
        multicombotree.setDict(dicttype);
        multicombotree.setId("multicombotree");
        multicombotree.setTitle("多选树型组合框");
        multicombotree.setValue("MultiComboTree");
        multicombotree.setRank(60);
        multicombotree.setCtime(new Date());
        dictItemDao.saveOrUpdate(multicombotree);

        //文章属性
        TDict yn = new TDict();
        yn.setId("online");
        yn.setName("online");
        yn.setTitle("显示隐藏");
        yn.setComment("网站文章选择“显示、隐藏”项：");
        yn.setType("RadioList");
        yn.setCtime(new Date());
        yn.setRank(10);
        yn.setState(StateType.SHOW);
        yn.setDictClass(sysDC);
        dictDao.saveOrUpdate(yn);

        TDictItem y = new TDictItem();
        y.setDict(yn);
        y.setId("show");
        y.setTitle("显示");
        y.setValue("true");
        y.setRank(10);
        y.setCtime(new Date());
        dictItemDao.saveOrUpdate(y);

        TDictItem n = new TDictItem();
        n.setDict(yn);
        n.setId("hide");
        n.setTitle("隐藏");
        n.setValue("false");
        n.setRank(20);
        n.setCtime(new Date());
        dictItemDao.saveOrUpdate(n);
    }

    //文章属性
    private void initAriProperty() {

        //后台字典
        String hql = "from TDictClass t where t.title='后台字典'";
        TDictClass bgDC = dictClassDao.getByHql(hql);

        //文章属性
        TDict artprop = new TDict();
        artprop.setId("artprop");
        artprop.setName("artprop");
        artprop.setTitle("文章属性");
        artprop.setComment("网站文章选择“文章属性”项：");
        artprop.setType("CheckboxList");
        artprop.setCtime(new Date());
        artprop.setRank(10);
        artprop.setState(StateType.SHOW);
        artprop.setDictClass(bgDC);
        dictDao.saveOrUpdate(artprop);

        TDictItem isaudit = new TDictItem();
        isaudit.setDict(artprop);
        isaudit.setId("isaudit");
        isaudit.setTitle("已审");
        isaudit.setValue("已审");
        isaudit.setRank(20);
        isaudit.setCtime(new Date());
        dictItemDao.saveOrUpdate(isaudit);

        TDictItem isnew = new TDictItem();
        isnew.setDict(artprop);
        isnew.setId("isnew");
        isnew.setTitle("最新");
        isnew.setValue("最新");
        isnew.setRank(30);
        isnew.setCtime(new Date());
        dictItemDao.saveOrUpdate(isnew);

        TDictItem ishomethumb = new TDictItem();
        ishomethumb.setDict(artprop);
        ishomethumb.setId("ishomethumb");
        ishomethumb.setTitle("专栏");
        ishomethumb.setValue("专栏");
        ishomethumb.setRank(40);
        ishomethumb.setCtime(new Date());
        dictItemDao.saveOrUpdate(ishomethumb);

        TDictItem isthumb = new TDictItem();
        isthumb.setDict(artprop);
        isthumb.setId("isthumb");
        isthumb.setTitle("缩图");
        isthumb.setValue("缩图");
        isthumb.setRank(50);
        isthumb.setCtime(new Date());
        dictItemDao.saveOrUpdate(isthumb);

        TDictItem isflash = new TDictItem();
        isflash.setDict(artprop);
        isflash.setId("isflash");
        isflash.setTitle("幻灯");
        isflash.setValue("幻灯");
        isflash.setRank(60);
        isflash.setCtime(new Date());
        dictItemDao.saveOrUpdate(isflash);

        TDictItem isimg = new TDictItem();
        isimg.setDict(artprop);
        isimg.setId("isimg");
        isimg.setTitle("滚图");
        isimg.setValue("滚图");
        isimg.setRank(70);
        isimg.setCtime(new Date());
        dictItemDao.saveOrUpdate(isimg);

        TDictItem ismarquee = new TDictItem();
        ismarquee.setDict(artprop);
        ismarquee.setId("ismarquee");
        ismarquee.setTitle("滚信");
        ismarquee.setValue("滚信");
        ismarquee.setRank(80);
        ismarquee.setCtime(new Date());
        dictItemDao.saveOrUpdate(ismarquee);

        TDictItem isrecom = new TDictItem();
        isrecom.setDict(artprop);
        isrecom.setId("isrecom");
        isrecom.setTitle("推荐");
        isrecom.setValue("推荐");
        isrecom.setRank(90);
        isrecom.setCtime(new Date());
        dictItemDao.saveOrUpdate(isrecom);

        TDictItem istop = new TDictItem();
        istop.setDict(artprop);
        istop.setId("istop");
        istop.setTitle("置顶");
        istop.setValue("置顶");
        istop.setRank(100);
        istop.setCtime(new Date());
        dictItemDao.saveOrUpdate(istop);


        //栏目属性
        TDict cateprop = new TDict();
        cateprop.setId("categoryprop");
        cateprop.setName("categoryprop");
        cateprop.setTitle("栏目属性");
        cateprop.setComment("网站栏目选择“文章属性”项：");
        cateprop.setType("SingleComboTree");
        cateprop.setCtime(new Date());
        cateprop.setRank(20);
        cateprop.setState(StateType.SHOW);
        cateprop.setDictClass(bgDC);
        dictDao.saveOrUpdate(cateprop);

        TDictItem isnav = new TDictItem();
        isnav.setDict(cateprop);
        isnav.setId("isnav");
        isnav.setTitle("导航");
        isnav.setValue("导航");
        isnav.setRank(10);
        isnav.setCtime(new Date());
        dictItemDao.saveOrUpdate(isnav);

        TDictItem ishomeimg = new TDictItem();
        ishomeimg.setDict(cateprop);
        ishomeimg.setId("ishomeimg");
        ishomeimg.setTitle("首页");
        ishomeimg.setValue("首页");
        ishomeimg.setRank(30);
        ishomeimg.setCtime(new Date());
        dictItemDao.saveOrUpdate(ishomeimg);

        TDictItem isnenu = new TDictItem();
        isnenu.setDict(cateprop);
        isnenu.setId("isnenu");
        isnenu.setTitle("菜单");
        isnenu.setValue("菜单");
        isnenu.setRank(20);
        isnenu.setCtime(new Date());
        dictItemDao.saveOrUpdate(isnenu);

        TDictItem iszt = new TDictItem();
        iszt.setDict(cateprop);
        iszt.setId("iszt");
        iszt.setTitle("专题");
        iszt.setValue("专题");
        iszt.setRank(40);
        iszt.setCtime(new Date());
        dictItemDao.saveOrUpdate(iszt);

    }

    //修复文章来源，文章作者字典
    private void initArtSrcWriData() {
        //后台字典
        String hql = "from TDictClass t where t.title='后台字典'";
        TDictClass bgDC = dictClassDao.getByHql(hql);

        //来源
        TDict source = new TDict();
        source.setId("source");
        source.setName("source");
        source.setTitle("来源");
        source.setComment("网站文章选择“文章来源”项");
        source.setType("SingleCombobox");
        source.setCtime(new Date());
        source.setRank(10);
        source.setState(StateType.DELETE);
        source.setDictClass(bgDC);
        dictDao.saveOrUpdate(source);

        TDictItem unit = new TDictItem();
        unit.setDict(source);
        unit.setId("unit");
        unit.setTitle("XXX学校");
        unit.setValue("XXX学校");
        unit.setRank(10);
        unit.setCtime(new Date());
        dictItemDao.saveOrUpdate(unit);

        TDictItem wl = new TDictItem();
        wl.setDict(source);
        wl.setId("wl");
        wl.setTitle("网络");
        wl.setValue("网络");
        wl.setRank(20);
        wl.setCtime(new Date());
        dictItemDao.saveOrUpdate(wl);

        //作者
        TDict writer = new TDict();
        writer.setId("writer");
        writer.setName("writer");
        writer.setTitle("作者");
        writer.setComment("网站文章选择“文章作者”项");
        writer.setType("SingleCombobox");
        writer.setCtime(new Date());
        writer.setRank(20);
        writer.setState(StateType.SHOW);
        writer.setDictClass(bgDC);
        dictDao.saveOrUpdate(writer);

        TDictItem xb = new TDictItem();
        xb.setDict(writer);
        xb.setId("xb");
        xb.setTitle("行政办公室");
        xb.setValue("行政办公室");
        xb.setRank(10);
        xb.setCtime(new Date());
        dictItemDao.saveOrUpdate(xb);

        TDictItem xxzx = new TDictItem();
        xxzx.setDict(writer);
        xxzx.setId("xxzx");
        xxzx.setTitle("信息中心");
        xxzx.setValue("信息中心");
        xxzx.setRank(10);
        xxzx.setCtime(new Date());
        dictItemDao.saveOrUpdate(xb);

        TDictItem yiming = new TDictItem();
        yiming.setDict(writer);
        yiming.setId("yiming");
        yiming.setTitle("佚名");
        yiming.setValue("佚名");
        yiming.setRank(10);
        yiming.setCtime(new Date());
        dictItemDao.saveOrUpdate(yiming);

        //试题标签
        TDict tags = new TDict();
        tags.setId("ExamTags");
        tags.setName("ExamTags");
        tags.setTitle("试题标签");
        tags.setComment("试题搜索和组卷使用的标签项");
        tags.setType("Tags");
        tags.setCtime(new Date());
        tags.setRank(30);
        tags.setState(StateType.SHOW);
        tags.setDictClass(bgDC);
        dictDao.saveOrUpdate(tags);

        TDictItem xuekao = new TDictItem();
        xuekao.setDict(tags);
        xuekao.setId("xuekao");
        xuekao.setTitle("学考题");
        xuekao.setValue("学考题");
        xuekao.setRank(10);
        xuekao.setCtime(new Date());
        dictItemDao.saveOrUpdate(xuekao);

        TDictItem moni = new TDictItem();
        moni.setDict(tags);
        moni.setId("moni");
        moni.setTitle("模拟题");
        moni.setValue("模拟题");
        moni.setRank(20);
        moni.setCtime(new Date());
        dictItemDao.saveOrUpdate(moni);

        TDictItem zhinan = new TDictItem();
        zhinan.setDict(tags);
        zhinan.setId("zhinan");
        zhinan.setTitle("指南题");
        zhinan.setValue("指南题");
        zhinan.setRank(30);
        zhinan.setCtime(new Date());
        dictItemDao.saveOrUpdate(zhinan);

        TDictItem lianxice = new TDictItem();
        lianxice.setDict(tags);
        lianxice.setId("lianxice");
        lianxice.setTitle("练习册题");
        lianxice.setValue("练习册题");
        lianxice.setRank(40);
        lianxice.setCtime(new Date());
        dictItemDao.saveOrUpdate(lianxice);

        TDictItem zizhu = new TDictItem();
        zizhu.setDict(tags);
        zizhu.setId("zizhu");
        zizhu.setTitle("自主命题");
        zizhu.setValue("自主命题");
        zizhu.setDef(true);
        zizhu.setRank(50);
        zizhu.setCtime(new Date());
        dictItemDao.saveOrUpdate(zizhu);

        TDictItem chaogang = new TDictItem();
        chaogang.setDict(tags);
        chaogang.setId("chaogang");
        chaogang.setTitle("超纲题");
        chaogang.setValue("超纲题");
        chaogang.setRank(60);
        chaogang.setCtime(new Date());
        dictItemDao.saveOrUpdate(chaogang);

        TDictItem t2013 = new TDictItem();
        t2013.setDict(tags);
        t2013.setId("t2013");
        t2013.setTitle("2013年");
        t2013.setValue("2013年");
        t2013.setRank(70);
        t2013.setCtime(new Date());
        dictItemDao.saveOrUpdate(t2013);

        TDictItem t2014 = new TDictItem();
        t2014.setDict(tags);
        t2014.setId("t2014");
        t2014.setTitle("2014年");
        t2014.setValue("2014年");
        t2014.setRank(80);
        t2014.setCtime(new Date());
        dictItemDao.saveOrUpdate(t2014);

        TDictItem t2015 = new TDictItem();
        t2015.setDict(tags);
        t2015.setId("t2015");
        t2015.setTitle("2015年");
        t2015.setValue("2015年");
        t2015.setRank(90);
        t2015.setCtime(new Date());
        dictItemDao.saveOrUpdate(t2015);

        TDictItem t2016 = new TDictItem();
        t2016.setDict(tags);
        t2016.setId("t2016");
        t2016.setTitle("2016年");
        t2016.setValue("2016年");
        t2016.setRank(100);
        t2016.setCtime(new Date());
        dictItemDao.saveOrUpdate(t2016);

        TDictItem t2017 = new TDictItem();
        t2017.setDict(tags);
        t2017.setId("t2017");
        t2017.setTitle("2017年");
        t2017.setValue("2017年");
        t2017.setRank(110);
        t2017.setCtime(new Date());
        dictItemDao.saveOrUpdate(t2017);

        TDictItem t2018 = new TDictItem();
        t2018.setDict(tags);
        t2018.setId("t2018");
        t2018.setTitle("2018年");
        t2018.setValue("2018年");
        t2018.setRank(120);
        t2018.setCtime(new Date());
        dictItemDao.saveOrUpdate(t2018);
    }

    /**
     * 生成Easy UI html字符串，缓存
     */
    private void dictInMemory(){
        String hql = "from TDict t where t.state=:state";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state",StateType.SHOW);
        List<TDict> dictlist = dictService.find(hql, params);
        for(TDict d : dictlist){
            Dict dd = new Dict();
            BeanUtils.copyProperties(d, dd);
            dictService.inCache(dd);
        }
    }
}
