package com.kh.countingBell.service;

import com.kh.countingBell.domain.Member;
import com.kh.countingBell.domain.MemberDTO;
import com.kh.countingBell.repo.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public List<Member> showAll() {
        return memberDAO.findAll();
    }

    public Member show(String id){
        return memberDAO.findById(id).orElse(null);
    }

    public Member create(Member member) {
        return memberDAO.save(member);
    }

    public Member update(Member member) {
        Member target = memberDAO.findById(member.getId()).orElse(null);
        if(target!=null) {
            return memberDAO.save(member);
        }
        return null;
    }

    public Member delete(String id) {
        Member target = memberDAO.findById(id).orElse(null);
        memberDAO.delete(target);
        return target;
    }

    public Member getByCredentials(String id, String password, PasswordEncoder encoder) {
        Member member = memberDAO.findById(id).orElse(null);
        if(member!=null && encoder.matches(password, member.getPassword())) {
            return member;
        }
        return null;
    }

    // 아이디 찾기
    public String searchId(MemberDTO memberDTO)
    {
        return memberDAO.searchId(memberDTO.getName(), memberDTO.getPhone());

    }

    // 패스워드 찾기
//    public String searchPwd(MemberDTO memberDTO)
//    {
//        return memberDAO.searchPwd(memberDTO.getName(), memberDTO.getPhone());
//
//    }

}




































