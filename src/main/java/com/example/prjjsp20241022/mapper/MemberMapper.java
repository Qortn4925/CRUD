package com.example.prjjsp20241022.mapper;


import com.example.prjjsp20241022.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {


    @Insert("""
                Insert into member
                (id,password,nick_name,description)
                values(#{id},#{password},#{nickName},#{description})
            """)
    void insert(Member member);

    @Select("""
                select *
                from member
            """)
    List<Member> selectAll();

    @Select("""
    select *
    from member
    where id=#{id}
""")
    Member selectById(String id);

    @Delete("""
            DELETE FROM member
            WHERE id = #{id} 
              AND password = #{password}
            """)
    int deleteByIdAndPassword(String id, String password);

    @Update("""
            UPDATE member
            SET nick_name = #{nickName},
                description = #{description}
            WHERE id = #{id}
            """)
    int update(Member member);

    @Update("""
            UPDATE member
            SET password = #{newPassword}
            WHERE id = #{id}
              AND password = #{oldPassword}
            """)
    int updatePassword(String id, String oldPassword, String newPassword);

    @Select("""
    select *
    from member
    where id=${id}
    and password =${password}
"""
    )
    Member selectByIdAndPassword(String id, String password);

    @Select("""
    select name
    from auth
    where id = #{id}
""")
    List<String> selectAuthById(String id);
}
