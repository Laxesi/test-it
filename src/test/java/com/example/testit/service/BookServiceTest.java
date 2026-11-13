package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Book;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MailService mailService;

    @Autowired
    BookService bookService;

    @Test
    void RetournerLaListeDesLivres() {
        //given
        Book booka = new Book();
        Book bookb = new Book();
        booka.setTitle("RetournerLaListeDesLivres a");
        bookb.setTitle("RetournerLaListeDesLivres b");
        booka.setAuthor("Dutertre Alexis");
        bookb.setAuthor("Dutertre Alexis");
        bookRepository.save(booka);
        bookRepository.save(bookb);
        List<Book> attendue = List.of(booka, bookb);

        //when
        List<Book> result = bookService.findAll();

        //then
        Assertions.assertThat(result).contains(booka, bookb);
    }

    @Test
    void findById() {
        //given
        Book book = new Book();
        book.setTitle("RetournerLaListeDesLivres c");
        book.setAuthor("Dutertre Alexis");
        bookRepository.save(book);
        List<Book> attendue = List.of(book);

        //when
        Optional<Book> result = bookService.findById(book);

        //then
        Assertions.assertThat(result).contains(booka, bookb);
    }

    @Test
    void findByMemberId() {
    }

    @Test
    void createBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void borrowBook() {
    }

    @Test
    void returnBook() {
    }
}