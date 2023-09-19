package com.kh.countingBell.service;

import com.kh.countingBell.domain.Member;
import com.kh.countingBell.repo.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

}




































