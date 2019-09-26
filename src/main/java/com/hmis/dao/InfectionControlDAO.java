package com.hmis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hmis.entity.MstAntibiotics;
import com.hmis.entity.MstInfectControlDevices;
import com.hmis.entity.MstUsers;
import com.hmis.entity.TrnIcAntibiotics;
import com.hmis.entity.TrnInfectControlDeviceHdr;
import com.hmis.entity.trn_infect_control_bundle_details;
import com.hmis.entity.trn_infect_control_bundle_details_daily;
import com.hmis.entity.trn_infect_control_daily_hdr;
import com.hmis.response.get_ic_summary.InfectionDeviceSummary;
import com.hmis.response.ic_patient_report.DailyBundle;
import com.hmis.response.ic_patient_report.DailyBundleDetails;
import com.hmis.response.ic_patient_report.InsertionBundle;
import com.hmis.response.ic_patient_report.InsertionBundleDetails;
import com.hmis.utils.TokenGenerator;

@Repository
public class InfectionControlDAO {

	@Autowired
	SessionFactory sessionFactory;

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

	public List<MstInfectControlDevices> get_ic_devices() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MstInfectControlDevices.class);
		criteria.add(Restrictions.eq("status", 1));
		criteria.addOrder(Order.asc("id"));
		List<MstInfectControlDevices> infectionControlDevicesList = criteria.list();

		return infectionControlDevicesList;
	}

	public List<Object[]> get_ic_bundle(Integer device_id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT bundle.id, bundle.bundle_name,bundle.mst_infect_control_devices_id From mst_infect_control_bundle bundle where bundle.mst_infect_control_devices_id=? order by bundle.id";
		Query query = session.createQuery(hql);
		query.setParameter(0, device_id);
		List<Object[]> results = query.list();
		return results;
	}

	public List<Object[]> get_ic_bundle_daily(Integer device_id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT bundle.id, bundle.bundle_name,bundle.mst_infect_control_devices_id From mst_infect_control_bundle_daily bundle where bundle.mst_infect_control_devices_id=? order by bundle.id";
		Query query = session.createQuery(hql);
		query.setParameter(0, device_id);
		List<Object[]> results = query.list();
		return results;
	}

	@Transactional
	public Integer saveInfectionControl(TrnInfectControlDeviceHdr trn_infect_control_device_hdr) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(trn_infect_control_device_hdr);
		try {
			Criteria criteria = session.createCriteria(TrnInfectControlDeviceHdr.class);

			session.save(trn_infect_control_device_hdr);

			trn_infect_control_daily_hdr trn_infect_control_daily_hdr = trn_infect_control_device_hdr
					.getTrn_infect_control_daily_hdr().get(0);
			trn_infect_control_daily_hdr.setTrn_infect_control_device_hdr(trn_infect_control_device_hdr);
			session.save(trn_infect_control_daily_hdr);

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

	@Transactional
	public Integer updateInfectionControl(TrnInfectControlDeviceHdr trn_infect_control_device_hdr, Integer hdr_id) {
		// System.out.println(trn_infect_control_device_hdr);
		Session session = sessionFactory.getCurrentSession();
		try {
			TrnInfectControlDeviceHdr new_trn_infect_control_device_hdr = session.get(TrnInfectControlDeviceHdr.class,
					hdr_id);
//			new_trn_infect_control_device_hdr
//					.setInfect_control_date(trn_infect_control_device_hdr.getInfect_control_date());
			new_trn_infect_control_device_hdr.setInsertion_date(trn_infect_control_device_hdr.getInsertion_date());
			new_trn_infect_control_device_hdr
					.setMst_infect_control_device_id(trn_infect_control_device_hdr.getMst_infect_control_device_id());
			new_trn_infect_control_device_hdr.setRemoval_date(trn_infect_control_device_hdr.getRemoval_date());
			new_trn_infect_control_device_hdr.setVisit_id(trn_infect_control_device_hdr.getVisit_id());
			session.saveOrUpdate(new_trn_infect_control_device_hdr);

			if (trn_infect_control_device_hdr.getTrn_infect_control_daily_hdr().size() != 0) {
				trn_infect_control_daily_hdr new_trn_infect_control_daily_hdr = trn_infect_control_device_hdr
						.getTrn_infect_control_daily_hdr().get(0);
				new_trn_infect_control_daily_hdr.setTrn_infect_control_device_hdr(new_trn_infect_control_device_hdr);
				session.save(new_trn_infect_control_daily_hdr);

				List<trn_infect_control_bundle_details_daily> new_trn_infect_control_bundle_details_daily = new_trn_infect_control_daily_hdr
						.getTrn_infect_control_bundle_daily_details();

				Iterator<trn_infect_control_bundle_details_daily> iterator = new_trn_infect_control_bundle_details_daily
						.iterator();
				while (iterator.hasNext()) {
					trn_infect_control_bundle_details_daily trn_infect_control_bundle_details_daily = iterator.next();
					trn_infect_control_bundle_details_daily
							.setTrn_infect_control_daily_hdr(new_trn_infect_control_daily_hdr);
					session.save(trn_infect_control_bundle_details_daily);
				}

			}
			return 1;
		} catch (HibernateException e) {
			return 0;
		}

	}

	public List<TrnInfectControlDeviceHdr> get_complete_ic(Integer device_id, Integer visit_id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(TrnInfectControlDeviceHdr.class)
					.add(Restrictions.eq("visit_id", visit_id))
					.add(Restrictions.eq("mst_infect_control_device_id", device_id))
					.add(Restrictions.isNotNull("removal_date"));
			Object object = criteria.list();
			List<TrnInfectControlDeviceHdr> header = (List<TrnInfectControlDeviceHdr>) object;
			return header;
		} catch (HibernateException e) {
			return null;
		}

	}

	public TrnInfectControlDeviceHdr get_incomplete_ic(Integer device_id, Integer visit_id) {
		System.out.println("incomplete");
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(TrnInfectControlDeviceHdr.class)
					.add(Restrictions.eq("visit_id", visit_id))
					.add(Restrictions.eq("mst_infect_control_device_id", device_id))
					.add(Restrictions.isNull("removal_date"));
			Object object = criteria.uniqueResult();
			TrnInfectControlDeviceHdr incomplete_details = (TrnInfectControlDeviceHdr) object;
//			System.out.println("incomplete_details-->"+incomplete_details);
			return incomplete_details;
		} catch (HibernateException e) {
			System.out.println(e);
			return null;
		}
	}

	public InsertionBundle getInserionReport(Integer hdr_id) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		try {
			InsertionBundle insertionBundle = new InsertionBundle();

			String insertion_hql = "SELECT Dhdr.bundle_date,Dhdr.unit,Dhdr.temperature ,hdr.insertion_date,hdr.removal_date "
					+ "FROM trn_infect_control_daily_hdr Dhdr "
					+ "LEFT JOIN trn_infect_control_device_hdr hdr ON(hdr.id=Dhdr.trn_infect_control_device_hdr) "
					+ "where Dhdr.id=(SELECT min(id) from trn_infect_control_daily_hdr where  trn_infect_control_device_hdr_id= ? )";

			Query insertion_query = session.createQuery(insertion_hql);
			insertion_query.setParameter(0, hdr_id);
			List<Object[]> insertion_results = insertion_query.list();

			for (Object[] result : insertion_results) {
				insertionBundle.setBundle_date((Date) result[0]);
				insertionBundle.setUnit((String) result[1]);
				insertionBundle.setTemperature((String) result[2]);
				insertionBundle.setInsertion_date((Date) result[3]);
				insertionBundle.setRemoval_date((Date) result[4]);

			}

			String insertion_details_hql = "SELECT mst_bundle.bundle_name,details.option_value,details.remarks FROM trn_infect_control_device_hdr device_hdr "
					+ "RIGHT JOIN trn_infect_control_bundle_details details ON details.trn_infect_control_device_hdr=device_hdr.id "
					+ "LEFT JOIN mst_infect_control_bundle mst_bundle ON mst_bundle.id=details.mst_infect_control_bundle_id where device_hdr.id=?";

			Query query = session.createQuery(insertion_details_hql);
			query.setParameter(0, hdr_id);
			List<Object[]> results = query.list();

			List<InsertionBundleDetails> insertionBundleDetailsList = new ArrayList<InsertionBundleDetails>();
			for (Object[] row : results) {
				InsertionBundleDetails insertionBundleDetails = new InsertionBundleDetails();
				insertionBundleDetails.setBundle_name((String) row[0]);
				insertionBundleDetails.setOption_value((String) row[1]);
				insertionBundleDetails.setRemarks((String) row[2]);
				insertionBundleDetailsList.add(insertionBundleDetails);
			}
			insertionBundle.setInsertion_details(insertionBundleDetailsList);
			if (insertion_results.size() != 0 && insertion_results != null) {
				return insertionBundle;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public List<DailyBundle> getDailyReport(Integer hdr_id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			List<DailyBundle> dailyBundleList = new ArrayList<DailyBundle>();

			String insertion_hql = "SELECT Dhdr.id,Dhdr.bundle_date,Dhdr.unit,Dhdr.temperature "
					+ "FROM trn_infect_control_daily_hdr Dhdr "
					+ "where Dhdr.id!=(SELECT min(id) from trn_infect_control_daily_hdr where  trn_infect_control_device_hdr="
					+ hdr_id + ") and Dhdr.trn_infect_control_device_hdr=" + hdr_id;

			Query insertion_query = session.createQuery(insertion_hql);
//			insertion_query.setParameter(0, hdr_id);
//			insertion_query.setParameter(1, hdr_id);
			List<Object[]> insertion_results = insertion_query.list();
			for (Object[] result : insertion_results) {
				DailyBundle dailyBundle = new DailyBundle();
				dailyBundle.setBundle_date((Date) result[1]);
				dailyBundle.setUnit((String) result[2]);
				dailyBundle.setTemperature((String) result[3]);

				String daily_bundle_hql = "SELECT mst_bundle.bundle_name,daily_details.option_value,daily_details.remarks from trn_infect_control_daily_hdr daily_hdr\r\n"
						+ "LEFT JOIN trn_infect_control_bundle_details_daily daily_details ON(daily_details.trn_infect_control_daily_hdr=daily_hdr.id)\r\n"
						+ "LEFT JOIN mst_infect_control_bundle_daily mst_bundle ON(mst_bundle.id=daily_details.mst_infect_control_bundle_id)\r\n"
						+ "WHERE daily_hdr.id = ? ";

				Query daily_bundle_query = session.createQuery(daily_bundle_hql);
				daily_bundle_query.setParameter(0, result[0]);
				List<Object[]> daily_bundle_results = daily_bundle_query.list();
				List<DailyBundleDetails> dailyBundleDetailsList = new ArrayList<>();
				for (Object[] daily_bundle_result : daily_bundle_results) {
					DailyBundleDetails dailyBundleDetails = new DailyBundleDetails();
					dailyBundleDetails.setBundle_name((String) daily_bundle_result[0]);
					dailyBundleDetails.setOption_value((String) daily_bundle_result[1]);
					dailyBundleDetails.setRemarks((String) daily_bundle_result[2]);
					dailyBundleDetailsList.add(dailyBundleDetails);
				}
				dailyBundleList.add(dailyBundle);
				dailyBundle.setDetails(dailyBundleDetailsList);
			}

			return dailyBundleList;
		} catch (HibernateException e) {
			return null;
		}
	}

	public List<Object[]> getIcSummary(String from_date, String to_date) {
		Session session = sessionFactory.getCurrentSession();
		InfectionDeviceSummary infectionDeviceSummary = new InfectionDeviceSummary();

		String hql = "select \r\n" + "device.id,\r\n" + "device.device_name ,\r\n"
				+ "sum(case when trn_hdr.removal_date is null then DATE_PART('day', now() - trn_hdr.insertion_date)+1 \r\n"
				+ "else DATE_PART('day', trn_hdr.removal_date - trn_hdr.insertion_date)+1 end) \r\n"
				+ "from trn_infect_control_device_hdr trn_hdr\r\n"
				+ "right join mst_infect_control_devices device on(device.id=trn_hdr.mst_infect_control_device_id)\r\n"
				+ "group by device.device_name ,device.id order by id";

		hql = "select \r\n" + "   mhdr.id,\r\n" + "   mst_floors.id floor_id,\r\n"
				+ "   mst_blocks.block_name || ' - ' ||mst_floors.floor_name as floor_name\r\n"
				+ "   ,mhdr.device_name\r\n" + "   ,sum(case  when thdr.removal_date is null then DATE_PART('day','"
				+ to_date
				+ "' -  thdr.insertion_date)+1  else DATE_PART('day',  thdr.removal_date -  thdr.insertion_date)+1 end) as count\r\n"
				+ "from \r\n" + "    trn_infect_control_device_hdr thdr\r\n"
				+ "    left join mst_beds on(mst_beds.id=thdr.bed_id)\r\n"
				+ "    left join mst_rooms on (mst_rooms.id=mst_beds.mst_room_id)\r\n"
				+ "    left join mst_floors on(mst_floors.id=mst_rooms.mst_floor_id)\r\n"
				+ "    left join mst_blocks on(mst_blocks.id=mst_floors.mst_block_id)\r\n"
				+ "right join mst_infect_control_devices mhdr on(mhdr.id=thdr.mst_infect_control_device_id)  \r\n"
				+ "where thdr.insertion_date between '" + from_date + "' and '" + to_date + "'\r\n"
				+ "group by mhdr.device_name, \r\n" + "	mst_floors.id ,\r\n" + "	mhdr.id,\r\n"
				+ "	mst_blocks.block_name || ' - ' ||mst_floors.floor_name \r\n" + "order by  mhdr.id";

		Query query = session.createSQLQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getInsertionBundleSummary(String from_date, String to_date) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT mst_beds.mst_floor_id\r\n"
				+ "	,mst_blocks.block_name || ' ' || mst_floors.floor_name as floor_name\r\n"
				+ "	,mst_devices.device_name\r\n" + "	,count(DISTINCT trn_hdr.id) as total_count\r\n"
				+ "	,count(DISTINCT option_val.trn_infect_control_device_hdr_id) as issue_count\r\n"
				+ "FROM trn_infect_control_device_hdr trn_hdr\r\n"
				+ "LEFT JOIN mst_infect_control_devices mst_devices ON (mst_devices.id = trn_hdr.mst_infect_control_device_id)\r\n"
				+ "LEFT JOIN trn_infect_control_bundle_details trn_details ON (trn_details.trn_infect_control_device_hdr_id = trn_hdr.id)\r\n"
				+ "LEFT JOIN mst_beds mst_beds ON (mst_beds.id = trn_hdr.bed_id)\r\n"
				+ "LEFT JOIN mst_floors mst_floors ON (mst_floors.id = mst_beds.mst_floor_id)\r\n"
				+ "LEFT JOIN mst_blocks mst_blocks ON (mst_blocks.id = mst_floors.mst_block_id)\r\n" + "LEFT JOIN (\r\n"
				+ "	SELECT DISTINCT trn_det.trn_infect_control_device_hdr_id , 1 as val\r\n"
				+ "	FROM trn_infect_control_device_hdr AS trn_hdr\r\n"
				+ "	INNER JOIN trn_infect_control_bundle_details AS trn_det ON (trn_det.trn_infect_control_device_hdr_id = trn_hdr.id)\r\n"
				+ "	WHERE trn_det.option_value = 'No'\r\n" + "	order by 1\r\n"
				+ "	) AS option_val ON (option_val.trn_infect_control_device_hdr_id = trn_hdr.id)\r\n"
				+ "WHERE trn_hdr.insertion_date between '" + from_date + "' and '" + to_date + "'\r\n"
				+ "GROUP BY mst_beds.mst_floor_id\r\n" + "	,mst_blocks.block_name || ' ' || mst_floors.floor_name\r\n"
				+ "	,mst_devices.device_name\r\n";

		Query query = session.createSQLQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getDailyBundleSummary(String from_date, String to_date) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT \r\n" + "	mst_beds.mst_floor_id,\r\n"
				+ "	mst_blocks.block_name || '-' || mst_floors.floor_name as floor_name,\r\n"
				+ "	mst_dev.device_name,\r\n" + "	count(DISTINCT dly_hdr.id) as total_count,\r\n"
				+ "	count(DISTINCT option_value.trn_infect_control_daily_hdr_id) as issue_count	\r\n"
				+ "FROM trn_infect_control_device_hdr as dev_hdr\r\n"
				+ "LEFT JOIN trn_infect_control_daily_hdr as dly_hdr ON(dly_hdr.trn_infect_control_device_hdr_id=dev_hdr.id)\r\n"
				+ "LEFT JOIN trn_infect_control_bundle_details_daily as dly_det ON(dly_det.trn_infect_control_daily_hdr_id = dly_hdr.id)\r\n"
				+ "LEFT JOIN mst_beds as mst_beds ON(mst_beds.id=dev_hdr.bed_id)\r\n"
				+ "LEFT JOIN mst_floors as mst_floors ON(mst_floors.id=mst_beds.mst_floor_id)\r\n"
				+ "LEFT JOIN mst_blocks as mst_blocks ON(mst_blocks.id=mst_floors.mst_block_id)\r\n"
				+ "LEFT JOIN mst_infect_control_devices as mst_dev ON(mst_dev.id=dev_hdr.mst_infect_control_device_id)\r\n"
				+ "LEFT JOIN (\r\n" + "	SELECT DISTINCT trn_infect_control_daily_hdr_id,1 as val \r\n"
				+ "	FROM trn_infect_control_daily_hdr \r\n"
				+ "	INNER JOIN trn_infect_control_bundle_details_daily ON(trn_infect_control_bundle_details_daily.trn_infect_control_daily_hdr_id=trn_infect_control_daily_hdr.id)\r\n"
				+ "	WHERE trn_infect_control_bundle_details_daily.option_value ='No'\r\n"
				+ "	) as option_value ON(option_value.trn_infect_control_daily_hdr_id=dly_hdr.id)\r\n"
				+ "WHERE dly_hdr.bundle_type='M' and dev_hdr.insertion_date between '" + from_date + "' and '" + to_date
				+ "'"
				+ "GROUP BY mst_beds.mst_floor_id,mst_blocks.block_name || '-' || mst_floors.floor_name,mst_dev.device_name\r\n"
				+ "\r\n" + "";

		Query query = session.createSQLQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<MstAntibiotics> get_antibiotics() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MstAntibiotics> cq = builder.createQuery(MstAntibiotics.class);
		Root<MstAntibiotics> author = cq.from(MstAntibiotics.class);
		cq.where(builder.equal(author.get("status"), 1));
		List<MstAntibiotics> allItems = session.createQuery(cq).getResultList();
		return allItems;
	}

	public Object saveOrUpdateAntibiotics(TrnIcAntibiotics trnIcAntibiotics) {
		try {
			Session session = sessionFactory.getCurrentSession();
			TrnIcAntibiotics newTrnIcAntibiotics = new TrnIcAntibiotics();
			newTrnIcAntibiotics.setId(trnIcAntibiotics.getId());
			newTrnIcAntibiotics.setStart_time(trnIcAntibiotics.getStart_time());
			newTrnIcAntibiotics.setEnd_time(trnIcAntibiotics.getEnd_time());
			newTrnIcAntibiotics.setMst_antibiotic_id(trnIcAntibiotics.getMst_antibiotic_id());
			newTrnIcAntibiotics.setHighend_antibiotic_id(trnIcAntibiotics.getHighend_antibiotic_id());
			session.saveOrUpdate(newTrnIcAntibiotics);
			return 1;
		} catch (Exception e) {
			return null;
		}

	}

}
