package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBUtil {
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("ShoppingCart"); 
			 public static EntityManagerFactory getEmFactory() {   return emf;
			 
			 }
			public static void insert(Cart cart) {
					
					EntityManager em = DBUtil.getEmFactory().createEntityManager();
					 EntityTransaction trans = em.getTransaction();
					 trans.begin(); 
					 try {
					 em.persist(cart);
					 trans.commit();
					 } catch (Exception e) {
					 System.out.println(e);
					 trans.rollback();
					 } finally {
					 em.close();

				}

			 }
			public static void insertToAdmin(Admincart user) {
				
				EntityManager em = DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 trans.begin(); 
				 try {
				 em.persist(user);
				 trans.commit();
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();

			}

		 }
			public static void insertUser(Shopper user) {
				
				EntityManager em = DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 trans.begin(); 
				 try {
				 em.persist(user);
				 trans.commit();
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();

			}
			}
			public static int deleteCart(Shopper s){
				EntityManager em=DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 int deletedCount =0;
				 String q="delete from Cart c where c.shopper.userId="+s.getUserId();				
				 trans.begin(); 
				 try {
					 deletedCount=em.createQuery(q).executeUpdate();
				 trans.commit();
				 return deletedCount;
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();

			}
				return deletedCount;
				
			}
			
			public static int deleteCartAdmin(Shopper s){
				EntityManager em=DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 int deletedCount =0;
				 String q="delete from Admincart c where c.shopper.userId="+s.getUserId();				
				 trans.begin(); 
				 try {
					 deletedCount=em.createQuery(q).executeUpdate();
				 trans.commit();
				 return deletedCount;
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();

			}
				return deletedCount;
				
			}

}
