package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Member;
import com.kh.countingBell.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberDAO extends JpaRepository<Member, String>, QuerydslPredicateExecutor<Member> {

    //@Query(value = "SELECT * FROM member WHERE authority=:condition", nativeQuery = true)
   // List<Member> findByRoll(String condition);

    @Query(value = "SELECT id FROM member WHERE name=:name AND phone=:phone", nativeQuery = true)
    //Request processing failed:Use @Param for query method parameters 오류나서 @Param값 명시
    String searchId(@Param("name")String name, @Param("phone")String phone);

    @Query(value = "SELECT password FROM member WHERE id=:id AND email=:email", nativeQuery = true)
    String searchPwd(@Param("id")String id, @Param("email")String email);

    Optional<Member> findByNickname(String nickname);
    Optional<Member> findById(String id);


}
