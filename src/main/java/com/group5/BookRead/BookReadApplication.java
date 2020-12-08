package com.group5.BookRead;

import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.book.BookService;
import com.group5.BookRead.services.book.ConcreteBookService;
import com.group5.BookRead.services.book.BookDecorator.BookServiceDecorator;
import com.group5.BookRead.services.book.BookDecorator.ConcreteBookServiceDecorator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;
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
    BookServiceDecorator decoratedService( 
        final BookRepository bookRepo,
        final MyBookRepository myBookRepository,
        final BookshelfServiceSelector bookshelfServiceSelector
    ) { 
        BookService basicBookService = new ConcreteBookService(myBookRepository, bookshelfServiceSelector); 
        return new ConcreteBookServiceDecorator(bookRepo, basicBookService); 
    }
}
