package com.example.prjjsp20241022.mapper;

import com.example.prjjsp20241022.dto.Board;
import com.example.prjjsp20241022.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface BoardMapper {


    @Insert("""
        INSERT INTO  board
        (title,content,writer)
        values (#{board.title},#{board.content},#{member.id})
""")
    @Options(useGeneratedKeys = true , keyProperty = "board.id")
    int insert(Board board, Member member);


    // 게시글은 보통 처음부터가 아니라 최근게 올라가니까
    @Select("""
    SELECT  *
    FROM board
    ORDER BY id DESC
""")
    List<Board> selectAll();

    @Select("""
    select * from board
    WHERE id =#{id}
    """)
    Board selectById(Integer id);




    @Delete("""
    delete from board
    where id =#{id}
""")
    void delete(Integer id);


    @Update("""
        UPDATE board
                    set  title=#{title},content=#{content}
        where id =#{id}
""")
    int update(Board board);

    @Select("""
    select * from board
    order by id desc
    LIMIT  #{offset},10
""")

    List<Board> selectAllPaging(Integer offset);

    @Select("""
    SELECT  count(*) from board
""")
    Integer countAll();

}
