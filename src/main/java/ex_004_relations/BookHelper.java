package ex_004_relations;


import ex_004_relations.entity.Author;
import ex_004_relations.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookHelper {

    private SessionFactory sessionFactory;

    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery cq = cb.createQuery(Book.class);

        Root<Author> root = cq.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)

        cq.select(root);

        //этап выполнения запроса
        Query query = session.createQuery(cq);

        List<Book> bookList = query.getResultList();

        session.close();

        return bookList;
    }
    //Задание 3
    public void deleteBookById(long id){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.delete(book);
        session.getTransaction().commit();

        session.close();
    }
    public void deleteAuthorBooks(Author author){
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        List<Book> bookList = author.getBooks();

        for (Book book : bookList) {
            session.delete(book);
        }

        session.getTransaction().commit();

        session.close();

    }
}
