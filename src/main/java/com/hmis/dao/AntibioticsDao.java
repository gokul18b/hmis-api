package com.hmis.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hmis.entity.MstAntibiotics;
import com.hmis.entity.MstSubAntibiotics;
import com.hmis.entity.TrnIcAntibiotics;
import com.hmis.response.getAntibioticsStatus.AntibioticDetails;

@Repository
public class AntibioticsDao {

	@Autowired
	SessionFactory sessionFactory;

	public List<MstAntibiotics> get_antibiotics() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MstAntibiotics> cq = builder.createQuery(MstAntibiotics.class);
		Root<MstAntibiotics> antibiotics = cq.from(MstAntibiotics.class);
		cq.where(builder.equal(antibiotics.get("status"), 1));
		List<MstAntibiotics> allItems = session.createQuery(cq).getResultList();
		return allItems;
	}

	public List<MstSubAntibiotics> get_sub_antibiotics(Integer antibiotics_id) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MstSubAntibiotics> cq = builder.createQuery(MstSubAntibiotics.class);
		Root<MstSubAntibiotics> _sub_antibiotics = cq.from(MstSubAntibiotics.class);
		cq.where(builder.equal(_sub_antibiotics.get("mst_antibiotics_id"), antibiotics_id),
				builder.equal(_sub_antibiotics.get("status"), 1));
		List<MstSubAntibiotics> resultList = session.createQuery(cq).getResultList();
		return resultList;
	}

	public Object saveOrUpdateAntibiotics(TrnIcAntibiotics trnIcAntibiotics) {
		System.out.println(trnIcAntibiotics);
		try {
			Session session = sessionFactory.getCurrentSession();
			TrnIcAntibiotics newTrnIcAntibiotics = new TrnIcAntibiotics();
			newTrnIcAntibiotics.setId(trnIcAntibiotics.getId());
			newTrnIcAntibiotics.setStart_time(trnIcAntibiotics.getStart_time());
			newTrnIcAntibiotics.setEnd_time(trnIcAntibiotics.getEnd_time());
			newTrnIcAntibiotics.setMst_antibiotic_id(trnIcAntibiotics.getMst_antibiotic_id());
			newTrnIcAntibiotics.setMst_sub_antibiotic_id(trnIcAntibiotics.getMst_sub_antibiotic_id());
			newTrnIcAntibiotics.setVisit_id(trnIcAntibiotics.getVisit_id());
			newTrnIcAntibiotics.setBed_id(trnIcAntibiotics.getBed_id());
			newTrnIcAntibiotics.setCreated_by(trnIcAntibiotics.getCreated_by());

			session.saveOrUpdate(newTrnIcAntibiotics);
			return 1;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Object[]> getInCompletedAntibiotics(Integer visit_id, Integer antibiotic_id,
			Integer sub_antibiotic_id) {
		try {
			Session session = sessionFactory.getCurrentSession();
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaQuery<TrnIcAntibiotics> cq = builder.createQuery(TrnIcAntibiotics.class);
//			Root<TrnIcAntibiotics> _trn_antibiotics = cq.from(TrnIcAntibiotics.class);
//			cq.where(builder.equal(_trn_antibiotics.get("visit_id"), visit_id),
//					builder.equal(_trn_antibiotics.get("mst_antibiotic_id"), antibiotic_id),
//					builder.equal(_trn_antibiotics.get("mst_sub_antibiotic_id"), sub_antibiotic_id),
//					_trn_antibiotics.get("end_time").isNull());
//
//			TrnIcAntibiotics result = session.createQuery(cq).getSingleResult();

			String hql = "SELECT \r\n"
					+ "trn_anti.id,mst_anti.antibiotics_name,mst_sub_anti.sub_antibiotics_name,trn_anti.start_time,trn_anti.end_time,mst_anti.id,mst_sub_anti.id\r\n"
					+ "FROM trn_ic_antibiotics  as trn_anti\r\n"
					+ "LEFT JOIN mst_antibiotics as mst_anti ON (mst_anti.id=trn_anti.mst_antibiotic_id)\r\n"
					+ "LEFT JOIN mst_sub_antibiotics as mst_sub_anti ON(mst_sub_anti.id=trn_anti.mst_sub_antibiotic_id)\r\n"
					+ "WHERE trn_anti.visit_id= ? "
					//+ "and trn_anti.mst_antibiotic_id= ? and trn_anti.mst_sub_antibiotic_id= ? "
					+ "and end_time is null";

			Query<?> query = session.createQuery(hql);
			query.setParameter(0, visit_id);
			//query.setParameter(1, antibiotic_id);
			//query.setParameter(2, sub_antibiotic_id);
			
			System.out.println(hql);

			List<Object[]> results =(List<Object[]>) query.getResultList();

			return results;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Object[]> getCompletedAntibiotics(Integer visit_id, Integer antibiotic_id,
			Integer sub_antibiotic_id) {
		try {
			Session session = sessionFactory.getCurrentSession();
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaQuery<TrnIcAntibiotics> cq = builder.createQuery(TrnIcAntibiotics.class);
//			Root<TrnIcAntibiotics> _trn_antibiotics = cq.from(TrnIcAntibiotics.class);
//			cq.where(builder.equal(_trn_antibiotics.get("visit_id"), visit_id),
//					builder.equal(_trn_antibiotics.get("mst_antibiotic_id"), antibiotic_id),
//					builder.equal(_trn_antibiotics.get("mst_sub_antibiotic_id"), sub_antibiotic_id),
//					_trn_antibiotics.get("end_time").isNotNull());

			String hql = "SELECT \r\n"
					+ "trn_anti.id,mst_anti.antibiotics_name,mst_sub_anti.sub_antibiotics_name,trn_anti.start_time,trn_anti.end_time\r\n"
					+ "FROM trn_ic_antibiotics  as trn_anti\r\n"
					+ "LEFT JOIN mst_antibiotics as mst_anti ON (mst_anti.id=trn_anti.mst_antibiotic_id)\r\n"
					+ "LEFT JOIN mst_sub_antibiotics as mst_sub_anti ON(mst_sub_anti.id=trn_anti.mst_sub_antibiotic_id)\r\n"
					+ "WHERE trn_anti.visit_id= ? "
//					+ "and trn_anti.mst_antibiotic_id= ? and trn_anti.mst_sub_antibiotic_id= ? "
					+ "and end_time is not null order by last_updated desc";

			Query<?> query = session.createQuery(hql);
			query.setParameter(0, visit_id);
//			query.setParameter(1, antibiotic_id);
//			query.setParameter(2, sub_antibiotic_id);
			
			System.out.println(hql);

			List<Object[]> results =(List<Object[]>) query.getResultList();
			
			return results;
		} catch (Exception e) {
			return null;
		}
	}

}
