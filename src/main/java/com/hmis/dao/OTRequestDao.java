package com.hmis.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hmis.entity.TrnIcOtFollowup;
import com.hmis.entity.TrnOtBookingRequests;

@Repository
public class OTRequestDao {
	@Autowired
	SessionFactory sessionFactory;

	public List<Object[]> get_ot_request_patients() {
		String hql = "SELECT id,\r\n" + "	patient_name,\r\n" + "	age,\r\n" + "	gender,\r\n"
				+ "	patient_code,\r\n" + "	surgery_name,\r\n" + "	surgeon,\r\n" + "	anaesthetist,\r\n"
				+ "	surgery_datetime \r\n" + "FROM trn_ot_booking_requests \r\n"
				+ "WHERE status in('Approved','Completed')";

		Session session = sessionFactory.getCurrentSession();

		Query<Object[]> query = session.createQuery(hql);
		List<Object[]> result = query.getResultList();
		return result;
	}

	public Integer save_ot_request(TrnIcOtFollowup trnIcOtFollowup) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(trnIcOtFollowup);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
