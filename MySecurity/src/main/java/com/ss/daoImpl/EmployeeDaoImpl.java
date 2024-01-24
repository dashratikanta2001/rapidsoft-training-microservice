package com.ss.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.ss.dao.EmployeeDao;
import com.ss.dto.FilterDto;
import com.ss.dto.PaginateRequestDto;
import com.ss.entity.Employee;
import com.ss.response.Response;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

//	@Autowired
//	public SessionFactory sessionFactory;

	@Autowired
	private EntityManager entityManager;
	
//	@Override
//	public Employee createEmployee(Employee employee) {
//	    try {
//	        Session session = sessionFactory.getCurrentSession();
//	        session.save(employee);
//	        return employee; 
//	    } catch (Exception e) {
//	        e.printStackTrace(); 
//	        return null;
//	    }
//	}

//	@Override
//	public List<Employee> getAll(Integer companyId,String name,String empCode) {
//
//		Session session = sessionFactory.getCurrentSession();
//		Criteria criteria = session.createCriteria(Employee.class);
//
//		criteria.add(Restrictions.eq("company.id",companyId ));
//		criteria.add(Restrictions.eq("firstname", name));
//
//		criteria.add(Restrictions.eq("isActive", true));
//		return criteria.list();
//	}

//	@Override
//	public Employee getEmployeeByPnone(String phone) {
//
////		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
////		criteria.add(Restrictions.eq("phoneNumber", phone));
////
////		return (Employee) criteria.uniqueResult();
//		
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//		
//	}

	@Override
	public List<Employee> getAll(Integer companyId, String name, String empCode) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);

		List<Predicate> predicates = new ArrayList<>();

		if (companyId != null && companyId > 0) {

			predicates.add(criteriaBuilder.equal(root.get("company").get("id"), companyId));

		}
		if (name != null && !name.isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("firstname"), name));
		}

		if (empCode != null && !empCode.isEmpty()) {
			predicates.add(criteriaBuilder.equal(root.get("empCode"), empCode));
		}

		predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@Override
	public List<Employee> getAllEmployee(FilterDto filterDto) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);

		List<Predicate> predicates = new ArrayList<>();

		if (filterDto.getCompanyId() != null) {

			predicates.add(criteriaBuilder.equal(root.get("company").get("id"), filterDto.getCompanyId()));

		}
		if (filterDto.getName() != null && !filterDto.getName().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("firstname"), filterDto.getName()));
		}

		if (filterDto.getEmpCode() != null && !filterDto.getEmpCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(root.get("empCode"), filterDto.getEmpCode()));
		}

		predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();

	}

	@Override
	public Page<Employee> getAllEmployees(PaginateRequestDto paginationRequest) {

		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);

			List<Predicate> predicates = new ArrayList<>();

			if (paginationRequest.getName() != null && !paginationRequest.getName().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("firstname"), paginationRequest.getName()));
			}

			if (paginationRequest.getEmpCode() != null && !paginationRequest.getEmpCode().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("empCode"), paginationRequest.getEmpCode()));
			}

			predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));

			TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);

			query.setFirstResult(paginationRequest.getPage() * paginationRequest.getSize());

			query.setMaxResults(paginationRequest.getSize());

			List<Employee> results = query.getResultList();

			int totalCount = getTotalCompanyCount(paginationRequest);
			if(paginationRequest.getSize()<0) {
				return (Page<Employee>) new Response("Page Size Cant be Zoro",null,HttpStatus.BAD_REQUEST.value());
			}

			return new PageImpl<>(results, PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()),
					totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private int getTotalCompanyCount(PaginateRequestDto paginationRequest) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);

		List<Predicate> predicates = new ArrayList<>();

		if (paginationRequest.getName() != null && !paginationRequest.getName().isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("firstname"), paginationRequest.getName()));
		}

		if (paginationRequest.getEmpCode() != null && !paginationRequest.getEmpCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(root.get("empCode"), paginationRequest.getEmpCode()));
		}

		predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList().size();

	}

	

	@Override
	public Employee getEmployeeByPnone(String phone) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);

		Predicate phonePredicate = criteriaBuilder.equal(root.get("phone"), phone);

		criteriaQuery.where(phonePredicate);

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
//	
	


}
