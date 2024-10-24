package com.example.prjjsp20241022.mapper;


import com.example.prjjsp20241022.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {


    @Insert("""
                Insert into member
                (id,password,nick_name,description)
                values(#{id},#{password},#{nickName},#{description})
            """)
    void insert(Member member);

}
