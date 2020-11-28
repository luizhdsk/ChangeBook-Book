package com.projeto.changebookbooks.repository;

import com.projeto.changebookbooks.domain.Book;
import com.projeto.changebookbooks.integration.user.response.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByUser(User user);

    List<Book> findAllByCategory(String category);
}
