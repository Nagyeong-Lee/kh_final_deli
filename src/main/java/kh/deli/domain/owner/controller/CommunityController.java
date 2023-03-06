package kh.deli.domain.owner.controller;

import com.sun.mail.handlers.text_plain;
import kh.deli.domain.owner.dto.BoardDTO;
import kh.deli.domain.owner.dto.Criteria;
import kh.deli.domain.owner.dto.PagingDTO;
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
    public String home(Model model, @RequestParam("page") Integer page , @RequestParam("PerPageNum") Integer PerPageNum) throws Exception {
//        *세션정보를 못가져옴
//        String loginEmail=(String)session.getAttribute("loginEmail");
//        model.addAttribute("loginEmail",loginEmail);
        Criteria cri = new Criteria();
        if(page == null){  //페이징 처리 (처음 page=1)
            page = 1;
        }
        if(PerPageNum == null){
            PerPageNum = 5;
        }
        System.out.println(page);
        System.out.println(PerPageNum);
        cri.setPage(page);
        cri.setPerPageNum(PerPageNum);

        System.out.println("page "+ cri.getPage());
        System.out.println("PerPageNum "+ cri.getPerPageNum());

        Integer boardListCount = service.countPost(); // 전체 글 개수

        PagingDTO pagingDTO = new PagingDTO(); //페이징 처리
        pagingDTO.setCri(cri);
        pagingDTO.setTotalCount(boardListCount);

        Integer getPageStart=cri.getPageStart();
        Integer perPageNum=cri.getPerPageNum();
        System.out.println("getPageStart : " + getPageStart); //게시글 시작 번호
        System.out.println("perPageNum : " + perPageNum); //글 몇개 보여줄건지
        int pageStartToPerPageNum= getPageStart + perPageNum; //끝 범위
        System.out.println("getPageStart + perPageNum : " + pageStartToPerPageNum );
        List<BoardDTO> list = service.select(getPageStart,getPageStart+perPageNum);
        model.addAttribute("board_list", list);
        model.addAttribute("paging", pagingDTO);
        return "/owner/communityMain";
    }

    //글 작성폼으로 이동
    @RequestMapping("/writeForm")
    public String toWriteForm() throws Exception {
        return "/owner/writeForm";
    }

    //게시글 상세페이지로 이동
    @RequestMapping("/detailBoard")
    public String toDetailBoard(Integer b_seq, Model model) throws Exception {
        service.count(b_seq); //조회수 증가
        BoardDTO dto = service.detailBoard(b_seq);
        String nickname = service.getNickname(b_seq); //작성자와 로그인 세션 동일해야 수정 삭제 띄우려고
        model.addAttribute("nickname", nickname);
        model.addAttribute("detailBoard", dto);
        return "/owner/detailBoard";
    }

    //insert
    @RequestMapping("/insert")
    public String insert(BoardDTO boardDTO) throws Exception {
        service.insert(boardDTO);
        return "redirect:/owner/community/list?page=1&PerPageNum=5";
    }

    //delete
    @RequestMapping("/delete")
    public String delete(Integer b_seq) throws Exception {
        service.delete(b_seq);
        return "redirect:/owner/community/list?page=1&PerPageNum=5";
    }

    //update
    @RequestMapping("/update")
    public String update(Integer b_seq, String b_title, String b_contents) throws Exception {
        service.update(b_seq, b_title, b_contents);
        return "redirect:/owner/community/list?page=1&PerPageNum=5";
    }

}
