package com.example.prjjsp20241022.service;

import com.example.prjjsp20241022.dto.Board;
import com.example.prjjsp20241022.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
        private final BoardMapper mapper;


    public void add(Board board) {
        mapper.insert(board);
    }



    public Map<String,Object> list(Integer page) {
        // List<Board> list= mapper.selectAll();
        // g한 페이지 10개
        Integer offset = (page - 1) * 10;



        //페이지 관련 정보들
        // 총 레코드 수
        Integer countAll= mapper.countAll();
        // 마지막 페이지
        Integer lastPageNumber =  (countAll-1) /10 +1;

        Map<Board> list = mapper.selectAllPaging(offset);

        map<String, Object>
        map.put("lastPageNumber", lastPageNumber);
        map.put("boardList",list);
        return map;
    }

    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public void remove(Integer id) {
        mapper.delete(id);
    }


    public void update(Board board) {
        mapper.update(board);
    }
}
