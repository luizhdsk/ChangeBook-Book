package com.projeto.changebookbooks.controller;

import com.projeto.changebookbooks.config.exception.BookException;
import com.projeto.changebookbooks.domain.Book;
import com.projeto.changebookbooks.integration.user.client.UserClient;
import com.projeto.changebookbooks.integration.user.response.User;
import com.projeto.changebookbooks.service.BookService;
import com.projeto.changebookbooks.service.SequenceServiceGenerator;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/change-book/v1/books")
public class BookController {

    private BookService bookService;
    private SequenceServiceGenerator sequenceServiceGenerator;

    Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private UserClient userClient;

    @Autowired
    public BookController(BookService bookService, SequenceServiceGenerator sequenceServiceGenerator) {
        this.bookService = bookService;
        this.sequenceServiceGenerator= sequenceServiceGenerator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(
            @RequestBody @Valid Book book,
            @RequestHeader String Authorization){
        User user = userClient.getUserByToken(Authorization);
        book.setId(sequenceServiceGenerator.generateSequence(Book.SEQUENCE_NAME));
        bookService.createBook(user, book);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBook(
            @RequestBody @Valid Book book,
            @RequestHeader String Authorization){
        User user = userClient.getUserByToken(Authorization);
        if (!user.getCpf().isEmpty())
            bookService.updateBook(book);
        else
            throw new BookException("Usuário inválido.");
    }

    @GetMapping
    public ResponseEntity<?> getBooks(@RequestHeader String Authorization){
        User user = userClient.getUserByToken(Authorization);
        return ResponseEntity.ok().body(bookService.getBooks(user));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(
            @PathVariable("bookId") String bookId,
            @RequestHeader String Authorization){
        User user = userClient.getUserByToken(Authorization);
        return ResponseEntity.ok().body(bookService.getBookById(bookId,user));
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBookById(
            @PathVariable("bookId") String bookId,
            @RequestHeader String Authorization){
        User user = userClient.getUserByToken(Authorization);
        bookService.deleteBookById(bookId,user);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getBookByCategory(@RequestParam String category){
        return ResponseEntity.ok().body(bookService.getBookByCategory(category));
    }

}
