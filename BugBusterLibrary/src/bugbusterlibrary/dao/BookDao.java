/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;

import bugbusterlibrary.EntityManagerFactoryHandler;
import bugbusterlibrary.entity.Book;
import bugbusterlibrary.entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Takudzwa Mzembegwa
 * @author Sango
 */
public class BookDao {

    /**
     * @return list of all books
     */
    public List<Book> findAll() {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        return books;
    }

    public Book findById(int Id) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Book book = em.createNamedQuery("Book.findByBookId", Book.class).setParameter("bookId", Id).getSingleResult();
        return book;
    }

    /**
     * Adds a book in the database
     *
     * @param book the book to be persisted
     */
    public void persist(Long bookId, String title, String author, String isbn, String description, String edition,
            String image, String availability, Category categoryId) {
        Book book = new Book(bookId, title, author, isbn, availability);
        book.setDescription(description);
        book.setEdition(edition);
        book.setImage(image);
        book.setCategoryId(categoryId);

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.clear();
    }

    public List<Book> searchBooksByParameters(String title, String author, int edition, String availability,
            Category category) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        String sql = "SELECT b FROM Book b WHERE 1 = 1 ";
        if (title != null && !title.isEmpty()) {
            sql += "AND b.title LIKE :title ";
        }
        if (author != null && !author.isEmpty()) {
            sql += "AND b.author LIKE :author ";
        }
        if (edition > 0) {
            sql += "AND b.edition LIKE :edition ";
        }
        if (availability != null && !availability.isEmpty()) {
            sql += "AND b.availability = :availability ";
        }
        if (category != null) {
            sql += "AND b.categoryId = :categoryId";
        }
        Query q = em.createQuery(sql);
        if (title != null && !title.isEmpty()) {
            q.setParameter("title", "%" + title + "%");
        }
        if (author != null && !author.isEmpty()) {
            q.setParameter("author", "%" + author + "%");
        }
        if (edition > 0) {
            q.setParameter("edition", "%" + edition + "%");
        }
        if (availability != null && !availability.isEmpty()) {
            q.setParameter("availability", availability);
        }
        if (category != null) {
            q.setParameter("categoryId", category);
        }

        return q.getResultList();
    }

    // function to delete a book from the databse.
    public void deleteBook(Long bookId) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Book book = em.find(Book.class, bookId); // search for the book by its ID.

        if (book != null) // check if the book exists in the database.
        {
            em.getTransaction().begin();
            em.remove(book); // delete the book.
            em.getTransaction().commit();
        }
    }

    // function to update a book.
    public void updateBook(Book myBook, String description, String edition, String image, Category categoryId) {
        // set the updated parameters for the book.
        Book book = myBook;
        book.setDescription(description);
        book.setEdition(edition);
        book.setImage(image);
        book.setCategoryId(categoryId);

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(book); // call to update the book.
        em.getTransaction().commit();
    }

}
