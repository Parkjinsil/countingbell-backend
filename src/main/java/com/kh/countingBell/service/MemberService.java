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

    // 회워가입 후 아이디 찾기
    public String searchId(MemberDTO memberDTO)
    {
        return memberDAO.searchId(memberDTO.getName(), memberDTO.getPhone());

    }

    // 패스워드 찾기
    public String searchPwd(MemberDTO memberDTO) {
        return memberDAO.searchPwd(memberDTO.getId(), memberDTO.getEmail());
    }

    public Member findUserById(String id) {
        Member member = memberDAO.findById(id).orElse(null);
        if (member != null) {
            return member;
        }
        log.info("없는 계정이거나 유저 계정이 아닙니다.");
        return null;
    }


    // 아이디 중복체크
    public boolean isIdExists(String id) {
        Member member = memberDAO.findById(id).orElse(null);
        return member != null;
    }

    // 닉네임 중복체크
    public boolean isNicknameExists(String nickname) {
        return memberDAO.findByNickname(nickname).isPresent();
    }

}




































