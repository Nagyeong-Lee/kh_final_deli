package kh.deli.domain.owner.service;

import kh.deli.domain.owner.dto.BoardDTO;
import kh.deli.domain.owner.dto.Criteria;
import kh.deli.domain.owner.dto.PagingDTO;
import kh.deli.domain.owner.mapper.OwnerCommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.CropImageFilter;
import java.util.List;
import java.util.Map;

@Service
public class OwnerCommunityService {
    @Autowired
    private OwnerCommunityMapper mapper;

    //글쓰기
    public void insert(BoardDTO boardDTO) throws Exception{
        mapper.insert(boardDTO);
    }

    //글 전체 목록 출력

    public List<BoardDTO> select(Integer getPageStart, Integer pageStartToPerPageNum) throws Exception{
        return mapper.select(getPageStart,pageStartToPerPageNum);
    }

    public String getNickname(Integer b_seq) throws Exception{
        return mapper.getNickname(b_seq);
    }


    //조회수 증가
    public void count(Integer b_seq) throws Exception{
        mapper.count(b_seq);
    }

    //글 상세페이지로 이동
    public BoardDTO detailBoard(Integer b_seq) throws Exception{
        return mapper.detailBoard(b_seq);
    }

    public void delete(Integer b_seq) throws Exception{
        mapper.delete(b_seq);
    }

    //글수정
    public void update(Integer b_seq, String b_title, String b_contents) throws Exception{
        mapper.update(b_seq,b_title,b_contents);
    }

    //전체 글 개수
    public int countPost() throws Exception{
        return mapper.countPost();
    }

}
