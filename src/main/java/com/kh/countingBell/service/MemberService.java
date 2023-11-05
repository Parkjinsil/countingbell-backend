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

    // 로그인 시 인증 수행
    // 주어진 id와 password를 기반으로 회원을 인증(authenticate)하는데 사용
    public Member getByCredentials(String id, String password, PasswordEncoder encoder) {
        Member member = memberDAO.findById(id).orElse(null);
       // id에 해당하는 회원이 존재하면 해당 회원을 반환하고, 그렇지 않으면 null을 반환
        if(member!=null && encoder.matches(password, member.getPassword())) {
            // password를 encoder를 사용하여 암호화한 후, 해당 암호화된 값이 member 객체의 비밀번호와 일치하는지를 확인
            return member;
        }
        return null;
    }

    // 회원가입 후 아이디 찾기
    // name과 phone을 기반으로 회원을 찾아 아이디를 반환
    public String searchId(MemberDTO memberDTO)
    {
        return memberDAO.searchId(memberDTO.getName(), memberDTO.getPhone());
    }

    // 패스워드 찾기
    public String searchPwd(MemberDTO memberDTO) {
        return memberDAO.searchPwd(memberDTO.getId(), memberDTO.getEmail());
    }


    // 아이디 찾기
    // id를 기반으로 회원을 찾아 반환하거나, 존재하지 않을 경우 예외 상황을 처리
    // 사용자의 프로필을 보여주거나 특정 회원의 정보를 확인할 때 사용
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
        // true : 중복된 아이디
    }

    // 닉네임 중복체크
    public boolean isNicknameExists(String nickname) {
        return memberDAO.findByNickname(nickname).isPresent();
        // false : 중복된 닉네임
    }

}



































