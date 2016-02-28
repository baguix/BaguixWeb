package com.baguix.web.service.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baguix.web.common.cache.SysData;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.db.core.TDictClass;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.enums.StateType;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.service.core.DictItemServiceI;
import com.baguix.web.service.core.DictServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import com.baguix.web.taglib.easyui.DictionaryTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<TDict> implements DictServiceI {
	@Autowired
	private BaseDaoI<TDict> dao;
	@Autowired
	private BaseDaoI<TDictClass> dcDao;
	@Autowired
	private DictItemServiceI dictItemService;

	@Override
	synchronized public Dict add(Dict dict) {
		TDictClass dc = dcDao.getById(TDictClass.class,dict.getDcid());
		TDict t = new TDict();
		BeanUtils.copyProperties(dict, t);
		t.setId(UUID.randomUUID().toString());
		t.setCtime(new Date());
		t.setMtime(new Date());
		t.setDictClass(dc);
        t.setState(StateType.SHOW);
		dao.save(t);
		BeanUtils.copyProperties(t, dict);
		dict.setDctitle(t.getDictClass().getTitle());
		dict.setDcid(t.getDictClass().getId());
		return dict;
	}

    // 暂删
    @Override
    public void delete(String ids) {
        String[] nids = ids.split(",");
        // 先清缓存
        String hql = "from TDict t where t.state=:state and t.id in (";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", StateType.SHOW);
        for (int i = 0; i < nids.length; i++) {
            if (i > 0) {
                hql += ",";
            }
            hql += "'" + nids[i] + "'";
        }
        hql += ")";
        List<TDict> list = dao.find(hql, params);
        for (TDict d : list){
            clsCache(d.getName());
        }

        // 再处理数据库
        String hql1 = "update TDict t set t.state=:state where t.id in (";
        for (int i = 0; i < nids.length; i++) {
            if (i > 0) {
                hql1 += ",";
            }
            hql1 += "'" + nids[i] + "'";
        }
        hql1 += ")";
        Map<String, Object> params1 = new HashMap<String, Object>();
        params1.put("state", StateType.DELETE);
        dao.executeHql(hql1,params1);
    }

    // 彻底删除
	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TDict t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		dao.executeHql(hql);
	}

	@Override
	synchronized public Dict edit(Dict dict) {
        TDictClass dc = dcDao.getById(TDictClass.class,dict.getDcid());
		TDict t = new TDict();
		BeanUtils.copyProperties(dict, t);
		t.setMtime(new Date());
        t.setDictClass(dc);
		dao.saveOrUpdate(t);
		BeanUtils.copyProperties(t, dict);
        this.inCache(dict);
		return dict;
	}

	@Override
	public Dict view(Dict dict) {
		TDict t = dao.getById(TDict.class, dict.getId());
		BeanUtils.copyProperties(t, dict);
		return dict;
	}
	
	@Override
	public DataGrid datagrid(Dict dict) {
		DataGrid dg = new DataGrid();
		String hql = "from TDict t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(dict, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(dict, hql);
		List<TDict> l = dao.find(hql, params, dict.getPage(), dict.getRows());
		List<Dict> nl = new ArrayList<Dict>();
		changeModel(l, nl);
		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public Dict search(String where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inCache(Dict dict) {
		DictionaryTemplate dt = new DictionaryTemplate();
        dt.setDictItemService(dictItemService);
		String name = dict.getName();
		String value ="";
		if(dict.getType().equals("RadioList")){
			value= dt.getRadioListCode(dict.getId());
		}
		if(dict.getType().equals("CheckboxList")){
			value= dt.getCheckboxListCode(dict.getId());
		}
		if(dict.getType().equals("SingleCombobox")){
			value= dt.getSingleComboboxCode(dict.getId());
		}
		if(dict.getType().equals("MultiCombobox")){
			value= dt.getMultiComboboxCode(dict.getId());
		}
		if(dict.getType().equals("SingleComboTree")){
			value= dt.getSingleComboTreeCode(dict.getId());
		}
		if(dict.getType().equals("MultiComboTree")){
			value= dt.getMultiComboTreeCode(dict.getId());
		}
		SysData.dictMap.put(name, value);
	}

    @Override
    public void clsCache(String dictName) {
        SysData.dictMap.put(dictName, "");
    }

    //private
	private void changeModel(List<TDict> l, List<Dict> nl) {
		if (l != null && l.size() > 0) {
			for (TDict t : l) {
				Dict u = new Dict();
				BeanUtils.copyProperties(t, u);
				u.setDcid(t.getDictClass().getId());
				u.setDctitle(t.getDictClass().getTitle());
				nl.add(u);
			}
		}
	}

	private String addOrder(Dict dict, String hql) {
		if (dict.getSort() != null) {
			hql += " order by " + dict.getSort() + " " + dict.getOrder();
		}else{
			hql += " order by t.rank asc";
		}
		return hql;
	}

	private String addWhere(Dict dict, String hql, Map<String, Object> params) {
        hql += " where t.state=:state ";
        params.put("state", StateType.SHOW);

        if (dict.getDcid() != null && !dict.getDcid().trim().equals("")) {
			hql += " and t.dictClass.id=:dcid ";
			params.put("dcid", dict.getDcid().trim());
		}
		return hql;
	}
}
