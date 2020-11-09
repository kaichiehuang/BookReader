package com.group5.BookRead.controllers;

import java.util.*; 

public class BookBaseController {

    static protected Map<String, List<MockupBook>> bookshelfs = new HashMap<>();

    protected class MockupBook{
        public String title;
        public String author;
        public int page;
        public String summary;
        public int id;

        protected MockupBook(final String t, final String a, final int p, 
            final String s, final int id){
            this.title = t;
            this.author = a;
            this.page = p;
            this.summary = s;
            this.id = id;
        }

        public String toString(){
            return "id: " + id + "\ntitle: " + title + "\nauthor: " + 
                author + "\npage: " + page + "\nsummary: " + summary;
        }
    }

    public BookBaseController(){
        List<MockupBook> books = new ArrayList<MockupBook>(); 
        
        books.add(new MockupBook("test1", "author1", 200, "dummySumm1", 1));
        books.add(new MockupBook("test2", "author2", 200, "dummySumm2", 2));
        books.add(new MockupBook("test3", "author3", 200, "dummySumm3", 3));
        books.add(new MockupBook("test4", "author4", 200, "dummySumm4", 4));
        books.add(new MockupBook("test5", "author5", 200, "dummySumm5", 5));
        books.add(new MockupBook("test6", "author6", 200, "dummySumm6", 6));
        books.add(new MockupBook("test7", "author7", 200, "dummySumm7", 7));
        books.add(new MockupBook("test8", "author8", 800, "dummySumm8", 8));
        books.add(new MockupBook("test9", "author9", 200, "dummySumm9", 9));
        books.add(new MockupBook("test10", "author10", 200, "dummySumm10", 10));
        books.add(new MockupBook("test11", "author11", 200, "dummySumm11", 11));

        List<MockupBook> books2 = new ArrayList<MockupBook>(books); 
        List<MockupBook> books3 = new ArrayList<MockupBook>(books); 
        List<MockupBook> books4 = new ArrayList<MockupBook>(books); 

        BookBaseController.bookshelfs.put("Reading", books);
        BookBaseController.bookshelfs.put("Want to Read", books2);
        BookBaseController.bookshelfs.put("Read", books3);
        BookBaseController.bookshelfs.put("Favorites", books4);
    }

    static public MockupBook removeMockupBook(int id, String type) {
        for(MockupBook book : bookshelfs.get(type)) {
            if(book.id == id) {
                bookshelfs.get(type).remove(book);
                return book;
            }
        }
        return null;
    }
}
