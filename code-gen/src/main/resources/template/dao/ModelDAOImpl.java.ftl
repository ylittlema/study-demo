package ${modelDefinition.packageName}.dao;

import java.util.List;
import java.sql.SQLException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ${modelDefinition.packageName}.model.${modelDefinition.className};
import org.springframework.orm.hibernate3.HibernateCallback;

public class ${modelDefinition.className}DAOImpl extends BaseDAOImpl implements ${modelDefinition.className}DAO {

	public ${modelDefinition.className} get${modelDefinition.className}ByName(final String name) {
		return (${modelDefinition.className}) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = session.createCriteria(${modelDefinition.className}.class);
				c.add(Restrictions.eq("name", name));
				return c.uniqueResult();
			}
		});
	}

	public List getAll${modelDefinition.className}(){
		return getHibernateTemplate().find("FROM ${modelDefinition.className}");
	}
	
}
