package it.annunci.statistiche;


import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import it.annunci.statistiche.AnnuncioMotore;
import it.annunci.statistiche.MotoriDiRicerca;
import it.annunci.statistiche.HibernateUtil;

public class Manager extends HibernateUtil {

	public AnnuncioMotore add(AnnuncioMotore annunciomotore) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(annunciomotore);
		session.getTransaction().commit();
		return annunciomotore;
	}
	public AnnuncioMotore delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AnnuncioMotore annunciomotore = (AnnuncioMotore) session.load(AnnuncioMotore.class, id);
		if(null != annunciomotore) {
			session.delete(annunciomotore);
		}
		session.getTransaction().commit();
		return annunciomotore;
	}

	@SuppressWarnings("unchecked")
	public List<AnnuncioMotore> list() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AnnuncioMotore> list = null;
		try {

			list = (List<AnnuncioMotore>)session.createQuery("from AnnuncioMotore").list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<MotoriDiRicerca> motoriList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<MotoriDiRicerca> list = null;
		try {

			list = (List<MotoriDiRicerca>)session.createQuery("FROM MotoriDiRicerca").list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Annunci> annunciList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Annunci> list = null;
		try {

			list = (List<Annunci>)session.createQuery("FROM Annunci").list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<AnnuncioMotore> query(String id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AnnuncioMotore> listAnnuncioMotore = null;
		try {

			listAnnuncioMotore = (List<AnnuncioMotore>)session.createQuery("FROM AnnuncioMotore WHERE id_motore IN" + id).list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return listAnnuncioMotore;
	}


	@SuppressWarnings("unchecked")
	public List<Object> query1(Date from, Date to, String id_motore) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Object> listAnnuncioMotore = null;
		System.out.println("From: " + from + "\tTo: " + to + "\tId_motore: " + id_motore);
		try {

			listAnnuncioMotore = (List<Object>)session
					.createQuery("SELECT am, mr.nome, an.data FROM AnnuncioMotore am , MotoriDiRicerca mr , Annunci an WHERE am.id_motore=mr.id AND an.id_annunci=am.id_annunci AND (an.data BETWEEN :from AND :to) AND am.id_motore IN "+ id_motore)

//					.createQuery("SELECT AnnuncioMotore, mr.nome, an.data FROM AnnuncioMotore AS am JOIN MotoriDiRicerca AS mr ON am.id_motore = mr.id JOIN Annunci an ON an.id_annunci = am.id_annunci WHERE (data BETWEEN :from AND :to) AND am.id_motore IN "+ id_motore)

//					.createSQLQuery("select am.*,mr.nome,an.data from annuncio_motore as am join motori_di_ricerca as mr on am.id_motore=mr.id join annunci an on an.id_annunci=am.id_annunci where (data between '2016-1-5' and '2016-1-23') AND am.id_motore IN (1,2)")
					.setParameter("from", from)
					.setParameter("to", to)
					.list();
//					.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)

			System.out.println(listAnnuncioMotore);
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return listAnnuncioMotore;
	}
}