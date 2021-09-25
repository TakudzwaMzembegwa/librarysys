/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugbusterlibrary.dao;

import bugbusterlibrary.EntityManagerFactoryHandler;
import bugbusterlibrary.entity.Book;
import bugbusterlibrary.entity.Category;
import bugbusterlibrary.entity.Receipt;
import bugbusterlibrary.entity.User;
import java.util.ArrayList;
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

    /**
     * Search book by book Id
     * 
     * @param Id
     * @return book
     */
    public Book findById(int Id) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        Book book = em.createNamedQuery("Book.findByBookId", Book.class).setParameter("bookId", Id).getSingleResult();
        return book;
    }

    /**
     * Adds a book in the database
     *
     * @param book the book to be persisted
     * @param category the category the book belongs to
     */
    public void persist(Book book, Category category) {
        book.setCategory(category);
        
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.clear();
    }

    /***
     * To search for a book using a set of parameters, defaults can be used to
     * ignore a parameter(s) while making a search.
     * 
     * @param title        the title you want to search book by: nul lable Or ""
     * @param author       the author you want to search book by: nullable Or ""
     * @param edition      the edition of the book you want to search for: 0 to
     *                     exclude it
     * @param availability the availability of the book you want to search for:
     *                     nullable Or ""
     * @param category     the category to search book from: nullable
     * 
     * @return List<Book> A List of book(s) that matches the given parameters
     */
    public List<Book> searchBooksByParameters(String title, String author, int edition, String availability,
            Category category) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        String sql = "SELECT b FROM Book b WHERE 1 = 1 ";
        if (title != null && !title.isEmpty())
            sql += "AND b.title LIKE :title ";
        if (author != null && !author.isEmpty())
            sql += "AND b.author LIKE :author ";
        if (edition > 0)
            sql += "AND b.edition LIKE :edition ";
        if (availability != null && !availability.isEmpty())
            sql += "AND b.availability = :availability ";
        if (category != null)
            sql += "AND b.category = :category";

        Query q = em.createQuery(sql);

        if (title != null && !title.isEmpty())
            q.setParameter("title", "%" + title + "%");
        if (author != null && !author.isEmpty())
            q.setParameter("author", "%" + author + "%");
        if (edition > 0)
            q.setParameter("edition", "%" + edition + "%");
        if (availability != null && !availability.isEmpty())
            q.setParameter("availability", availability);
        if (category != null)
            q.setParameter("category", category);
        return q.getResultList();
    }

    /**
     * Function to delete a book from the database.
     * 
     * @param bookId The id of the book to delete
     */
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

    /**
     * Function to update a book
     * 
     * @param myBook
     * @param description
     * @param edition
     * @param image
     * @param categoryId
     */
    public void updateBook(Book myBook) {
        // set the updated parameters for the book.
        Book book = myBook;

        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(book); // call to update the book.
        em.getTransaction().commit();
    }

    /**
     * Finds books loaned by a user
     * 
     * @param user
     * @return books
     */
    public List<Book> userBooks(User user) {
        ReceiptDao receiptdao = new ReceiptDao();
        List<Receipt> receipts = receiptdao.findUserReceipts(user);
        List<Book> userBooks = new ArrayList<>();
        for (Receipt receipt : receipts) {
            userBooks.add(receipt.getBook());
        }
        return userBooks;
    }

}
