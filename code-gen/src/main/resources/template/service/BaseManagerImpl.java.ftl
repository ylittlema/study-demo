package ${packageName}.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import ${packageName}.dao.BaseDAO;
import ${packageName}.model.ResultPage;

public class BaseManagerImpl implements BaseManager {
	BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	public void save(Object obj) {
		dao.save(obj);
	}

	public void merge(Object obj) {
		dao.merge(obj);
	}

	public Object get(Class clazz, String id) {
		return dao.get(clazz, id);
	}

	public void lock(Object obj) {
		dao.lock(obj);
	}

	public void delete(Object obj) {
		dao.delete(obj);
	}
	
	public void evict(Object obj) {
		dao.evict(obj);
	}

	public int countByCriteria(DetachedCriteria dc) {
		return dao.countByCriteria(dc);
	}

	public List getListByCriteria(DetachedCriteria dc) {
		return dao.getListByCriteria(dc);
	}

	public List getListByCriteria(DetachedCriteria dc, int currentPage,
			int pageSize) {
		return dao.getListByCriteria(dc, currentPage, pageSize);
	}

	public ResultPage getResultPageByCriteria(ResultPage resultPage) {
		return dao.getResultPageByCriteria(resultPage);
	}
}
