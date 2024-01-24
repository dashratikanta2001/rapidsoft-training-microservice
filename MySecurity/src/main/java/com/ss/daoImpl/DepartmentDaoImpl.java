package com.ss.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.dao.DepartmentDao;
import com.ss.entity.Department;



@Repository
@Transactional
public class DepartmentDaoImpl  implements DepartmentDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public Department save(Department department) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(department);
			return department;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Department getByCompanyIdAndDepartmentName(Integer companyId, String departmentName) {
		try {
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Department.class);
			criteria.createAlias("company", "c");
			criteria.add(Restrictions.eq("c.id", companyId));
			criteria.add(Restrictions.eq("name", departmentName));
			return (Department) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
