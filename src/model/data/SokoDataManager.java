package model.data;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javassist.bytecode.stackmap.BasicBlock.Catch;

public class SokoDataManager {

	private SessionFactory factory;
	

	public SokoDataManager() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();
	}

	public void addSokoData(SokoData sokodata) {
		
		User user = new User(sokodata.getUser_name());
		LevelDB leveldb = new LevelDB(sokodata.getLevel_name());
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String usernameString = sokodata.getUser_name();
			Query query = session.createQuery("from Users where UserName LIKE :username");
			query.setParameter("username", usernameString);
			if(query.getResultList().isEmpty())
			{
				session.save(user);
			}
			String levelnameString = sokodata.getLevel_name();
			query = session.createQuery("from Levels where LevelName LIKE :levelname");
			query.setParameter("levelname", levelnameString);
			if(query.getResultList().isEmpty())
			{
				session.save(leveldb);			}
			session.save(sokodata);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();			
		} 
		finally {
			if (session != null)
				session.close();
			

		}
	}

	
	public LinkedList<SokoData> getData(){
		Session session = null;
		Transaction tx = null;
		LinkedList<SokoData> dataList = new LinkedList<SokoData>();
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from SokoData  order by Score DESC");
			tx.commit();
			dataList.addAll(query.list());

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();			
		} 
		finally {
			if (session != null)
				session.close();
			return dataList;
		}
	}
	
	public void close() {
		factory.close();
	}
}