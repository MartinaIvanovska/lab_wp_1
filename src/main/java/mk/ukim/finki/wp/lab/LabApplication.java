package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.web.BookListServlet;
import mk.ukim.finki.wp.lab.web.BookReservationServlet;
import mk.ukim.finki.wp.lab.web.HelloServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

@ServletComponentScan
@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<BookListServlet> bookListServlet(BookService bookService,
                                                                    SpringTemplateEngine springTemplateEngine) {
        BookListServlet servlet = new BookListServlet(bookService, springTemplateEngine);
        return new ServletRegistrationBean<>(servlet, "/");
    }
    @Bean
    public ServletRegistrationBean<BookReservationServlet> bookReservationServlet(BookReservationService bookReservationService,
                                                                                  SpringTemplateEngine springTemplateEngine) {
        BookReservationServlet servlet = new BookReservationServlet(bookReservationService, springTemplateEngine);
        return new ServletRegistrationBean<>(servlet, "/bookReservation");
    }
}
