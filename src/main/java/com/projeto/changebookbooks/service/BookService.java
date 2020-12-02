package com.projeto.changebookbooks.service;

import com.projeto.changebookbooks.config.Messages;
import com.projeto.changebookbooks.config.exception.BookException;
import com.projeto.changebookbooks.controller.BookController;
import com.projeto.changebookbooks.domain.Book;
import com.projeto.changebookbooks.integration.user.response.User;
import com.projeto.changebookbooks.repository.BookRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;


    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(User user, Book book) {
        book.setUser(user);
        bookRepository.save(book);
        logger.info(user.toString());
    }

    public List<Book> getBooks(User user) {
        return bookRepository.findAllByUser(user);
    }

    public Book getBookById(String bookId, User user) {
        logger.info(bookRepository.findById(bookId).toString());
        Optional<Book> book = bookRepository.findById(bookId);
        logger.info(book.get().toString());
        if (!book.getUser().getCpf().equals(user.getCpf()))
            logger.info(book.toString());
            throw new BookException(Messages.BOOK_NOT_FOUND);
        return book.get();
    }

    public void deleteBookById(String bookId, User user) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> {throw new BookException(Messages.BOOK_NOT_FOUND);});
        if (!book.getUser().getCpf().equals(user.getCpf()))
            throw new BookException(Messages.BOOK_NOT_FOUND);
        bookRepository.delete(book);
    }


    public List<Book> getBookByCategory(String category) {
        return bookRepository.findAllByCategory(category);
    }
}
