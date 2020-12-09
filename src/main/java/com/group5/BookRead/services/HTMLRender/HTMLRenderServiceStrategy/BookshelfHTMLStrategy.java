package com.group5.BookRead.services.HTMLRender.HTMLRenderServiceStrategy;

import java.util.List;
import java.util.Map;

import com.group5.BookRead.models.Book;

import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.HTMLRender.HTMLRenderService;
import com.group5.BookRead.services.HTMLRender.StrategyName;
import com.group5.BookRead.services.book.DecoratorChainException;
import com.group5.BookRead.services.book.BookDecorator.BookServiceDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class BookshelfHTMLStrategy implements HTMLRenderService {
    @Autowired
    @Qualifier("basicDecoratedBookService")
    BookServiceDecorator bookServiceDecorator;

    @Autowired
    BookServiceSelector bookServiceSelector;

    @Override
    public String renderPage(final Model model) throws DecoratorChainException{
        SecurityContext context = SecurityContextHolder.getContext();
        int userId = Integer.parseInt(context.getAuthentication()
            .getPrincipal().toString());


        Map<String, List<Book>> bookshelfs =
            bookServiceSelector.getBooksFromShelves(userId);
        //System.out.println(bookshelfs);
        model.addAttribute("bookshelfs", bookshelfs);
        return "bookshelf";
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.BookshelfHTMLStrategy;
    }
}