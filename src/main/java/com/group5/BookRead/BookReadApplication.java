package com.group5.BookRead;

import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.ExcludedBookRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.book.BookService;
import com.group5.BookRead.services.book.BasicBookService;
import com.group5.BookRead.services.book.BookDecorator.BookServiceDecorator;
import com.group5.BookRead.services.book.BookDecorator.BookShelfServiceDecorator;
import com.group5.BookRead.services.book.BookDecorator.ExcludedBookServiceDecorator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookReadApplication {
    public static void main(final String[] args) {
        SpringApplication.run(BookReadApplication.class, args);
    }

    @Bean
    public static RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean(name = "basicDecoratedBookService") 
    BookServiceDecorator decoratedService( 
        final BookRepository bookRepository,
        final BookshelfServiceSelector bookshelfServiceSelector,
        final BookshelfRepository bookshelfRepo,
        final MyBookRepository myBookRepository,
        final ExcludedBookRepository excludedBookRepository
    ) { 
        BookService basicBookService = new BasicBookService(
            bookRepository, 
            myBookRepository
        ); 

        BookService excludedBookService = new ExcludedBookServiceDecorator(
            basicBookService, excludedBookRepository);

        return new BookShelfServiceDecorator (
            excludedBookService , 
            bookRepository, 
            bookshelfServiceSelector,
            bookshelfRepo,
            myBookRepository
        ); 
    }
}
