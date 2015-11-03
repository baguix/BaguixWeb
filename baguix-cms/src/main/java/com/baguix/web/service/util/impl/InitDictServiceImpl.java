package com.baguix.web.service.util.impl;

import com.baguix.web.common.cache.SysData;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.service.core.DictItemServiceI;
import com.baguix.web.service.core.DictServiceI;
import com.baguix.web.service.util.InitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("initDictService")
public class InitDictServiceImpl implements InitServiceI {
    @Autowired
    private BaseDaoI<TDict> dictDao;
    @Autowired
    private BaseDaoI<TDictItem> dictItemDao;
    @Autowired
    private DictServiceI dictService;
    @Autowired
    private DictItemServiceI dictItemService;


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

        //是否
        TDict yn1 = new TDict();
        yn1.setId("yesno");
        yn1.setName("yesno");
        yn1.setTitle("是否");
        yn1.setComment("是否选项");
        yn1.setType("RadioList");
        yn1.setCtime(new Date());
        yn1.setRank(10);
        dictDao.saveOrUpdate(yn1);

        TDictItem y1 = new TDictItem();
        y1.setDict(yn1);
        y1.setId("yes");
        y1.setTitle("是");
        y1.setValue("true");
        y1.setRank(10);
        y1.setCtime(new Date());
        dictItemDao.saveOrUpdate(y1);

        TDictItem n1 = new TDictItem();
        n1.setDict(yn1);
        n1.setId("no");
        n1.setTitle("否");
        n1.setValue("false");
        n1.setRank(20);
        n1.setCtime(new Date());
        dictItemDao.saveOrUpdate(n1);


        //图片水印状态
        TDict watermark = new TDict();
        watermark.setId("watermark");
        watermark.setName("watermark");
        watermark.setTitle("图片水印");
        watermark.setComment("图片添加水印：0-无，1-文字水印，2-图片水印");
        watermark.setType("SingleCombobox");
        watermark.setCtime(new Date());
        watermark.setRank(30);
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
        center.setRank(40);
        center.setCtime(new Date());
        dictItemDao.saveOrUpdate(center);


        //文章属性
        TDict yn = new TDict();
        yn.setId("online");
        yn.setName("online");
        yn.setTitle("是否显示");
        yn.setComment("网站文章选择“显示、隐藏”项：");
        yn.setType("RadioList");
        yn.setCtime(new Date());
        yn.setRank(10);
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
        //文章属性
        TDict artprop = new TDict();
        artprop.setId("artprop");
        artprop.setName("artprop");
        artprop.setTitle("文章属性");
        artprop.setComment("网站文章选择“文章属性”项：");
        artprop.setType("CheckboxList");
        artprop.setCtime(new Date());
        artprop.setRank(10);
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
        cateprop.setType("CheckboxList");
        cateprop.setCtime(new Date());
        cateprop.setRank(20);
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
        //来源
        TDict source = new TDict();
        source.setId("source");
        source.setName("source");
        source.setTitle("来源");
        source.setComment("网站文章选择“文章来源”项");
        source.setType("SingleCombobox");
        source.setCtime(new Date());
        source.setRank(10);
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
    }

    /**
     * 生成Easy UI html字符串，缓存
     */
    private void dictInMemory(){
        String hql = "from TDict t ";
        List<TDict> dictlist = dictService.find(hql);
        for(TDict d : dictlist){
            String name = d.getName();
            String value ="";
            if(d.getType().equals("RadioList")){
                value= getRadioListCode(d.getId());
            }
            if(d.getType().equals("CheckboxList")){
                value= getCheckboxListCode(d.getId());
            }
            if(d.getType().equals("SingleCombobox")){
                value= getSingleComboboxCode(d.getId());
            }
            SysData.dictMap.put(name, value);
        }
    }


    // 类型：单选列表（如：●是  ○否）
    private String getRadioListCode(String dictid) {
        StringBuffer code = new StringBuffer();
        String hql = "from TDictItem t where t.dict.id=:id order by t.rank";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", dictid);
        List<TDictItem> list = dictItemService.find(hql, params);
        int i = 0;
        for (TDictItem di : list) {
            i++;
            code.append("<label><input type='radio' id='[$id$]_" + i
                    + "' name='[$name$]' value='" + di.getValue() + "' style='[$style$]' />" + di.getTitle()
                    + "</label>");
            code.append("&nbsp;\n");
        }
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = String([$init$]);");
        code.append("$(\"input[name='[$name$]']\").each(function(){");
        code.append("if( [$name$]_value==$(this).val() ){");
        code.append("$(this).prop('checked', true);");
        code.append("}else{");
        code.append("$(this).prop('checked',false);");
        code.append("}});");
        code.append("</script>");
        code.append("initEnd");
        return code.toString();
    }

    // 类型：多选列表（如：■男装、■红色、□衬衣）
    private String getCheckboxListCode(String dictid){
        StringBuffer code = new StringBuffer();
        String hql = "from TDictItem t where t.dict.id=:id order by t.rank";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", dictid);
        List<TDictItem> list = dictItemService.find(hql,params);
        int i=0;
        for(TDictItem di : list){
            i++;
            code.append("<label><input type='checkbox' id='[$id$]_"+i+"' name='[$name$]' value='"+di.getValue()+"' style='[$style$]' />"+di.getTitle()+"</label>");
            code.append("&nbsp;\n");
        }
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = String([$init$]).split(',');");
        code.append("$(\"input[name='[$name$]']\").each(function(){");
        code.append("if( SS_inarray([$name$]_value, $(this).val()) ){");
        code.append("$(this).prop('checked', true);");
        code.append("}else{");
        code.append("$(this).prop('checked',false);");
        code.append("}});");
        code.append("</script>");
        return code.toString();
    }

    //ComboBox单选(EasyUI风格)
    private String getSingleComboboxCode(String dictid){
        StringBuffer code = new StringBuffer();
        code.append("<input id='[$id$]' name='[$name$]' style='[$style$]' class='easyui-combobox' data-options=\"valueField: 'value', textField: 'text',panelHeight:'auto',data:");

        //取数据
        code.append("[");
        String hql = "from TDictItem t where t.dict.id=:id order by t.rank";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", dictid);
        List<TDictItem> list = dictItemService.find(hql,params);
        for(TDictItem di : list){
            code.append("{'value':'");
            code.append(di.getValue());
            code.append("','text':'");
            code.append(di.getTitle());
            code.append("'}");
        }
        code.append("]");
        code.append("\" />");
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = [$init$];");
        code.append("$('#[$id$]').combobox({'value': [$name$]_value});");
        code.append("</script>");
        return code.toString().replace("}{", "},{");
    }
}
