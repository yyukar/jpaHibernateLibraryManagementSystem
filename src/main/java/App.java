import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("practicelibrarymanagementsystem");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        // add author
        Author author = new Author();
        author.setName("John Doe");
        author.setBirthDate(1965);
        author.setCountry("TR");
        entityManager.persist(author);

        // add publisher
        Publisher publisher = new Publisher();
        publisher.setName("John Doe");
        publisher.setEstablishmentYear(2018);
        publisher.setAddress("home");
        entityManager.persist(publisher);

        // add category
        Category sciFi = new  Category();
        sciFi.setName("SciFi");
        entityManager.persist(sciFi);

        Set<Category> categories = new HashSet<>();
        categories.add(sciFi);

        // add borrowings
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBorrowerName("John Doe");
        bookBorrowing.setBorrowingDate(LocalDate.now());
        entityManager.persist(bookBorrowing);

        List<BookBorrowing> bookBorrowings = new ArrayList<>();
        bookBorrowings.add(bookBorrowing);


        // add book
        Book book = new  Book();
        book.setName("John Doe");
        book.setPublicationYear(1231);
        book.setStock(12312);

        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        bookBorrowing.setBook(book);
        book.setBorrowings(bookBorrowings);
        entityManager.persist(book);

        System.out.println(book);

        transaction.commit();
    }
}
