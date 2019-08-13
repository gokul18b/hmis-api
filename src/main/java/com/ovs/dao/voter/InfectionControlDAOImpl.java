package com.ovs.dao.voter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ovs.model.MstUsers;
import com.ovs.utils.TokenGenerator;

@Repository
public class InfectionControlDAOImpl implements InfectionControlDAO {

	@Autowired
	SessionFactory sestionFactory;

	@Override
	public MstUsers login(MstUsers mstUsers) {
		Session session = sestionFactory.getCurrentSession();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();

		Criteria criteria = session.createCriteria(MstUsers.class)
				.add(Restrictions.eq("username", mstUsers.getUsername()))
				.add(Restrictions.eq("password", md5.encodePassword(mstUsers.getPassword(), null)));
		MstUsers result = (MstUsers) criteria.uniqueResult();
		return result;

	}

	@Override
	public String token_generation(int id) {
		Session session = sestionFactory.getCurrentSession();
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

}
