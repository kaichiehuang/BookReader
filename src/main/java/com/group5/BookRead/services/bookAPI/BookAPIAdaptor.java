package com.group5.BookRead.services.bookAPI;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookAPIAdaptor {

   List<BookFromAPI> convert(ResponseEntity<String> response);
}
