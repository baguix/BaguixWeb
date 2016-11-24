package com.baguix.web.service.exam.impl;

import com.baguix.utils.data.Converter;
import com.baguix.utils.data.PermutationTool;
import com.baguix.utils.data.ValueTool;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.ECommonField;
import com.baguix.web.model.db.exam.*;
import com.baguix.web.model.enums.QuestionType;
import com.baguix.web.model.enums.StateType;
import com.baguix.web.model.page.exam.Knowledge;
import com.baguix.web.model.page.exam.Option;
import com.baguix.web.model.page.exam.Select;
import com.baguix.web.service.exam.SelectServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Scott on 2016/3/28.
 */

@Service("selectService")
public class SelectServiceImpl extends BaseServiceImpl<TSelect> implements SelectServiceI {
    @Autowired
    private BaseDaoI<TSelect> dao;
    @Autowired
    private BaseDaoI<TOption> optdao;
    @Autowired
    private BaseDaoI<TKnowledge> kdao;
    @Autowired
    private BaseDaoI<TQuestionKnowledge> qklinkedao;
    @Autowired
    private BaseDaoI<TQuestionPool> qpdao;

    @Override
    public Select add(Select question) {
        // 更新数据库
        TSelect t = new TSelect();
        t.setId(UUID.randomUUID().toString());
        t.setContent(question.getContent());
        t.setAnalysis(question.getAnalysis());
        // 摘要，截取前100字符
        t.setSummary(question.getSummary());
        t.setTags(question.getTags());
        ECommonField cf = new ECommonField();
        cf.setCtime(new Date());
        cf.setStatus(StateType.SHOW);
        t.setCommonfield(cf);
        List<Option> options = question.getOption();
        List<TOption> opts = new ArrayList<>();
        for (Option o : options){
            TOption ot = new TOption();
            ot.setId(UUID.randomUUID().toString());
            ot.setContent(o.getContent());
            if(o.getAnswer()!=null && "true".equals(o.getAnswer())){
                o.setSelected(true);
            }
            ot.setSelected(o.getSelected());
            // 摘要，截取前100字符
            ot.setSummary(o.getSummary());
            opts.add(ot);
            optdao.saveOrUpdate(ot);
        }
        t.setOption(opts);

        //设置知识点
        String kid = question.getKid();

        Set<TQuestionKnowledge> qkset = new HashSet<>();
        if(StringUtils.isNotBlank(kid)){
            getKnowledgeSet(qkset, t, kid);
        }
        t.setQuestionKnowledges(qkset);
        dao.saveOrUpdate(t);

        // 自动生成试题池
        genQuestionPool(t);
        return question;
    }

    private String getPerStr(int num){
        String[] text = {"0","1","2","3","4","5","6","7","8","9"};
        String[] r = Arrays.copyOfRange(text, 0, num);
        String s = Arrays.toString(r);
        String result = s.substring(1,s.length()-1);
        return result;
    }

    private void genQuestionPool(TSelect t){
        // 自动生成试题池
        PermutationTool pt = new PermutationTool();
        // 选项数
        int onum = t.getOption().size();
        String[] text = {"A","B","C","D","E","F","G","H","I","J"};
        // 选项列表
        List<String>optPer = pt.Permutation(getPerStr(onum));
        for(int j=0;j<optPer.size();j++){
            String optIndexList = optPer.get(j);
            // 组装试题内容、答案和索引
            StringBuilder Content = new StringBuilder();
            Content.append("<div class='question_content'>");
            Content.append(t.getContent());
            Content.append("</div>");
            Content.append("<div class='question_options'>");

            String[] optIndex = optIndexList.split(",");
            String optHTML="";
            TQuestionPool qp = new TQuestionPool();
            for (int i =0; i<optIndex.length; i++){
                int optInd = ValueTool.str2Int(optIndex[i].trim());
                TOption o = t.getOption().get(optInd);
                // 去掉首尾<p></p>
                String opt = o.getContent().substring(3,o.getContent().length()-4);
                StringBuilder optContent = new StringBuilder();
                optContent.append("<div class='question_option'>");
                optContent.append(text[i]);
                optContent.append("．");
                optContent.append(opt);
                optContent.append("</div>");
                if(o.isSelected()){
                    qp.setAnswer(text[i]);
                }
                optHTML += optContent.toString();
            }
            Content.append(optHTML);
            Content.append("</div>");
            qp.setId(UUID.randomUUID().toString());
            qp.setQid(t.getId());
            qp.setContent(Content.toString());
            qp.setAnalysis(t.getAnalysis());
            qp.setType(QuestionType.SELECT);
            qp.setIndex(j);
            qpdao.saveOrUpdate(qp);
        }
    }
    @Override
    public List<Option> getOptions(String id) {
        TSelect select = dao.getById(TSelect.class, id);
        List<TOption> opts = select.getOption();
        List<Option> optlist = new ArrayList<>();
        for(TOption t: opts){
            Option p = new Option();
            p.setId(t.getId());
            p.setContent(t.getContent());
            p.setSummary(t.getSummary());
            p.setSelected(t.isSelected());
            if(t.isSelected()){
                p.setAnswer("true");
            }
            else{
                p.setAnswer("false");
            }
            optlist.add(p);
        }

        return optlist;
    }

