package com.group5.BookRead.services.bookAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BookGoogleAPIAdaptor implements BookAPIAdaptor {


    /**
     *
     * @param response
     * @return
     */
    @Override
    public List<BookFromAPI> convert(final ResponseEntity<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        List<BookFromAPI> res = new ArrayList<>();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode items = root.path("items");

            Iterator<JsonNode> itr = items.iterator();

            while (itr.hasNext()) {
                JsonNode rootNode = itr.next();
                String bookIdentifier = rootNode.path("id").textValue();
                JsonNode parent = rootNode.path("volumeInfo");
                String title = parent.path("title").textValue();
                String authors = parent.path("authors").toString();
                String description = parent.path("description").textValue();
                int page = parent.path("pageCount").asInt();
                String imageLink = parent.
                        path("imageLinks").
                        path("thumbnail").
                        textValue();
                res.add(new BookFromAPI(
                        bookIdentifier,
                        page,
                        authors,
                        title,
                        description,
                        imageLink));
            }
            return res;
        } catch (Exception exception) {
            return res;
        }
    }

    /**
     *  convert single book
     * @param response
     * @return
     */
    @Override
    public BookFromAPI convertBook(final ResponseEntity<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            String bookIdentifier = root.path("id").textValue();
            JsonNode parent = root.path("volumeInfo");
            String title = parent.path("title").textValue();
            String authors = parent.path("authors").toString();
            String description = parent.path("description").textValue();
            int page = parent.path("pageCount").asInt();
            String imageLink = parent.
                    path("imageLinks").
                    path("thumbnail").
                    textValue();
            BookFromAPI book = new BookFromAPI(
                    bookIdentifier,
                    page,
                    authors,
                    title,
                    description,
                    imageLink);
            return book;
        } catch (Exception exception) {
            return null;
        }
    }
}
