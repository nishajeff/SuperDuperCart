package model;

import java.math.BigDecimal;

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
					// EntityTransaction trans1 = em.getTransaction();
					 trans.begin(); 
					 try {
					 em.persist(cart);
					 trans.commit();
					// trans1.commit();
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
			
			public static void insertComments(Comment c) {
				
				EntityManager em = DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 trans.begin(); 
				 try {
				 em.persist(c);
				 trans.commit();
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();

			}
			}
			

			public static void insertStoreCredit(Storecredit s) {
				
				EntityManager em = DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 trans.begin(); 
				 try {
				 em.persist(s);
				 trans.commit();
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();

			}
			}
public static void updateStoreCredit(Shopper s) {
				
				EntityManager em = DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				 String q="update Storecredit c set c.status='used' where c.userId="+s.getUserId()+" and c.status='unused'";	
				 trans.begin(); 
				 try {
				 em.createQuery(q).executeUpdate();
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
				 String q="delete from Cart c where c.shopper.userId="+s.getUserId()+" and c.status='no'";				
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
			
			
			
			public static void updateShipAddress(String s){
				EntityManager em=DBUtil.getEmFactory().createEntityManager();
				 EntityTransaction trans = em.getTransaction();
				
				 String q="update Shopper s set  s.shipaddress='"+s+"'";	
				
				 trans.begin(); 
				 try {
					 em.createQuery(q).executeUpdate();
				 trans.commit();				
				 } catch (Exception e) {
				 System.out.println(e);
				 trans.rollback();
				 } finally {
				 em.close();
			}	
				
			}
			
			
}
