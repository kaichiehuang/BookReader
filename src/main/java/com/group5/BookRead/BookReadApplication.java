package com.group5.BookRead;

import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.book.BookService;
import com.group5.BookRead.services.book.ConcreteBookService;
import com.group5.BookRead.services.book.BookDecorator.BookServiceDecorator;
import com.group5.BookRead.services.book.BookDecorator.ConcreteBookServiceDecorator;
import com.group5.BookRead.services.book.excludedBook.ExcludedBookService;

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

    // @Bean(name = "basicBookService") 
    // BookService basicService( 
        
    // ) { 
    //     return new 
    // }  
    
    @Bean(name = "basicDecoratedBookService") 
    ConcreteBookServiceDecorator decoratedService( 
        final BookRepository bookRepository,
        final BookshelfServiceSelector bookshelfServiceSelector,
        final BookshelfRepository bookshelfRepo,
        final MyBookRepository myBookRepository,
        final ExcludedBookService excludedBookService
    ) { 
        BookService basicBookService = new ConcreteBookService(
            bookRepository, 
            myBookRepository
        ); 

        return new ConcreteBookServiceDecorator (
            basicBookService, 
            bookRepository, 
            bookshelfServiceSelector,
            bookshelfRepo,
            myBookRepository,
            excludedBookService
        ); 
    }
}
