package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<User> users = null;

    private  final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){

        Author a1 = new Author("Jane", "Austen", "United Kingdom",
                "English novelist known for romantic fiction including 'Pride and Prejudice' and 'Sense and Sensibility'.");
        Author a2 = new Author("Fyodor", "Dostoevsky", "Russia",
                "Russian novelist and philosopher, author of psychological classics such as 'Crime and Punishment' and 'The Brothers Karamazov'.");
        Author a3 = new Author("George", "Orwell", "United Kingdom",
                "English novelist, essayist, journalist, and critic, best known for '1984' and 'Animal Farm'.");

        authorRepository.save(a1);
        authorRepository.save(a2);
        authorRepository.save(a3);

        Book b1 = new Book("prva", "romance", 4.3, a1);
        Book b2 = new Book("vtora", "drama", 4.3, a2);
        Book b3 = new Book("treta", "novel", 4.3, a3);

        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);

        if (userRepository.findAll().isEmpty()) {
            users = new ArrayList<>();
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            userRepository.saveAll(users);
        }



    }
}
