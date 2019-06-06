package cn.cyit.traffic.comm;

import java.util.ArrayList;
import java.util.List;
/**
 * 
* @ClassName: PageHelper
* @Description: 分页插件泛型
* @author hean
* @date 2018年9月6日
*
* @param <T>
 */
public class PageHelper<T> {
	//实体类集合
    private List<T> rows = new ArrayList<T>();
    //数据总条数
    private int total;
 
    public PageHelper() {
        super();
    }
 
    public List<T> getRows() {
        return rows;
    }
 
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }

}
