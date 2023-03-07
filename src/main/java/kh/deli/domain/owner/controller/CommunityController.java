package kh.deli.domain.owner.controller;

import kh.deli.domain.owner.dto.BoardDTO;
import kh.deli.domain.owner.service.OwnerCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/owner/community")
public class CommunityController {

    @Autowired
    private OwnerCommunityService service;
    private HttpSession session;


    //글 전체 목록 출력
    @RequestMapping("/list")
    public String home(Model model, @RequestParam("currentPage") Integer currentPage, @RequestParam("count") Integer count,
                       @RequestParam("search_type") String search_type, @RequestParam("keyword") String keyword) throws Exception {
//        *세션정보를 못가져옴
//        String loginEmail=(String)session.getAttribute("loginEmail");
//        model.addAttribute("loginEmail",loginEmail);

        if (currentPage == null) {
            currentPage = 1;
        }

        //게시글 개수 기본 10
        if (count == null) {
            count = 10;
        }

        Integer boardListCount = service.countPost(); // 전체 글 개수
        Integer start = currentPage * count - 9; //시작 글 번호
        Integer end = currentPage * count; // 끝 글 번호
        List<BoardDTO> list = service.selectByType(start, end, search_type, keyword); // 글 목록 보여주기
        String paging = service.getBoardPageNavi(currentPage, count); // 하단 페이징 처리
        model.addAttribute("board_list", list);
        model.addAttribute("paging", paging);

        return "/owner/communityMain";
    }

    //글 작성폼으로 이동
    @RequestMapping("/writeForm")
    public String toWriteForm() throws Exception {
        return "/owner/writeForm";
    }

    //게시글 상세페이지로 이동
    @RequestMapping("/detailBoard")
    public String toDetailBoard(@RequestParam("b_seq") Integer b_seq, Model model) throws Exception {
        service.count(b_seq); //조회수 증가
        BoardDTO dto = service.detailBoard(b_seq);
        String nickname = service.getNickname(b_seq); //작성자와 로그인 세션 동일해야 수정 삭제 띄우려고
        model.addAttribute("nickname", nickname);
        model.addAttribute("detailBoard", dto);
        return "/owner/detailBoard";
    }

    //insert
    @RequestMapping("/insert")
    public String insert(@RequestParam("boardDTO") BoardDTO boardDTO) throws Exception {
        service.insert(boardDTO);
        return "redirect:/owner/community/list?page=1&PerPageNum=5";
    }

    //delete
    @RequestMapping("/delete")
    public String delete(@RequestParam("b_seq") Integer b_seq) throws Exception {
        service.delete(b_seq);
        return "redirect:/owner/community/list?page=1&PerPageNum=5";
    }

    //update
    @RequestMapping("/update")
    public String update(@RequestParam("b_seq") Integer b_seq, @RequestParam("b_title") String b_title,@RequestParam("b_contents") String b_contents) throws Exception {
        service.update(b_seq, b_title, b_contents);
        return "redirect:/owner/community/list?page=1&PerPageNum=5";
    }

    //search
//    @RequestMapping("/search")
//    public String search(Model model, Integer currentPage, @RequestParam("search_type") String search_type, @RequestParam("keyword") String keyword, Integer count) throws Exception {
//        System.out.println("currentPage : " + currentPage);
//        System.out.println("search_type : " + search_type);
//        System.out.println("keyword : " + keyword);
//        System.out.println("count : " + count);
//
//        Integer start = currentPage * count - 9; //시작 글 번호
//        Integer end = currentPage * count; // 끝 글 번호
//        List<BoardDTO> list = service.selectByType(start, end, search_type, keyword);
//        String paging = service.getBoardPageNavi(currentPage, count);
//        model.addAttribute("boardSearchList", list);
//        model.addAttribute("paging", paging);
//        return "/owner/boardListBySearchOption";
//    }

}
