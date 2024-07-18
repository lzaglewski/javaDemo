package com.example.demo.infrastructure;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepositoryImpl implements MemberRepositoryInterface {
    @PersistenceContext
    private EntityManager entityManager;

    private final JpaMemberRepositoryInterface jpaMemberRepository;

    @Autowired
    JpaMemberRepositoryImpl(JpaMemberRepositoryInterface jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public List<Member> findByEmail(String email) {
        String query = "SELECT m FROM Member m WHERE m.email = :email";
        return entityManager.createQuery(query, Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return jpaMemberRepository.findAll(pageable);
    }

    @Override
    public void add(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaMemberRepository.deleteById(id);
    }
}
