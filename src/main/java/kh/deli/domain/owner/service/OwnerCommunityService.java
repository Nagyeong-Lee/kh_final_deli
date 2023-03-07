package kh.deli.domain.owner.service;

import kh.deli.domain.owner.dto.BoardDTO;
import kh.deli.domain.owner.mapper.OwnerCommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerCommunityService {
    @Autowired
    private OwnerCommunityMapper mapper;

    //글쓰기
    public void insert(BoardDTO boardDTO) throws Exception{
        mapper.insert(boardDTO);
    }

    //글 전체 목록 출력

    public List<BoardDTO> select(Integer start, Integer end) throws Exception{
        return mapper.select(start, end);
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

    //페이징 처리
    public String getBoardPageNavi(int currentPage, Integer count) throws Exception {
        int postTotalCount = this.countPost();

        int recordCountPerPage = count; // 페이지 당 게시글 개수
        int naviCountPerPage = 10; // 내비 개수

        int pageTotalCount = 0; // 전체 내비 수
        if(postTotalCount % recordCountPerPage > 0) {
            pageTotalCount = postTotalCount / recordCountPerPage + 1;
        }else {
            pageTotalCount = postTotalCount / recordCountPerPage;
        }

        if(currentPage < 1) {
            currentPage = 1;
        }else if(currentPage > pageTotalCount) {
            currentPage = pageTotalCount;
        }

        int startNavi = (currentPage-1) / naviCountPerPage * naviCountPerPage + 1; // 페이지 시작 내비 값
        int endNavi = startNavi + naviCountPerPage - 1; // 페이지 마지막 내비 값

        if(endNavi > pageTotalCount) {
            endNavi = pageTotalCount;
        }

        boolean needPrev = true;
        boolean needNext = true;

        if(startNavi == 1) {needPrev = false;}
        if(endNavi == pageTotalCount) {needNext = false;}

        StringBuilder sb = new StringBuilder();

        if(needPrev) {
            sb.append("<a href='/owner/community/list?currentPage="+(startNavi-1)+"&count="+count+"'><</a> ");
        }

        for(int i = startNavi; i <= endNavi; i++) {
            if(currentPage == i){
                sb.append("<a href='/owner/community/list?currentPage="+i+"&count="+count+"'><b>" + i + "</b></a> ");

            }else {
                sb.append("<a href='/owner/community/list?currentPage="+i+"&count="+count+"'>" + i + "</a> ");

            }
        }
        if(needNext) {
            sb.append("<a href='/owner/community/list?currentPage="+(endNavi+1)+"&count="+count+"'>></a> ");
        }
        return sb.toString();
    }

    //게시글 검색
    public List<BoardDTO> selectByType(Integer start,Integer end,String search_type,String keyword) throws Exception{
        return mapper.selectByType(start,end,search_type,keyword);
    }

}
