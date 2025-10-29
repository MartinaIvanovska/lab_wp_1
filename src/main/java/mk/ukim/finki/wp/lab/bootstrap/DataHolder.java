package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;

    @PostConstruct
    public void init(){
        books = new  ArrayList<>(10);
        books.add(new Book("prva", "romance", 4.3));
        books.add(new Book("vtora", "romance", 4.3));
        books.add(new Book("treta", "romance", 4.3));
        reservations = new  ArrayList<>();
    }
}
