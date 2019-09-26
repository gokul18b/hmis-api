package com.hmis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hmis.entity.MstActiveMedicalAudit;
import com.hmis.entity.TrnActiveMedicalAudit;
import com.hmis.entity.TrnActiveMedicalAuditDetails;
import com.hmis.request.nabh.savemedicalchecklist.Details;
import com.hmis.request.nabh.savemedicalchecklist.SaveActiveMedicalRecordRequest;

@Repository
public class ActiveMedicalRecordDao {

	@Autowired
	SessionFactory sessionFactory;

	public List<MstActiveMedicalAudit> get_questions() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaQuery<MstActiveMedicalAudit> cq = session.getCriteriaBuilder().createQuery(MstActiveMedicalAudit.class);
		Criteria criteria = session.createCriteria(MstActiveMedicalAudit.class);
		criteria.add(Restrictions.eq("status", 1));
		return criteria.list();
		// cq.from(MstActiveMedicalAudit.class);
		// List<MstActiveMedicalAudit> checkList =
		// session.createQuery(cq).getResultList();
		// return checkList;
	}

	public Integer save_checklist(SaveActiveMedicalRecordRequest saveActiveMedicalRecordRequest) {
		Session session = sessionFactory.getCurrentSession();
		try {

			TrnActiveMedicalAudit trnActiveMedicalAudit = new TrnActiveMedicalAudit();
			trnActiveMedicalAudit.setVisit_id(saveActiveMedicalRecordRequest.getVisit_id());
			trnActiveMedicalAudit.setCreated_by(saveActiveMedicalRecordRequest.getCreated_by());
			trnActiveMedicalAudit.setBed_id(saveActiveMedicalRecordRequest.getBed_id());
			session.save(trnActiveMedicalAudit);

			for (Details details : saveActiveMedicalRecordRequest.getDetails()) {
				TrnActiveMedicalAuditDetails trnActiveMedicalAuditDetails = new TrnActiveMedicalAuditDetails();
				trnActiveMedicalAuditDetails.setTrnActiveMedicalAudit(trnActiveMedicalAudit);
				trnActiveMedicalAuditDetails.setMst_active_medical_audit_id(details.getMst_active_medical_audit_id());
				trnActiveMedicalAuditDetails.setOption_value(details.getOption_value());
				trnActiveMedicalAuditDetails.setRemarks(details.getRemarks());
				trnActiveMedicalAuditDetails.setTotal_minutes(details.getTotal_minutes());
				session.save(trnActiveMedicalAuditDetails);
			}
			return 1;
		} catch (HibernateException e) {
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	public List<TrnActiveMedicalAudit> checkPreviousEntry(Integer visit_id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria cr = session.createCriteria(TrnActiveMedicalAudit.class);
			cr.add(Restrictions.eq("visit_id", visit_id));
			List<TrnActiveMedicalAudit> objList = cr.list();
			return objList;
		} catch (Exception e) {
			return null;
		}

	}

	public List<?> getPreviousTime(Integer visit_id) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "select mst_audit.id,trn_audit_detail.total_minutes,trn_audit_detail.option_value from trn_active_medical_audit_details trn_audit_detail \r\n"
				+ "				left join mst_active_medical_audit mst_audit on(mst_audit.id=trn_audit_detail.mst_active_medical_audit_id)\r\n"
				+ "				left join trn_active_medical_audit trn_audit on(trn_audit.id=trn_audit_detail.trnActiveMedicalAudit)\r\n"
				+ "				where mst_audit.question_type='TIME' and trn_audit.visit_id='" + visit_id
				+ "' and trn_audit_detail.option_value='YES' and trn_audit_detail.total_minutes is not null";
		Query<?> query = session.createQuery(hql);

		return query.getResultList();

	}

//	public List<?> getActiveMedicalSummary(){
//		Session session = sessionFactory.getCurrentSession();
//		
//		CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
//		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
//		
//		Root<TrnActiveMedicalAudit> root_audit = cq.from(TrnActiveMedicalAudit.class);
//		cq.multiselect(root_audit.get("created_by"),cb.count(root_audit)).groupBy(root_audit.get("created_by"));
//		
//		List<Object[]> list = session.createQuery(cq).getResultList();  
//		
//		System.out.println(list.get(0)[0]+"--"+list.get(0)[1]);
//		return list;
//	}

	public List<Object[]> get_audit_summary(String from_date, String to_date) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select emp.emp_fname,count(emp.id) from trn_active_medical_audit as trn_audit \r\n" + 
				"LEFT JOIN mst_employees as emp ON(emp.id=trn_audit.created_by) \r\n" + 
				"WHERE trn_audit.created_date between '"+from_date+" 00:00:00' and '"+to_date+" 23:59:59'\r\n" + 
				"GROUP BY emp.id,emp.emp_fname";
		Query<Object[]> query = session.createQuery(hql);
		return query.getResultList();
	}

}
