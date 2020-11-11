package com.group5.BookRead.controllers;

import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.BookshelfServiceSelector;

import org.springframework.beans.factory.annotation.Autowired;

abstract class BookController {
    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    BookServiceSelector bookServiceSelector;

    public static final int DUMMYID = 3;

    // public static final String DUMMYSUMM = "DUMMYSUMM";
    // public static final String DUMMYTITLE = "test";
    // public static final String DUMMYAUTHOR = "author";
    // public static final int DUMMYPAGE = 200;
    // public static final int DUMMYTOTALBOOK = 11;

    // protected class MockupBook {
    //     public String title;
    //     public String author;
    //     public int page;
    //     public String summary;
    //     public int id;

    //     protected MockupBook(final String t, final String a, final int p,
    //         final String s, final int bookId) {
    //         this.title = t;
    //         this.author = a;
    //         this.page = p;
    //         this.summary = s;
    //         this.id = bookId;
    //     }

    //     /**
    //      * <p> convert to mockup book to string
    //      * </p>
    //      * @return string of mockup book
    //      * @since 1.0
    //      */
    //     public String toString() {
    //         return "id: " + id + "\ntitle: " + title + "\nauthor: "
    //             + author + "\npage: " + page + "\nsummary: " + summary;
    //     }
    // }

    // public BookBaseController() {
    //     List<MockupBook> books = new ArrayList<MockupBook>();

    //     for (int i = 1; i < DUMMYTOTALBOOK; i++) {
    //         books.add(new MockupBook(
    //             DUMMYTITLE + i, DUMMYAUTHOR + i,
    //             DUMMYPAGE, DUMMYSUMM + 1, i));
    //     }

    //     List<MockupBook> books2 = new ArrayList<MockupBook>(books);
    //     List<MockupBook> books3 = new ArrayList<MockupBook>(books);
    //     List<MockupBook> books4 = new ArrayList<MockupBook>(books);

    //     BookBaseController.bookshelfs.put("Reading", books);
    //     BookBaseController.bookshelfs.put("Want to Read", books2);
    //     BookBaseController.bookshelfs.put("Read", books3);
    //     BookBaseController.bookshelfs.put("Favorites", books4);
    // }

    // public static MockupBook removeMockupBook(final int id,
    //    final String type) {
    //     for (MockupBook book : bookshelfs.get(type)) {
    //         if (book.id == id) {
    //             bookshelfs.get(type).remove(book);
    //             return book;
    //         }
    //     }
    //     return null;
    // }
}
