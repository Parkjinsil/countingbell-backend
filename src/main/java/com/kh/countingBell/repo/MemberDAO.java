package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member, String> {
}
