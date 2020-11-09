package com.group5.BookRead.services.myBook;

import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service(value = "regular")
public class RegularMyBookService implements  MyBookService {

    @Autowired
    MyBookRepository myBookRepository;

    public MyBook removeFromShelf(int myBookId) {
        MyBook myBook = myBookRepository.findById(myBookId);
        if (myBookRepository.deleteById(myBookId) == 1) return myBook;
        return null;
    }

    public MyBook addToShelf(MyBook book, int bookshelfId) {
        book.setBookshelf_id(bookshelfId);
        try {
            if (myBookRepository.insert(book) == 1)
                return myBookRepository.findById(
                    book.getBookshelf_id(),
                    book.getUser_id(),
                    book.getBook_id());
            return null;
        } catch(SQLIntegrityConstraintViolationException e) {
            return null;
        }
    }

    public MyBook getMyBook(String username, String bookshelfName, String bookId) {
        return myBookRepository.findByUsernameAndBookShelfnameAndBookId(username, bookshelfName, bookId);
    }
}
