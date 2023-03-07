package kh.deli.domain.owner.mapper;

import kh.deli.domain.owner.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OwnerCommunityMapper {

    void insert(BoardDTO boardDTO); //글 작성
    List<BoardDTO> select(@Param("start") Integer start,
                            @Param("end") Integer end);//글 전체 목록 출력

    void count(@Param("b_seq") Integer b_seq); //조회수 증가
    BoardDTO detailBoard(@Param("b_seq") Integer b_seq); //글상세페이지로이동
    void delete(@Param("b_seq") Integer b_seq);//글 삭제
    String getNickname(@Param("b_seq") Integer b_seq); // 작성자 닉네임 가져오기
    void update(@Param("b_seq") Integer b_seq
            ,@Param("b_title") String b_title
            ,@Param("b_contents") String b_contents); //글 수정하기

    int countPost();//전체 글 개수
    List<BoardDTO> selectByType( @Param("start") Integer start,
                                @Param("end") Integer end,
                                @Param("search_type") String search_type,
                                @Param("keyword") String keyword
                                );   // 게시글 검색
}
