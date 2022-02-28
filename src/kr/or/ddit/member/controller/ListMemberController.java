package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

// xml을 대체할 수 있는 Annotation 방식
// @webServlet(url)
@WebServlet(value = "/member/list.do")
public class ListMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 2. 회원목록 조회
		List<MemberVO> memList = memService.getAllMemberList();
		req.setAttribute("memList", memList); //Request가 없어지지 않는 이상 받아온 회원목록은 남아있는다.
		
		// 3. view 페이지 전달
		RequestDispatcher rd = req.getRequestDispatcher("/view/member/list.jsp");
		rd.forward(req, resp); // response하지 않고 소스를 url에 전달한다.
		// service에서 가져온 데이터들을 view영역인 list.jsp가 최종 화면을 띄워준다.
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
