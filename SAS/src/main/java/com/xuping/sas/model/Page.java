package com.xuping.sas.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 对分页的基本数据进行一个简单的封装 
 */  
@SuppressWarnings("serial")
public class Page<T> implements Serializable{  
	
    private int pageCurrent = 1;//页码，默认是第一页  
    private int pageSize = 30;//每页显示的记录数，默认是30 
    private int totalRecord;//总记录数  
    private int total;//总页数  
    private List<T> list;//对应的当前页记录  
    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象  
    private String orderField;//排序字段
    private String orderDirection;//排序方向
    public String getOrderField() {
		return orderField;
	}

	public Page<T> setOrderField(String orderField) {
		this.orderField = orderField==null||orderField.equals("orderField")?null:orderField;
		return this;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public Page<T> setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection==null||orderDirection.equals("orderDirection")?null:orderDirection;
		return this;
	}

	public int getPageCurrent() {  
       return pageCurrent;  
    }  
   
    public Page<T> setPageCurrent(Integer pageCurrent) {  
       this.pageCurrent = pageCurrent==null?1:pageCurrent;  
       return this;
    }  
   
    public int getPageSize() {  
       return pageSize;  
    }  
   
    public Page<T> setPageSize(Integer pageSize) {  
       this.pageSize = pageSize==null?30:pageSize;  
       return this;
    }  
   
    public int getTotalRecord() {  
       return totalRecord;  
    }  
   
    public Page<T> setTotalRecord(int totalRecord) {  
       this.totalRecord = totalRecord;  
       //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。  
       int totalPage = total%pageSize==0 ? total/pageSize : total/pageSize + 1;  
       this.setTotal(totalPage);  
       return this;
    }  
   
    public int getTotal() {  
       return total;  
    }  
   
    public void setTotal(int totalPage) {  
       this.total = totalPage;  
    }  
   
    public List<T> getList() {  
       return list;  
    }  
   
    public Page<T> setList(List<T> list) {  
       this.list = list;  
       return this;
    }  
    
    public Map<String, Object> getParams() {  
       return params;  
    }  
     
    public Page<T> setParams(Map<String, Object> params) {  
       this.params = params;
       return this;
    }  
   
    @Override  
    public String toString() {
       StringBuilder builder = new StringBuilder();  
       builder.append("Page [pageCurrent=").append(pageCurrent).append(", pageSize=")  
              .append(pageSize).append(", results=").append(list).append(  
                     ", totalPage=").append(total).append(  
                     ", totalRecord=").append(total).append("]");  
       return builder.toString();
    }
	public Page(Integer pageCurrent, Integer pageSize) {
		super();
		if(pageCurrent!=null ){
			this.pageCurrent = pageCurrent;
		}
		if(pageSize!=null){
			this.pageSize = pageSize;
		}
	}

	public Page() {
		super();
	}
}