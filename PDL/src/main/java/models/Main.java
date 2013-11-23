/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Niels
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.HibernateUtil;
 
public class Main {
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
 
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        //maak hier objecten aan

        session.getTransaction().commit();
        session.close();
    }
}
