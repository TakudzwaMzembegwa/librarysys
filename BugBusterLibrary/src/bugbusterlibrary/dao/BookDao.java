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
import javax.persistence.Query;
/**
 *
 * @author Takudzwa Mzembegwa
 * @author Sango
 */
public class BookDao {

    /**
     * Finds all the Books in the database
     * 
     * @return list of all books
     */
    public List<Book> findAll() {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    /**
     * Finds book by book id
     * 
     * @param id the {@code id} of the book to be found
     * @return book in any was found else {@code null}
     */
    public Book findById(int id) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        return em.createNamedQuery("Book.findByBookId", Book.class).setParameter("bookId", id).getSingleResult();
    }

    /**
     * Adds a book in the database
     *
     * @param book     the book to be persisted
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
     * @param title        the title you want to search book by. Can be {@code null} Or {@code ""}.
     * @param author       the author you want to search book by. Can be {@code null} Or {@code ""}.
     * @param edition      the edition of the book you want to search for: Use {@code 0} to
     *                     exclude it.
     * @param availability the availability of the book you want to search for. Can be {@code null} Or {@code ""}.
     * @param category     the category to search book from. Can be {@code null}.
     * 
     * @return {@code List<Book>} A List of book(s) that matches the given
     *         parameters.
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
     * Function to update a book.
     * 
     * @param book the updated book object to be persisted
     */
    public void updateBook(Book book) {
        EntityManager em = EntityManagerFactoryHandler.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(book); // call to update the book.
        em.getTransaction().commit();
    }

    /**
     * Finds books loaned by a user
     * 
     * @param user the user whose books should be found
     * @return books user books found
     */
    public List<Book> userBooks(User user) {
        ReceiptDao receiptDao = new ReceiptDao();
        List<Receipt> receipts = receiptDao.findUserReceipts(user);
        List<Book> userBooks = new ArrayList<>();
        receipts.forEach((receipt) -> {
            userBooks.add(receipt.getBook());
        });
        return userBooks;
    }

}
