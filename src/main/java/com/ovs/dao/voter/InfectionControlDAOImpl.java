package com.ovs.dao.voter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ovs.model.Employee;
import com.ovs.model.MstUsers;
import com.ovs.model.Phone;
import com.ovs.response.EmployeeResponse;
import com.ovs.response.LoginResponse;
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
	public LoginResponse login_join(MstUsers mstUsers) {
		Session session = sestionFactory.getCurrentSession();
		String hql = "select usr.displayname  name ,usr.app_login_token,emp.mst_designation_id from mst_users  usr left join mst_employees  emp on(emp.id=usr.userref_id) where usr.id=7355";
		Query query = session.createQuery(hql);
		Object obj = query.uniqueResult();
		System.out.println(obj.toString());
		LoginResponse loginResponse = (LoginResponse) obj;
		System.out.println(loginResponse.getName());

		return loginResponse;
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

	@Override
	public void addEmployee() {
		Employee emp = new Employee("Gokul", 25);
		Phone ph = new Phone();
		ph.setNo("7418227833");
//		ph.setEmp(emp);
//		emp.setPhone(ph);

		Session session = sestionFactory.getCurrentSession();
		session.save(emp);
		System.out.println(emp);
		System.out.println(ph);
		session.save(ph);
	}

	@Override
	public List<EmployeeResponse> employeeList() {
		/*Session session = sestionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);

		Root<Employee> employee = criteria.from(Employee.class);
		Root<Phone> phone = criteria.from(Phone.class);

		criteria.multiselect(employee, phone);

		List<Tuple> tuples = session.createQuery(criteria).getResultList();

		for (Tuple tuple : tuples) {
			Employee emp = (Employee) tuple.get(0);
			Phone pho = (Phone) tuple.get(1);
			if (emp != null) {
				System.out.println(emp.getName() + "-" + pho.getNo());
			}
		}*/

//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery criteria = builder.createQuery();

//		Criteria employeeCriteria = session.createCriteria(Employee.class);
//		Criteria phoneCriteria = employeeCriteria.createCriteria("phone", org.hibernate.sql.JoinType.RIGHT_OUTER_JOIN);
//		List<Object> object = employeeCriteria.list();
//		Employee employee = (Employee) object.get(0);

		EntityManager em = sestionFactory.createEntityManager();

		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = qb.createQuery(Employee.class);

		Metamodel m = em.getMetamodel();
		EntityType<Employee> emproot_ = m.entity(Employee.class);
		EntityType<Phone> phroot_ = m.entity(Phone.class);

		Root<Employee> root = criteriaQuery.from(Employee.class);

		/**
		 * if the Cliente is a list you should use ListJoin instead of Join so it's
		 * ListJoin<Pedido, Cliente> if you don't have metadata you can use
		 * root.join(root.get("id_cliente")).
		 */
		Join<Employee, Phone> cliente = root.join("id", JoinType.LEFT);

		/*
		 * select pr.*,p.id, cf.nome, p.valor_total as "valor total", f.descricao as
		 * "forma de pagamento", t.descricao as "tipo de pagamento", s.descricao as
		 * "Status" from pedido p left join cliente c on c.id = p.id_cliente left join
		 * cliente_pessoa_fisica cf on cf.id_cliente = c.id left join forma_pagamento f
		 * on f.id = p.id_forma_pagamento left join tipo_pagamento t on t.id =
		 * p.id_tipo_pagamento left join status_pedido s on s.id = p.id_status_pedido
		 * left join itempedido itp on itp.id_pedido = p.id left join produto pr on
		 * pr.id = itp.id_produto
		 */

		criteriaQuery.multiselect(root.get("id"));

//		TypedQuery<Employee> query = em.createQuery(criteriaQuery);
//		List<Employee> employee = query.getResultList();
//		
		
		
		
		return null;

		// return null;
	}

}
