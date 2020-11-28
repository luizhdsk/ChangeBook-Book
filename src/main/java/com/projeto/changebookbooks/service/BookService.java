package com.projeto.changebookbooks.service;

import com.projeto.changebookbooks.config.Messages;
import com.projeto.changebookbooks.config.exception.BookException;
import com.projeto.changebookbooks.domain.Book;
import com.projeto.changebookbooks.integration.user.response.User;
import com.projeto.changebookbooks.repository.BookRepository;
import lombok.val;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(User user, Book book) {
        book.setUser(user);
        bookRepository.save(book);
    }

    public List<Book> getBooks(User user) {
        return bookRepository.findAllByUser(user);
    }

    public Book getBookById(String bookId, User user) {
        val book = bookRepository.findById(bookId).orElseThrow(() -> {throw new BookException(Messages.BOOK_NOT_FOUND);});
        if (!book.getUser().getEmail().equals(user.getEmail()))
            throw new BookException(Messages.BOOK_NOT_FOUND);
        return book;
    }

    public void deleteBookById(String bookId, User user) {
        val book = bookRepository.findById(bookId).orElseThrow(() -> {throw new BookException(Messages.BOOK_NOT_FOUND);});
        if (!book.getUser().getEmail().equals(user.getEmail()))
            throw new BookException(Messages.BOOK_NOT_FOUND);
        bookRepository.delete(book);
    }


    public List<Book> getBookByCategory(String category) {
        return bookRepository.findAllByCategory(category);
    }
}
