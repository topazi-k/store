package com.urianskui.store.dao;

import com.urianskui.store.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepositoryJpa {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryJpa(EntityManagerFactory em) {
        sessionFactory = em.unwrap(SessionFactory.class);
    }

    public List<Product> findPaginated(int firstResult) {

        StatelessSession session = sessionFactory.openStatelessSession();
        Query query = session.createQuery("From Product p ORDER BY p.id");
        query.setFirstResult(firstResult);
        query.setMaxResults(10000);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

}
