package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "BookListServlet", urlPatterns = "/")
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine ;

    public BookListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    public BookListServlet() {
        bookService=null;
        springTemplateEngine=null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("books", bookService.listAll());

        springTemplateEngine.process("listBooks.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        Double rating = Double.parseDouble(req.getParameter("rating"));

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        List<Book> books= new ArrayList<>();
        try {
            books = this.bookService.searchBooks(text, rating);
        }catch (Exception e){
            System.out.println( e.getMessage() );
        }


        context.setVariable("books", books);
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }
}
