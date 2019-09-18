package com.hmis.dao.ic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		cq.from(MstActiveMedicalAudit.class);
		List<MstActiveMedicalAudit> checkList = session.createQuery(cq).getResultList();
		return checkList;
	}

	public Integer save_checklist(SaveActiveMedicalRecordRequest saveActiveMedicalRecordRequest) {
		Session session = sessionFactory.getCurrentSession();
		try {

			TrnActiveMedicalAudit trnActiveMedicalAudit = new TrnActiveMedicalAudit();
			trnActiveMedicalAudit.setVisit_id(saveActiveMedicalRecordRequest.getVisit_id());
			session.save(trnActiveMedicalAudit);

			for (Details details : saveActiveMedicalRecordRequest.getDetails()) {
				TrnActiveMedicalAuditDetails trnActiveMedicalAuditDetails = new TrnActiveMedicalAuditDetails();
				trnActiveMedicalAuditDetails.setTrnActiveMedicalAudit(trnActiveMedicalAudit);
				trnActiveMedicalAuditDetails.setMst_active_medical_audit_id(details.getMst_active_medical_audit_id());
				trnActiveMedicalAuditDetails.setOption_value(details.getOption_value());
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

}
