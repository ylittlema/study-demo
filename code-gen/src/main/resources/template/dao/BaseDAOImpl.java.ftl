package ${packageName}.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import ${packageName}.model.ResultPage;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {

	public void save(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public void merge(Object obj) {
		getHibernateTemplate().merge(obj);
	}

	public Object get(Class clazz, String id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public void lock(Object obj) {
		getHibernateTemplate().lock(obj, LockMode.UPGRADE);
	}

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public void evict(Object obj) {
		getHibernateTemplate().evict(obj);
	}

	public int countByCriteria(final DetachedCriteria dc) {
		return ((Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria c = dc.getExecutableCriteria(session);
						c.setProjection(Projections.projectionList().add(
								Projections.rowCount()));
						return c.uniqueResult();
					}
				}, true)).intValue();
	}

	public List getListByCriteria(final DetachedCriteria dc) {
		return getListByCriteria(dc, 1, Integer.MAX_VALUE);
	}

	public List getListByCriteria(final DetachedCriteria dc,
			final int currentPage, final int pageSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				try {
					Criteria c = dc.getExecutableCriteria(session);
					c.setFirstResult((currentPage - 1) * pageSize)
							.setMaxResults(pageSize);
					return c.list();
				} catch (Exception e) {
					return new ArrayList();
				}
			}
		}, true);
	}

	public ResultPage getResultPageByCriteria(ResultPage resultPage) {
		if (resultPage.getCurrentPage() < 1)
			resultPage.setCurrentPage(1);
		int totalRecord = countByCriteria(resultPage.getDetachedCriteria());
		resultPage.setTotalRecord(totalRecord);
		if (resultPage.getPageSize() < 1)
			resultPage.setPageSize(ResultPage.PAGE_SIZE);
		int totalPage = totalRecord % resultPage.getPageSize() == 0 ? totalRecord
				/ resultPage.getPageSize()
				: totalRecord / resultPage.getPageSize() + 1;
		resultPage.setTotalPage(totalPage);

		if (resultPage.getCurrentPage() < 1)
			resultPage.setCurrentPage(1);
		else if (resultPage.getCurrentPage() > resultPage.getTotalPage())
			resultPage.setCurrentPage(resultPage.getTotalPage());
		resultPage.getDetachedCriteria().setProjection(null);
		resultPage.getDetachedCriteria().setResultTransformer(
				CriteriaSpecification.ROOT_ENTITY);
		Iterator it = resultPage.getOrders().iterator();
		while (it.hasNext())
			resultPage.getDetachedCriteria().addOrder((Order) it.next());
		resultPage.setResult(getListByCriteria(
				resultPage.getDetachedCriteria(), resultPage.getCurrentPage(),
				resultPage.getPageSize()));
		return resultPage;
	}

}
