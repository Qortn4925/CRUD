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
            SELECT b.id,
                   b.title,
                   b.content,
                   b.inserted,
                   b.writer,
                   m.nick_name writerNickName
            FROM board b JOIN member m
                    ON b.writer = m.id
            WHERE b.id = #{id}
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
    <script>
                         SELECT b.id, b.title,b.inserted,m.nick_name writerNickName
                         FROM board b join member m
                         on b.writer=m.id
                         <trim prefix="WHERE" prefixOverrides="OR">
                             <if test="searchTarget == 'all' or searchTarget == 'title'">
                                 title LIKE CONCAT('%', #{keyword}, '%')
                             </if>
                             <if test="searchTarget == 'all' or searchTarget == 'content'">
                                 OR content LIKE CONCAT('%', #{keyword}, '%')
                             </if>
                             <if test="searchTarget == 'all' or searchTarget == 'writer'">
                                 OR m.nick_name LIKE CONCAT('%', #{keyword}, '%')
                             </if>
                         </trim>
                         ORDER BY b.id DESC
                         LIMIT #{offset}, 10
                     </script>
""")
    List<Board> selectAllPaging(Integer offset, String searchTarget, String keyword);

    @Select("""
    <script>
    SELECT  count(*) from board
                  <trim prefix="WHERE" prefixOverrides="OR">
                                         <if test="searchTarget == 'all' or searchTarget == 'title'">
                                             title LIKE CONCAT('%', #{keyword}, '%')
                                         </if>
                                         <if test="searchTarget == 'all' or searchTarget == 'content'">
                                             OR content LIKE CONCAT('%', #{keyword}, '%')
                                         </if>
                                         <if test="searchTarget == 'all' or searchTarget == 'writer'">
                                             OR writer LIKE CONCAT('%', #{keyword}, '%')
                                         </if>
                                     </trim>
</script>
""")
    Integer countAll(String keyword,String searchTarget);

}



