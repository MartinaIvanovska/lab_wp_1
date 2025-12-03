package mk.ukim.finki.wp.lab.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByAuthorId(Long id);
    List<Book> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String genre);
    List<Book> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCaseAndAverageRatingGreaterThanEqual(String title, String genre, double averageRating);
}
