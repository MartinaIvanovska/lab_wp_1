package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepositoryMock {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book save(Long id,String title, String genre, double averageRating, Author author);
    Book getById(Long id);
    Book delete(Book book);

}
