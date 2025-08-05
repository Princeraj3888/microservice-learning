package com.explorer.aws.api.controller;

import com.explorer.aws.api.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

     List<Book> books = new ArrayList<>();

     @PostMapping
    public Book addBook(@RequestBody Book book){
        books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getBooks(){
         return books;
    }
}
