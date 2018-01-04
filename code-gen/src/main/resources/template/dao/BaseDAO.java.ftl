package ${packageName}.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import ${packageName}.model.ResultPage;

public interface BaseDAO {

	public void save(Object obj);

	public void merge(Object obj);

	public Object get(Class clazz, String id);

	public void lock(Object obj);

	public void delete(Object obj);
	
	public void evict(Object obj);

	public int countByCriteria(DetachedCriteria dc);

	public List getListByCriteria(DetachedCriteria dc);

	public List getListByCriteria(DetachedCriteria dc, int currentPage,
			int pageSize);

	public ResultPage getResultPageByCriteria(ResultPage resultPage);
}
