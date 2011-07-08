package com.indus.dao.hibernate;

// Generated 09-Jul-2011 00:48:36 by Hibernate Tools 3.4.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Orderitems.
 * @see com.indus.dao.hibernate.Orderitems
 * @author Hibernate Tools
 */
public class OrderitemsHome {

	private static final Log log = LogFactory.getLog(OrderitemsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Orderitems transientInstance) {
		log.debug("persisting Orderitems instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Orderitems instance) {
		log.debug("attaching dirty Orderitems instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orderitems instance) {
		log.debug("attaching clean Orderitems instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Orderitems persistentInstance) {
		log.debug("deleting Orderitems instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orderitems merge(Orderitems detachedInstance) {
		log.debug("merging Orderitems instance");
		try {
			Orderitems result = (Orderitems) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Orderitems findById(com.indus.dao.hibernate.OrderitemsId id) {
		log.debug("getting Orderitems instance with id: " + id);
		try {
			Orderitems instance = (Orderitems) sessionFactory
					.getCurrentSession().get(
							"com.indus.dao.hibernate.Orderitems", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Orderitems instance) {
		log.debug("finding Orderitems instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.indus.dao.hibernate.Orderitems")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
