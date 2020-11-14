package com.group5.BookRead;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;

public class TestGoogleBook {

    @Test
    void requestGoogleAPIShouldReturnOK() throws JsonProcessingException {
        String GENERAL_KEYWORDS_SEARCH = "https://www.googleapis.com/books/v1/volumes?q=%s&key=AIzaSyBS_bQYbKIUs7hBTGYpQFtpNJI8hcG76ww";

        RestTemplate restTemplate = new RestTemplate();
        String searchUrl
                = String.format(GENERAL_KEYWORDS_SEARCH, "flowers");
        ResponseEntity<String> response
                = restTemplate.getForEntity(searchUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode items = root.path("items");

        Iterator<JsonNode> itr = items.iterator();

        while(itr.hasNext()) {
            JsonNode parent = itr.next().path("volumeInfo");
            String title = parent.path("title").textValue();
            String authors = parent.path("authors").toString();
            int page = parent.path("pageCount").asInt();
            String imageLink = parent.path("imageLinks").path("thumbnail").textValue();
            System.out.printf("title: %s\nauthors:%s\npage:%d\nimageLink:%s\n\n", title, authors, page, imageLink);
        }
    }
}
