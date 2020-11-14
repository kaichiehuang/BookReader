package com.group5.BookRead.services.bookAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class GoogleAPIService implements BookAPI {


    private BookAPIAdaptor bookAPIAdaptor;
    private RestTemplate restTemplate;
    @Autowired
    public GoogleAPIService(final RestTemplate restTemplate, final BookAPIAdaptor bookAPIAdaptor) {
        this.bookAPIAdaptor = bookAPIAdaptor;
        this.restTemplate = restTemplate;
    }

    final String keywordsSEARCH = "https://www.googleapis.com/books/v1/volumes?"
            + "q=%s&key=AIzaSyBS_bQYbKIUs7hBTGYpQFtpNJI8hcG76ww&maxResults=20";

    /**
     *  search for books whoes titles match the search key
     * @param searchKey
     * @return a list of BookFromAPI object
     */
    public List<BookFromAPI> getBooks(final String searchKey) {

        String searchUrl
                = String.format(keywordsSEARCH, searchKey);
        ResponseEntity<String> response
                = restTemplate.getForEntity(searchUrl, String.class);
        return bookAPIAdaptor.convert(response);
    }
}
