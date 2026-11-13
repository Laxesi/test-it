package com.example.testit.repository;

import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookRepositoryIT {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void RetourneLivreEnFonctionDeMemberStatut() {
        Book book = new Book();
        book.setTitle("RetourneLivreEnFonctionDeMemberStatut d");
        book.setAuthor("Dutertre Alexis");
        book.setStatus(Status.DISPONIBLE);
        bookRepository.save(book);
        Member member = new Member();
        member.setUsername("user d");
        member.setBooks(List.of(book));
        memberRepository.save(member);

        //when
        List<Book> result = bookRepository.findByMemberAndStatus(member.getId(), book.getStatus());

        //then
        Assertions.assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(book));
    }

    @Test
    void findByBorrowerId() {
    }
}