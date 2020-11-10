package com.group5.BookRead.services.bookAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GoogleAPI implements BookAPI {
    private RestTemplate restTemplate;

    @Autowired
    public GoogleAPI(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    final String keywordsSEARCH = "https://www.googleapis.com/books/v1/volumes?"
            + "q=%s&key=AIzaSyBS_bQYbKIUs7hBTGYpQFtpNJI8hcG76ww";

    /**
     *  search for books whoes titles match the search key
     * @param searchKey
     * @return a list of BookFromAPI object
     */
    @Override
    public List<BookFromAPI> getBooks(final String searchKey) {
        String searchUrl
                = String.format(keywordsSEARCH, searchKey);
        ResponseEntity<String> response
                = restTemplate.getForEntity(searchUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<BookFromAPI> res = new ArrayList<>();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode items = root.path("items");

            Iterator<JsonNode> itr = items.iterator();

            while (itr.hasNext()) {
                JsonNode parent = itr.next().path("volumeInfo");
                String title = parent.path("title").textValue();
                String authors = parent.path("authors").toString();
                String description = parent.path("description").textValue();
                int page = parent.path("pageCount").asInt();
                String imageLink = parent.
                        path("imageLinks").
                        path("thumbnail").
                        textValue();
                res.add(new BookFromAPI(
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
}
