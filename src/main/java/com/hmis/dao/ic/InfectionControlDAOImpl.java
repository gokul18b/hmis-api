package com.hmis.dao.ic;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hmis.model.MstInfectControlDevices;
import com.hmis.model.MstUsers;
import com.hmis.model.trn_infect_control_bundle_details;
import com.hmis.model.trn_infect_control_device_hdr;
import com.hmis.utils.TokenGenerator;

@Repository
public class InfectionControlDAOImpl implements InfectionControlDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<?> login(MstUsers mstUsers) {
		Session session = sessionFactory.getCurrentSession();

		Md5PasswordEncoder md5 = new Md5PasswordEncoder();

		String hql = "SELECT users.id,users.displayname, dept.dept_name,desi.design_name FROM mst_users users "
				+ "JOIN mst_employees as emp ON emp.id=users.userref_id "
				+ "JOIN mst_departments as dept ON dept.id=emp.mst_department_id "
				+ "JOIN mst_designations as desi ON desi.id=emp.mst_designation_id "
				+ "WHERE users.username = ? and users.password= ?";

		Query<?> query = session.createQuery(hql);
		query.setParameter(0, mstUsers.getUsername());
		query.setParameter(1, md5.encodePassword(mstUsers.getPassword(), null));
		System.out.println(hql);

		List<?> results = query.getResultList();
		return results;
	}

	@Override
	public String token_generation(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		TokenGenerator token_generator = new TokenGenerator();
		String token = token_generator.nextString();

		Criteria criteria = session.createCriteria(MstUsers.class).add(Restrictions.eq("app_login_token", token));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();

		if (count == 0) {
			String hql = "update mst_users mst set mst.app_login_token=? where mst.id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, token);
			query.setParameter(1, id);
			query.executeUpdate();

		} else {
			token_generation(id);
		}

		return token;
	}

	@Override
	public List<MstInfectControlDevices> get_ic_devices() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MstInfectControlDevices.class);
		criteria.add(Restrictions.eq("status", 1));
		List<MstInfectControlDevices> infectionControlDevicesList = criteria.list();
		return infectionControlDevicesList;
	}

	@Override
	public List<Object[]> get_ic_bundle(Integer device_id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT bundle.id, bundle.bundle_name,bundle.mst_infect_control_devices_id From mst_infect_control_bundle bundle where bundle.mst_infect_control_devices_id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, device_id);
		List<Object[]> results = query.list();
		return results;
	}

	@Transactional
	@Override
	public Integer saveInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(trn_infect_control_device_hdr.class);

			session.save(trn_infect_control_device_hdr);

			List<trn_infect_control_bundle_details> trn_infect_control_bundle_details_list = trn_infect_control_device_hdr
					.getTrn_infect_control_bundle_details();
			Iterator<trn_infect_control_bundle_details> iterator = trn_infect_control_bundle_details_list.iterator();

			while (iterator.hasNext()) {
				trn_infect_control_bundle_details trn_infect_control_bundle_details = iterator.next();
				trn_infect_control_bundle_details.setTrn_infect_control_device_hdr(trn_infect_control_device_hdr);
				session.save(trn_infect_control_bundle_details);
			}

			return 1;
		} catch (HibernateException e) {
			return null;
		}

	}

	@Override
	public Integer updateInfectionControl(trn_infect_control_device_hdr trn_infect_control_device_hdr, Integer hdr_id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			trn_infect_control_device_hdr newHeaderObject = trn_infect_control_device_hdr;

			trn_infect_control_device_hdr finalHeaderObject = session.get(trn_infect_control_device_hdr.getClass(),
					hdr_id);

			finalHeaderObject.setVisit_id(newHeaderObject.getVisit_id());
			finalHeaderObject.setMst_infect_control_device_id(newHeaderObject.getMst_infect_control_device_id());
			finalHeaderObject.setInfect_control_date(newHeaderObject.getInfect_control_date());
			finalHeaderObject.setTemperature(newHeaderObject.getTemperature());
			finalHeaderObject.setInsertion_date(newHeaderObject.getInsertion_date());
			finalHeaderObject.setRemoval_date(newHeaderObject.getRemoval_date());
			session.update(finalHeaderObject);

			List<trn_infect_control_bundle_details> newBundleList = trn_infect_control_device_hdr
					.getTrn_infect_control_bundle_details();

			Criteria bundleCriteria = session.createCriteria(trn_infect_control_bundle_details.class);
			Criteria hdr_criteria = bundleCriteria.createCriteria("trn_infect_control_device_hdr");
			hdr_criteria.add(Restrictions.eq("id", hdr_id));

			List<trn_infect_control_bundle_details> previousBundlesList = bundleCriteria.list();

			for (int i = 0; i < previousBundlesList.size(); i++) {
				trn_infect_control_bundle_details finalBundleObject = previousBundlesList.get(i);
				trn_infect_control_bundle_details newBundleObject = newBundleList.get(i);
				finalBundleObject.setId(newBundleObject.getId());
				finalBundleObject.setTrn_infect_control_device_hdr(finalHeaderObject);
				finalBundleObject.setMst_infect_control_bundle_id(newBundleObject.getMst_infect_control_bundle_id());
				finalBundleObject.setOption_value(newBundleObject.getOption_value());
				finalBundleObject.setRemarks(newBundleObject.getRemarks());

				session.update(finalBundleObject);
			}

			return 1;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return 0;
		}

	}

	@Override
	public List<trn_infect_control_device_hdr> get_complete_ic(Integer device_id, Integer visit_id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(trn_infect_control_device_hdr.class)
					.add(Restrictions.eq("visit_id", visit_id))
					.add(Restrictions.eq("mst_infect_control_device_id", device_id))
					.add(Restrictions.isNotNull("removal_date"));
			Object object = criteria.list();
			List<trn_infect_control_device_hdr> header = (List<trn_infect_control_device_hdr>) object;
			return header;
		} catch (HibernateException e) {
			return null;
		}

	}

	@Override
	public trn_infect_control_device_hdr get_incomplete_ic(Integer device_id, Integer visit_id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(trn_infect_control_device_hdr.class)
					.add(Restrictions.eq("visit_id", visit_id))
					.add(Restrictions.eq("mst_infect_control_device_id", device_id))
					.add(Restrictions.isNull("removal_date"));
			Object object = criteria.uniqueResult();
			trn_infect_control_device_hdr incomplete_details = (trn_infect_control_device_hdr) object;
			return incomplete_details;
		} catch (HibernateException e) {
			return null;
		}
	}

}
