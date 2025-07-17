package com.explorer.controller;

import com.explorer.entity.Book;
import com.explorer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/ping")
    public String ping(){
        return "Pong";
    }

    @GetMapping("/findAll")
    public List<Book> findAllBookDetails(){
        return bookRepository.findAll();
    }

    @GetMapping("/findById/{bookId}")
    public Book findBookById(@PathVariable Long bookId){
        return bookRepository.findById(bookId).get();
    }

    @PostMapping("/add")
    public Book addBookDetails(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/update")
    public void updateBookDetails(@RequestBody Book book){
        bookRepository.findById(book.getId()).ifPresent(b -> bookRepository.save(b));
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteBookDetails(@PathVariable Long bookId){
        bookRepository.deleteById(bookId);
    }

}
