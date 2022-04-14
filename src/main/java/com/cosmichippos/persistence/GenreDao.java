package com.cosmichippos.persistence;

import com.cosmichippos.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Expression;
import java.util.List;

/**
 * The type Genre dao.
 */
public class GenreDao {

    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets Genre by id.
     *
     * @param id the id
     * @return the by id
     */
    public Genre getById(long id) {
        logger.debug("Searching for getById {}", id);
        Session session = sessionFactory.openSession();
        Genre genre = session.get(Genre.class, id);
        session.close();
        return genre;
    }

    /**
     * Gets all children of specified genre.
     *
     * @param parentId the parent id
     * @return the children
     */
    public List<Genre> getChildren(long parentId) {
        logger.debug("Searching for children of {}", parentId);
        // String hql = "";
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
        Root<Genre> root = query.from(Genre.class);
        query.select(root).where(builder.equal(root.get("parentId"), parentId));
        List<Genre> children = session.createQuery(query).getResultList();
        session.close();
        return children;
    }

    /**
     * Gets all genres.
     *
     * @return the all
     */
    public List<Genre> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
        Root<Genre> root = query.from(Genre.class);
        List<Genre> genres = session.createQuery(query).getResultList();
        session.close();
        return genres;
    }


    /**
     * Save or update a genre.
     *
     * @param genre the genre
     */
    public void saveOrUpdate(Genre genre) {
        logger.debug("SaveOrUpdating: {}", genre);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(genre);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a genre.
     *
     * @param genre the genre
     * @return the int
     */
    public long insert(Genre genre) {
        long id;
        logger.debug("Inserting: {}", genre);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (long)session.save(genre);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a genre.
     *
     * @param genre the genre
     */
    public void delete(Genre genre) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(genre);
        transaction.commit();
        session.close();
    }

    public Genre getByName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
        Root<Genre> root = query.from(Genre.class);
        Expression<String> propertyPath = root.get("name");
        query.where(builder.equal(propertyPath, name));
        Genre genre = session.createQuery(query).getSingleResult();
        session.close();
        return genre;
    }

}