package com.ss.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ss.dao.CrediantialDao;
import com.ss.entity.CredentialMaster;


@Repository
@Transactional
public class CredentialDaoImpl  implements CrediantialDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer saveCrediantial(CredentialMaster credentialMaster) {
        try {
            entityManager.persist(credentialMaster);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public CredentialMaster getByUsername(String username) {
        String jpql = "SELECT cm FROM CredentialMaster cm WHERE cm.username = :username";
        return entityManager.createQuery(jpql, CredentialMaster.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}









//@Repository
//@Transactional
//public class CredentialDaoImpl  implements CrediantialDao{
//	
//	
////	@Autowired
////	private SessionFactory sessionFactory;
//
//	@Override
//	public Integer saveCrediantial(CredentialMaster credentialMaster) {
//		try {
//			sessionFactory.getCurrentSession().save(credentialMaster);
//			return 1;
//
//		} catch (Exception e) {
//			return 0;
//		}
//	}
//
//	public CredentialMaster getUsername(String username) {
//		try { 
//		Session session = sessionFactory.getCurrentSession();
//			Criteria criteria = session.createCriteria(CredentialMaster.class);
//			criteria.createAlias("employee", "e"); 
//			criteria.add(Restrictions.eq("username", username));
//			criteria.add(Restrictions.eq("e.isActive", true)); 
//
//			return (CredentialMaster) criteria.uniqueResult();
//		} catch (Exception e) {
//		
//			e.printStackTrace();
//			return null; 
//		}
//	}
//
//}
