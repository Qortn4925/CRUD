package com.example.prjjsp20241022.service;


import com.example.prjjsp20241022.dto.Member;
import com.example.prjjsp20241022.mapper.BoardMapper;
import com.example.prjjsp20241022.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper mapper;
    private final BoardMapper boardMapper;


    public void addMember(Member member) {
        mapper.insert(member);
    }

    public List<Member> viewMember() {
        return   mapper.selectAll();

    }

    public Member info(String id) {
      return   mapper.selectById(id);
    }

    public boolean remove(String id, String password) {
        // 게시물 먼저 삭제후 회원 삭제 (외래키 참조 무결성?)
        boardMapper.deleteByMemberId(id);


        int cnt = mapper.deleteByIdAndPassword(id, password);
        return cnt == 1;
    }

    public void update(Member member) {
        mapper.update(member);
    }


    public boolean updatePassword(String id, String oldPassword, String newPassword) {
        int cnt = mapper.updatePassword(id, oldPassword, newPassword);
        return cnt == 1;
    }

    public Member get(String id, String password) {
        Member member = mapper.selectByIdAndPassword(id, password);

        if (member == null) {
            return null;
        }else{
            //id 의 권한 정보를 가져옴
            List<String> authList = mapper.selectAuthById(id);

            member.setAuth(authList);
            return member;
        }
    }

    public boolean hasAccess(String id, Member member) {
        return id.equals(member.getId());
    }
}