    @Override
    public Select edit(Select question) {
        // 更新数据库
        TSelect t = new TSelect();
        t.setId(question.getId());
        t.setContent(question.getContent());
        t.setAnalysis(question.getAnalysis());
        // 摘要，截取前100字符
        t.setSummary(question.getSummary());
        t.setTags(question.getTags());
        ECommonField cf = new ECommonField();
        cf.setMtime(new Date());
        cf.setStatus(StateType.SHOW);
        t.setCommonfield(cf);
        //选项处理
        List<Option> options = question.getOption();
        List<TOption> opts = new ArrayList<>();
        for (Option o : options){
            TOption ot = new TOption();
            ot.setId(o.getId());
            ot.setContent(o.getContent());
            if(o.getAnswer()!=null && "true".equals(o.getAnswer())){
                o.setSelected(true);
            }else{
                o.setSelected(false);
            }
            ot.setSelected(o.getSelected());
            // 摘要，截取前100字符
            ot.setSummary(o.getSummary());
            opts.add(ot);
            optdao.saveOrUpdate(ot);
        }
        t.setOption(opts);
        //删掉试题池中的试题
        String hql = "delete from TQuestionPool qp where qp.qid=:qid";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qid",question.getId());
        qpdao.executeHql(hql, params);
        // 自动生成试题池
        genQuestionPool(t);

        dao.saveOrUpdate(t);
        return question;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void del(String id) {
    }

    @Override
    public Select getSelect(String id) {
        TSelect t =  dao.getById(TSelect.class,id);
        Select p = new Select();
        db2page(t,p);
        return p;
    }

    @Override
    public List<Select> listAll(String kid) {
        StringBuilder hql = new StringBuilder();
        hql.append("from TSelect t ");
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(kid) && !"000".equals(kid)) {
            hql.append(" join t.questionKnowledges qk ");
            hql.append(" where qk.knowledge.id=:kid and t.commonfield.status=:status ");
            hql.append(" order by t.commonfield.rank asc");
            params.put("kid", kid);
            params.put("status", StateType.SHOW);
        }
        else {
            hql.append(" where t.commonfield.status=:status ");
            hql.append(" order by t.commonfield.rank asc");
            params.put("status", StateType.SHOW);
        }
        List dblist = dao.find(hql.toString(), params);
        List<Select> list = new ArrayList<Select>();
        if (dblist != null && dblist.size() > 0) {
            if (StringUtils.isNotBlank(kid) && !"000".equals(kid)) {
                for (int i = 0; i < dblist.size(); i++) {
                    Object[] objects = (Object[]) dblist.get(i);
                    TSelect t = (TSelect) objects[0];
                    Select p = new Select();
                    db2page(t, p);
                    list.add(p);
                }
            }
            else {
                for (int i = 0; i < dblist.size(); i++) {
                    TSelect t = (TSelect) dblist.get(i);
                    Select p = new Select();
                    db2page(t, p);
                    list.add(p);
                }
            }
        }
        return list;
    }

    @Override
    public String viewQPContent(String qid, int index) {
        StringBuilder result = new StringBuilder();
        String hql = "from TQuestionPool as t where t.qid=:qid and t.index=:index";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qid", qid);
        params.put("index", index);
        TQuestionPool qp = qpdao.getByHql(hql,params);
        result.append(qp.getContent());
        result.append("<div class='answer'>");
        result.append("【答案】");
        result.append(qp.getAnswer());
        result.append("</div>");
        result.append("<div class='analysis'>");
        result.append("【解析】");
        result.append(qp.getAnalysis());
        result.append("</div>");
        return result.toString();
    }

    private void db2page(TSelect t, Select p) {
        BeanUtils.copyProperties(t, p, "commonfield", "knowledges", "knowledgestr");
        // 公共字段
        p.setCtime(t.getCommonfield().getCtime());
        p.setMtime(t.getCommonfield().getMtime());
        p.setStatus(t.getCommonfield().getStatus());
        p.setRank(t.getCommonfield().getRank());
        // 知识点
        Set<Knowledge> pset = new HashSet<Knowledge>();
        Set<TQuestionKnowledge> qkset = t.getQuestionKnowledges();
        StringBuilder kstr = new StringBuilder();
        for (TQuestionKnowledge qk : qkset) {
            Knowledge k = new Knowledge();
            TKnowledge tk = qk.getKnowledge();
            k.setId(tk.getId());
            k.setText(tk.getText());
            k.setContent(tk.getContent());
            pset.add(k);
            kstr.append(",");
            kstr.append(k.getText());
        }
        p.setKnowledges(pset);
        p.setKnowledgestr(kstr.toString().substring(1));
    }

    private void page2db(Select p, TSelect t) {
        BeanUtils.copyProperties(p, t);
        // 公共字段
        ECommonField cf = new ECommonField();
        cf.setRank(p.getRank());
        cf.setCtime(p.getCtime());
        cf.setMtime(p.getMtime());
        cf.setStatus(p.getStatus());
        t.setCommonfield(cf);
    }

    // 试题的知识点，父知识点也属于它的知识点。
    private void getKnowledgeSet(Set<TQuestionKnowledge> qkset, TSelect t, String kid){
        if(StringUtils.isNotBlank(kid)){
            TKnowledge k = kdao.getById(TKnowledge.class, kid);
            TQuestionKnowledge qk = new TQuestionKnowledge();
            qk.setKnowledge(k);
            qk.setQuestion(t);
            qkset.add(qk);
            if(k.getParent()!=null){
                getKnowledgeSet(qkset, t, k.getParent().getId());
            }
        }
    }
}
