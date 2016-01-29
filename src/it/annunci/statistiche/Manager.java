package it.annunci.statistiche;


import java.util.ArrayList;
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
	public List<MotoriDiRicerca> motoriList(String answer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<MotoriDiRicerca> list = null;
		try {
			if(answer.equals("2")){
				list = (List<MotoriDiRicerca>)session.createQuery("FROM MotoriDiRicerca WHERE attivo = '1'").list();
			}else{
				list = (List<MotoriDiRicerca>)session.createQuery("FROM MotoriDiRicerca").list();
			}
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

			list = (List<Annunci>)session.createQuery("FROM Annunci order by data desc").list();

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
		try {

			listAnnuncioMotore = (List<Object>)session
					.createQuery("SELECT am, mr.nome, an.data FROM AnnuncioMotore am , MotoriDiRicerca mr , Annunci an WHERE am.id_motore=mr.id AND an.id_annunci=am.id_annunci AND (an.data BETWEEN :from AND :to) AND am.id_motore IN "+ id_motore)
					.setParameter("from", from)
					.setParameter("to", to)
					.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return listAnnuncioMotore;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> nomiMotoriFromId(String id_motore){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<String> list = null;
		try {

			list = (List<String>)session
					.createQuery("SELECT nome FROM MotoriDiRicerca WHERE id IN "+ id_motore)
					.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<AnnuncioMotore> query2(Date from, Date to, String id_motore) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AnnuncioMotore> listAnnuncioMotore = null;
		try {

			listAnnuncioMotore = (List<AnnuncioMotore>)session
					.createQuery("SELECT am FROM AnnuncioMotore am , MotoriDiRicerca mr , Annunci an WHERE am.id_motore=mr.id AND an.id_annunci=am.id_annunci AND (an.data BETWEEN :from AND :to) AND am.id_motore IN "+ id_motore)
					.setParameter("from", from)
					.setParameter("to", to)
					.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return listAnnuncioMotore;
	}
	
}