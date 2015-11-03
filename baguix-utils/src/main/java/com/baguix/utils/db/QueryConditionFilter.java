package com.baguix.utils.db;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;


/**
 * HQL查询条件过滤器，用于添加where条件和排序，过滤结果集
 * 
 * 举例：
 * S_title_LK_关于_ASC_AN;D_ctime_BE_2014-07-10_ASC_;
 * 
 * 连出来的HQL是：
 * and t.title like '%:title%' and ctime <2014-07-10 order by t.title asc, t.ctime asc
 * 
 * 格式说明：
 * 类型_属性_关系_值_排序_逻辑;
 *
 * 类型：
 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
 * 
 * 关系操作符：
 * EQ 相等、 NE 不等、 LT 小于、 GT 大于、 LE 小于或等于、 GE 大于或等于、 LK 模糊、BE 早于、AF 晚于。
 * 
 * 排序：
 * ASC 递增、 DESC 递减。
 * 
 * 逻辑：
 * AN 与、OO 或。
 * 
 * @author Scott
 * 
 */
public class QueryConditionFilter {
	// 参数
	private Map<String, Object> params = new HashMap<String, Object>();
	// HQL
	private StringBuffer hql = new StringBuffer();
	private StringBuffer hqlorder = new StringBuffer();

	/**
	 * 默认构造器
	 */
	public QueryConditionFilter() {

	}

	/**
	 * 转换HQL操作符
	 * 
	 * @param operator
	 * @return
	 */
	private String getSqlOperator(String operator) {
		String o = operator.trim().toUpperCase();
		if (o.equals("EQ")) {
			return " = ";
		}
		if (o.equals("NE")) {
			return " != ";
		}
		if (o.equals("LT")) {
			return " < ";
		}
		if (o.equals("GT")) {
			return " > ";
		}
		if (o.equals("LE") || o.equals("BE")) {
			return " <= ";
		}
		if (o.equals("GE") || o.equals("AF")) {
			return " >= ";
		}
		if (o.equals("LK")) {
			return " like ";
		}
		return "";
	}

	/**
	 * 获得添加过滤字段后的HQL（Where部分）
	 * 
	 * @return
	 */
	public String getWhereHql() {
//		String s = hql.toString().trim();
//		if (!"".equals(s) ){
//			hql.append(" order by "+hqlorder.substring(0, hqlorder.length()-1));
//		}
		return hql.toString();
	}
	
	public String getOrderHql(){
		if(StringUtils.isEmpty(hqlorder.toString())){
			return hqlorder.toString().substring(0,hqlorder.length()-1);
		}
		return "";
	}
	
	/**
	 * 获得过滤字段参数和值
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 添加过滤
	 * 
	 * @param query 查询式
	 */
	public void addFilter(String query) {
		if (query != null) {
			String[] list = query.split("#");
			int i = 0;
			for(String s : list){
				i++;
				//类型
				String datatype = s.substring(0,s.indexOf("_"));
				s = s.substring(s.indexOf("_")+1,s.length());
				//属性
				String column = s.substring(0,s.indexOf("_"));
				s = s.substring(s.indexOf("_")+1,s.length());
				//关系操作符
				String reloperator = s.substring(0,s.indexOf("_"));
				s = s.substring(s.indexOf("_")+1,s.length());
				//数据值
				String value = s.substring(0,s.indexOf("_"));
				s = s.substring(s.indexOf("_")+1,s.length());
				//排序值
				String order = s.substring(0,s.indexOf("_"));
				s = s.substring(s.indexOf("_")+1,s.length());
				if( StringUtils.isEmpty(order) ){
					order = "asc";
				}
				//逻辑值
				String logic = s;
				if(logic.equals("AN")||logic=="AN"){
					logic = " and ";
				}
				else
				if(logic.equals("OO")||logic=="OO"){
					logic = " or ";
				}
				else{
					logic = "";
				}
				//随机生成一个UUID的参数名
				String placeholder = UUID.randomUUID().toString().replace("-", "");
				if(i==1){
					hql.append(" and ");
				}
				hql.append("t." + column + " " + getSqlOperator(reloperator) + " :c" + placeholder + " " + logic + " ");
				params.put("c" + placeholder, getObjValue(datatype, reloperator, value));
				hqlorder.append(" t." + column + " " + order + ",");
			}
		}
	}

	/**
	 * 将String值转换成Object
	 * 
	 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
	 * 
	 * @param valueType
	 * @param operator
	 * @param value
	 * @return
	 */
	private Object getObjValue(String valueType, String operator, String value) {
		if (valueType.equals("S")) {
			if (operator.equals("LK")) {
				value = "%%" + value + "%%";
			} else if (operator.equals("RLK")) {
				value =value + "%%";
			} else if (operator.equals("LLK")) {
				value = "%%" + value;
			}
			return value;
		}
		if (valueType.equals("L")) {
			return Long.parseLong(value);
		}
		if (valueType.equals("I")) {
			return Integer.parseInt(value);
		}
		if (valueType.equals("D")) {
			try {
				return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (valueType.equals("ST")) {
			return Short.parseShort(value);
		}
		if (valueType.equals("BD")) {
			return BigDecimal.valueOf(Long.parseLong(value));
		}
		if (valueType.equals("FT")) {
			return Float.parseFloat(value);
		}
		return null;
	}

	public String getHql() {
		return hql.toString();
	}

	public void setHql(String hql) {
		this.hql.append(hql);
	}
}
