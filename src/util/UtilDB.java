/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.Game;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Game> listGames() {
      List<Game> resultList = new ArrayList<Game>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> games = session.createQuery("FROM Game").list();
         for (Iterator<?> iterator = games.iterator(); iterator.hasNext();) {
            Game game = (Game) iterator.next();
            resultList.add(game);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Game> listGames(String keyword) {
      List<Game> resultList = new ArrayList<Game>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> games = session.createQuery("FROM Game").list();
         for (Iterator<?> iterator = games.iterator(); iterator.hasNext();) {
            Game game = (Game) iterator.next();
            if (game.getName().contains(keyword)) {
               resultList.add(game);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static List<Game> listGames(String keyword, String releasedate) {
	      List<Game> resultList = new ArrayList<Game>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> games = session.createQuery("FROM Game").list();
	         for (Iterator<?> iterator = games.iterator(); iterator.hasNext();) {
	            Game game = (Game) iterator.next();
	            if (game.getName().contains(keyword) && game.getReleasedate().contains(releasedate)) {
	               resultList.add(game);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }

   public static void createGame(String name, String releasedate, String gamesystem, String publisher, String price, String region) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Game(name, releasedate, gamesystem, publisher, price, region));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
