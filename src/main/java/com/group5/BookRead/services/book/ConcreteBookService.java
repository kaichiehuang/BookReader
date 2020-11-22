package com.group5.BookRead.services.book;


import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service(value = "book")
public final class ConcreteBookService implements BookService {

    private MyBookRepository myBookRepository;
    private BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    public ConcreteBookService(final MyBookRepository mybookRepository,
                    final  BookshelfServiceSelector bookshelfServiceSelector) {
        this.myBookRepository = mybookRepository;
        this.bookshelfServiceSelector = bookshelfServiceSelector;
    }

    @Override
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

    @Override
    public List<Bookshelf> getBookShelves(final int userId) {
        return bookshelfServiceSelector.getBookShelves(userId);
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
    public MyBook getMyBook(final int userId,
                            final int bookshelfId,
                            final int bookId) {
        return myBookRepository.findByAllIds(bookId, userId, bookshelfId);
    }


}
