package com.projeto.changebookbooks.controller;

import com.projeto.changebookbooks.domain.Book;
import com.projeto.changebookbooks.integration.user.client.UserClient;
import com.projeto.changebookbooks.service.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/change-book/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    private UserClient userClient;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(
            @RequestBody @Valid Book book,
            @RequestHeader String Authorization){
        val user = userClient.getUserByToken(Authorization);
        bookService.createBook(user, book);
    }

    @GetMapping
    public ResponseEntity<?> getBooks(@RequestHeader String Authorization){
        val user = userClient.getUserByToken(Authorization);
        return ResponseEntity.ok().body(bookService.getBooks(user));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(
            @PathVariable("bookId") String bookId,
            @RequestHeader String Authorization){
        val user = userClient.getUserByToken(Authorization);
        return ResponseEntity.ok().body(bookService.getBookById(bookId,user));
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBookById(
            @PathVariable("bookId") String bookId,
            @RequestHeader String Authorization){
        val user = userClient.getUserByToken(Authorization);
        bookService.deleteBookById(bookId,user);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getBookByCategory(@RequestParam String category){
        return ResponseEntity.ok().body(bookService.getBookByCategory(category));
    }

}
