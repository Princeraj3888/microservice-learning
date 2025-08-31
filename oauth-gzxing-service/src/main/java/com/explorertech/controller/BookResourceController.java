package com.explorertech.controller;

import com.explorertech.entity.Book;
import com.explorertech.util.MethodUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookResourceController {

    Logger logger = LoggerFactory.getLogger(BookResourceController.class);

    private final String imagePath = "./qrcodes/QRCode.png";

    @GetMapping("/findAll")
    public ResponseEntity<Collection<Book>> findAll(){
        logger.info("findAll method of BookResourceController");
        Collection<Book> books = List.of(new Book(101L, "Core Java", "12345", "", MethodUtils.generateByteQRCode("d12sa", 250, 250)),
                new Book(102L, "Spring in Action", "1234548", "https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg", MethodUtils.generateByteQRCode("df23afsa", 250, 250)));

        return ResponseEntity.ok(books);
    }

    @GetMapping("/generateImageQRCode/{bookId}")
    public ResponseEntity<Book> generateImageQRCode(@PathVariable("bookId") Long bookId){
        logger.info("generateImageQRCode method of BookResourceController");
        List<Book> books = List.of(new Book(101L, "Core Java", "12345", "", MethodUtils.generateByteQRCode("dfadsafsa", 250, 250)),
                new Book(102L, "Spring in Action", "1234548", "https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg", MethodUtils.generateByteQRCode("ddfdsafafsa", 250, 250)));

        Optional<Book> book = books.stream().filter(b -> b.getId().equals(bookId)).findFirst();
        if(!book.isPresent()) {
            return (ResponseEntity<Book>) ResponseEntity.status(HttpStatus.NO_CONTENT);
        }
        File file = new File(imagePath);
        file.getParentFile().mkdirs();

        MethodUtils.generateImageQRCode(book.get().getCoverPhotoUrl(), 250, 250, imagePath);
        return ResponseEntity.ok(book.get());
    }

    @GetMapping("/generateByteQRCode/{bookId}")
    public ResponseEntity<Book> generateByteQRCode(@PathVariable("bookId") Long bookId){
        logger.info("generateByteQRCode method of BookResourceController");
        List<Book> books = List.of(new Book(101L, "Core Java", "12345", "",
                        MethodUtils.generateByteQRCode("dart", 250, 250)),
                new Book(102L, "Spring in Action", "1234548",
                        "https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg",
                        MethodUtils.generateByteQRCode("https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg", 250, 250)));

        Optional<Book> book = books.stream().filter(b -> b.getId().equals(bookId)).findFirst();
        if(!book.isPresent()) {
            return (ResponseEntity<Book>) ResponseEntity.status(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(book.get());
    }
}
