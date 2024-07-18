package com.example.demo.domain.member;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryInterface {
    List<Member> findByEmail(String name);

    Page<Member> findAll(Pageable pageable);

    void add(Member member);

    Optional<Member> findById(Long id);

    void deleteById(Long id);
}
