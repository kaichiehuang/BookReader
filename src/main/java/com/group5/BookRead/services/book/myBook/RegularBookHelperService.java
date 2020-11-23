package com.group5.BookRead.services.book.myBook;


import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookshelfRepository;
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
    private BookshelfRepository bookshelfRepo;

    @Autowired
    public RegularBookHelperService(final MyBookRepository mybookRepository,
                    final  BookshelfServiceSelector bookshelfServiceSelector,
                    final BookshelfRepository bookshelfRepo) {
        this.myBookRepository = mybookRepository;
        this.bookshelfServiceSelector = bookshelfServiceSelector;
        this.bookshelfRepo = bookshelfRepo;
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

    /**
     * get all MyBook object with user and book
     * @param bookId
     * @param userId
     * @return List of mybook
     */
    @Override
    public List<MyBook> getMyBooks(final int userId,
                            final int bookId) {
        return myBookRepository.findAllMybooks(userId, bookId);
    }


    @Override
    public MyBook getMyBook(final int userId,
                            final int bookshelfId,
                            final int bookId) {
        return myBookRepository.findByAllIds(bookId, userId, bookshelfId);
    }

    @Override
    public List<Bookshelf> getBookShelves(final int userId) {
        return bookshelfServiceSelector.getBookShelves(userId);
    }

    // need to move in decorator
    @Override
    public double updateProgress (final int userId, 
        final int bookId, final double progress){

        List<MyBook> mybooks = this.getMyBooks(userId, bookId);
        for (MyBook b : mybooks){
            b.setProgress(progress);
            myBookRepository.update(b);
        }
        return progress;
    }
    
    /**
     * get currect bookshelf that the book located in current user
     * only return: want to read, reading, and read used for automatical 
     * moving bookshelf
     * @param bookId
     * @param userId
     * @return bookshelf
     */
    @Override
    public Bookshelf getReadingShelf(final int userId, final int bookId){
        List<MyBook> mybooks = this.getMyBooks(userId, bookId);
        for (MyBook b : mybooks){
            Bookshelf shelf = bookshelfRepo.findById(b.getBookshelfId());
            String name = shelf.getName();
            if (name.equals("want to read") 
                || name.equals("reading")
                || name.equals("read")){
                return shelf;
            }
        }
        System.out.println("book not in want to read, reading or read");
        return null;
    }
}
