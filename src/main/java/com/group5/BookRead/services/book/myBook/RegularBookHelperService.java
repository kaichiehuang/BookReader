package com.group5.BookRead.services.book.myBook;


import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Component
public final class RegularBookHelperService implements BookHelperService {

    private MyBookRepository myBookRepository;
    private BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    public RegularBookHelperService(final MyBookRepository mybookRepository,
                              final  BookshelfServiceSelector bookshelfServiceSelector) {
        this.myBookRepository = mybookRepository;
        this.bookshelfServiceSelector = bookshelfServiceSelector;
    }


    public Bookshelf getShelf(final String bookshelf, final int userId) {
        return bookshelfServiceSelector.getBookShelf(userId, bookshelf);
    }

    @Override
    public boolean remove(final int bookId,
                          final int userId,
                          final String bookshelf) {
        Bookshelf shelf = bookshelfServiceSelector.getBookShelf(userId,
                bookshelf);
        int status = myBookRepository.deleteByUserIdAndBookshelfIdAndBookId(
                userId,
                shelf.getId(),
                bookId);
        return  status == 1;
    }

    @Override
    public boolean addToShelf(final MyBook book) {
        try {
            return myBookRepository.insert(book) == 1;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    /**
     *  Heleper methods for Book service
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public List<MyBook> getMyBooks(final String bookshelfName,
                                   final int userId) {
        Bookshelf bookshelf = bookshelfServiceSelector.getBookShelf(
                userId,
                bookshelfName);
        return myBookRepository.findAllByUserIdAndShelfId(
                userId,
                bookshelf.getId());
    }


    @Override
    public MyBook getMyBook(final String user,
                            final String bookshelf,
                            final int bookId) {
        return myBookRepository.findByUsernameAndBookShelfnameAndBookId(
                user,
                bookshelf,
                bookId);
    }

    @Override
    public List<Bookshelf> getBookShelves(final int userId) {
        return bookshelfServiceSelector.getBookShelves(userId);
    }


}
