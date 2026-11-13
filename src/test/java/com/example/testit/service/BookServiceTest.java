package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
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
        Assertions.assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(attendue);
    }

    @Test
    void RetournerLeLivreDUnIdExistant() {
        //given
        Book book = new Book();
        book.setTitle("RetournerLeLivreDUnIdExistant c");
        book.setAuthor("Dutertre Alexis");
        bookRepository.save(book);

        //when
        Optional<Book> result = bookService.findById(book.getId());

        //then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void RetournerLeLivreDUnIdInexistant() {
        //given

        //when
        Optional<Book> result = bookService.findById(999L);

        //then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void RetournerLesLivresParLeMembre() {
        //given
        Book book = new Book();
        book.setTitle("RetournerLesLivresParLeMembre d");
        book.setAuthor("Dutertre Alexis");
        bookRepository.save(book);
        Member member = new Member();
        member.setUsername("user d");
        member.setBooks(List.of(book));
        memberRepository.save(member);

        //when
        List<Book> result = bookService.findByMemberId(member.getId());

        //then
        Assertions.assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(book));
    }

    @Test
    void updateBook() {

    }

    @Test
    void deleteBook() {
    }

    @Test
    void unmprinterUnLivreDisponible() {
        //given
        Book book = new Book();
        book.setTitle("RetournerLeLivreDUnIdExistant c");
        book.setAuthor("Dutertre Alexis");
        bookRepository.save(book);
        Member member = new Member();
        member.setUsername("user d");
        memberRepository.save(member);

        //when
        bookService.borrowBook(book.getId(), member.getId());

        //then
        Book bookAttendue = new Book();
        bookAttendue = book;
        bookAttendue.setStatus(Status.EMPRUNTE);
        bookAttendue.setBorrower(member);
        Optional<Book> byId = bookService.findById(book.getId());

        Assertions.assertThat(byId.get()).usingRecursiveComparison().isEqualTo(bookAttendue);
    }

    @Test
    void returnBook() {
    }
}