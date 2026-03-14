package com.inventory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProductDAO {

    public void addProduct(Product product) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(product);

        tx.commit();
        session.close();
    }

    public Product getProduct(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();

        return product;
    }

    public void updateProduct(int id, double price, int quantity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if(product != null) {
            product.setPrice(price);
            product.setQuantity(quantity);
            session.update(product);
        }

        tx.commit();
        session.close();
    }
    public void countAvailableProducts() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(
                "SELECT COUNT(*) FROM Product WHERE quantity > 0");

        Long count = (Long) q.uniqueResult();

        System.out.println("Available Products: " + count);

        session.close();
    }

    public void deleteProduct(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if(product != null) {
            session.delete(product);
        }

        tx.commit();
        session.close();
    }
    public void sortPriceAscending() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("FROM Product ORDER BY price ASC");
        List<Product> list = q.list();

        list.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        session.close();
    }
    public void sortPriceDescending() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("FROM Product ORDER BY price DESC");
        List<Product> list = q.list();

        list.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        session.close();
    }
    public void sortByQuantity() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("FROM Product ORDER BY quantity DESC");
        List<Product> list = q.list();

        list.forEach(p -> System.out.println(p.getName()+" "+p.getQuantity()));

        session.close();
    }
    public void firstThreeProducts() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("FROM Product");
        q.setFirstResult(0);
        q.setMaxResults(3);

        List<Product> list = q.list();
        list.forEach(p -> System.out.println(p.getName()));

        session.close();
    }
    public void nextThreeProducts() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("FROM Product");
        q.setFirstResult(3);
        q.setMaxResults(3);

        List<Product> list = q.list();
        list.forEach(p -> System.out.println(p.getName()));

        session.close();
    }
    public void countProducts() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("SELECT COUNT(*) FROM Product");
        Long count = (Long) q.uniqueResult();

        System.out.println("Total Products: "+count);

        session.close();
    }
    public void minMaxPrice() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(
                "SELECT MIN(price), MAX(price) FROM Product");

        Object[] result = (Object[]) q.uniqueResult();

        System.out.println("Min Price: "+result[0]);
        System.out.println("Max Price: "+result[1]);

        session.close();
    }
    public void priceRange(double min, double max) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE price BETWEEN :min AND :max",
                Product.class);

        q.setParameter("min", min);
        q.setParameter("max", max);

        List<Product> list = q.list();

        list.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

        session.close();
    }
    public void nameStartsWith(String letter) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Product> q = session.createQuery(
                "FROM Product WHERE name LIKE :pattern", Product.class);

        q.setParameter("pattern", letter + "%");

        List<Product> list = q.list();

        list.forEach(p -> System.out.println(p.getName()));

        session.close();
    }
    public void groupByDescription() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description");

        List<Object[]> list = q.list();

        for(Object[] row : list){
            System.out.println(row[0]+" : "+row[1]);
        }

        session.close();
    }
    public void countByDescription() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(
                "SELECT description, COUNT(*) FROM Product GROUP BY description");

        List<Object[]> list = q.list();

        for(Object[] row : list){
            System.out.println(row[0]+" -> "+row[1]);
        }

        session.close();
    }

}