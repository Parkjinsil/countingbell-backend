package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Member;
import com.kh.countingBell.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member, String>, QuerydslPredicateExecutor<Member> {

    @Query(value = "SELECT id FROM member WHERE name=:name AND phone=:phone", nativeQuery = true)
    String searchId(String name, String phone);

//    @Query(value = "SELECT password FROM member WHERE id=:id AND phone=:phone", nativeQuery = true)
//    String searchPwd(String name, String phone);
}
