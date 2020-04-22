package com.sky7th.springdatajpashop.repository;

import com.sky7th.springdatajpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByName(String name);
}
