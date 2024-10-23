package com.example.prjjsp20241022.service;

import com.example.prjjsp20241022.dto.Board;
import com.example.prjjsp20241022.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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



        List<Board> list = mapper.selectAllPaging(offset);

        // Controller에게 넘겨 줄 정보들을 담을 map
        Map<String, Object> map = new HashMap<>();

        // 페이지 관련 정보들
        Integer countAll = mapper.countAll();
        Integer lastPageNumber = (countAll - 1) / 10 + 1; // 마지막 페이지 번호

        Integer rightPageNumber = ( (page-1) / 10 +1)*10 ;
        Integer leftPageNumber = rightPageNumber - 9;
        Integer nextPageNumber = rightPageNumber + 1;
        Integer previousPageNumber = leftPageNumber - 1;

        Boolean hasNextPage =nextPageNumber < lastPageNumber;   // 다음 버튼 존재 유무
        Boolean  hasPrevPage = previousPageNumber > 0;    // 이전 버튼 존재 유뮤
        // 오른쪽 끝 페이지는 마지막 페이지 보다 클 수 없음
        rightPageNumber =Math.min(rightPageNumber,lastPageNumber);
        Map<String,Object> pageInfo =new HashMap<>();

        pageInfo.put("hasNextPage",hasNextPage);
        pageInfo.put("hasPrevPage",hasPrevPage);
        pageInfo.put("nextPageNumber",nextPageNumber);
        pageInfo.put("previousPageNumber",previousPageNumber);
        pageInfo.put("rightPageNumber",rightPageNumber);
pageInfo.put("leftPageNumber",leftPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);
        pageInfo.put("currentPageNumber", page);

        map.put("pageInfo",pageInfo);
        map.put("boardList", list);

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
