package com.hmis.dao.ic;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hmis.entity.MstUsers;

@Repository
public class CommonDao {
	@Autowired
	SessionFactory sessionFactory;

	public List<Object[]> login(MstUsers mstUsers) {
		Session session = sessionFactory.getCurrentSession();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		try {

			String hql = "select mst_usrs.userref_id,mst_usrs.displayname,mob_role.role_name from  mst_users mst_usrs\r\n"
					+ "left join mst_employees mst_emp on(mst_emp.id=mst_usrs.userref_id)\r\n"
					+ "right join mst_mobileapp_user_role mob_role on(mob_role.mst_employee_id=mst_emp.id)\r\n"
					+ "where mst_usrs.username= ? and mst_usrs.password= ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, mstUsers.getUsername());
			query.setParameter(1, md5.encodePassword(mstUsers.getPassword(), null));
				
			query.setMaxResults(1);

			return query.list();
		} catch (Exception e) {
			return null;
		}
	}
}
