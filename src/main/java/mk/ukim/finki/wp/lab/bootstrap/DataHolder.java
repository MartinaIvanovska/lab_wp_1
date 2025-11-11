package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    @PostConstruct
    public void init(){
        authors = new  ArrayList<>(10);
        Author a1=new Author("Jane", "Austen", "United Kingdom",
                "English novelist known for romantic fiction including 'Pride and Prejudice' and 'Sense and Sensibility'.");
        Author a2= new Author("Fyodor", "Dostoevsky", "Russia",
                "Russian novelist and philosopher, author of psychological classics such as 'Crime and Punishment' and 'The Brothers Karamazov'.");
        Author a3= new Author("George", "Orwell", "United Kingdom",
                "English novelist, essayist, journalist, and critic, best known for '1984' and 'Animal Farm'.");
        books = new  ArrayList<>(10);
        books.add(new Book("prva", "romance", 4.3,a1));
        books.add(new Book("vtora", "drama", 4.3,a2));
        books.add(new Book("treta", "novel", 4.3,a3));
        reservations = new  ArrayList<>();
        authors.add(a1);
        authors.add(a2);
        authors.add(a3);

    }
}
