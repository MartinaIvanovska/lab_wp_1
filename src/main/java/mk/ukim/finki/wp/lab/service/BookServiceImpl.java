package mk.ukim.finki.wp.lab.service;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.mock.BookRepositoryMock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) throws NullPointerException {
        if ((text == null || text.isBlank()) && rating == null) {
            return listAll();
        }
        if (rating == null) {
            return bookRepository.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(text, text);
        } else {
            return bookRepository.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, text, rating);
        }
    }

    @Override
    @Transactional
    public Book save(Long id, String title, String genre, double averageRating, Author author) {
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional
    public Book update(Long bookId,String title, String genre, double averageRating, Author author) {
        Book book = bookRepository.getById(bookId);
        if(title != null || !title.isEmpty()
                || genre != null || !genre.isEmpty()
                || averageRating!=0 || author!=null
        ){
            book.setTitle(title);
            book.setGenre(genre);
            book.setAverageRating(averageRating);
            book.setAuthor(author);
            book.setId(bookId);
        }
        return book;

    }

    @Override
    @Transactional
    public Book deleteById(Long id) {
        Book book= bookRepository.getById(id);
        bookRepository.delete(book);
        return book;
    }
}
