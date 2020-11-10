package com.group5.BookRead.services.myBook;

import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;

@Service(value = "myBook")
public final class RegularMyBookService implements  MyBookService {


    private MyBookRepository myBookRepository;

    @Autowired
    public RegularMyBookService(final MyBookRepository myBookRepository) {
        this.myBookRepository = myBookRepository;
    }

    public MyBook removeFromShelf(final int myBookId) {
        MyBook myBook = myBookRepository.findById(myBookId);
        if (myBookRepository.deleteById(myBookId) == 1) {
            return myBook;
        }
        return null;
    }

    public MyBook addToShelf(final MyBook book, final int bookshelfId) {
        book.setBookshelfId(bookshelfId);
        try {
            if (myBookRepository.insert(book) == 1) {
                return myBookRepository.findById(
                        book.getBookshelfId(),
                        book.getUserId(),
                        book.getBookId());
            }
            return null;
        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        }
    }

    public MyBook getMyBook(final String user,
                            final String bookshelf,
                            final int bookId) {
        return myBookRepository.findByUserAndBookShelfAndBookId(
                user,
                bookshelf,
                bookId);
    }
}
