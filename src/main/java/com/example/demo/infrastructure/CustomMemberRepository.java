package com.example.demo.infrastructure;

import com.example.demo.domain.member.CustomMemberRepositoryInterface;
import com.example.demo.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomMemberRepository implements CustomMemberRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Member> findByEmail(String email) {
        String query = "SELECT m FROM Member m WHERE m.email = :email";
        return entityManager.createQuery(query, Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}
