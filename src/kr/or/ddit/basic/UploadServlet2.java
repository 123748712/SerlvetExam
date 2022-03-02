package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// 서블릿3부터 지원하는 Part 인터페이스를 이용한 파일 업르도 예제
@WebServlet("/uploadTest2.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet2 extends HttpServlet {
	private static final String UPLOAD_DIR = "upload_files";
	
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			String fileName = "";
			
			for(Part part : req.getParts()) {
				fileName = getFileName(part);
				
				if(fileName != null && !fileName.equals("")) {
					part.write(uploadPath + File.separator + fileName); // 파일 업로드
					System.out.println("파일 명 : " + fileName + " 업로드 완료.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("text/plan");
		resp.getWriter().println("sender : " + req.getParameter("sender"));
		resp.getWriter().println("업로드 완료.");
		
	}

	// Part객체 파싱하여 파일이름 추출하기
	// return => 파일명 : 파일명이 존재하지 않으면 null(폼필드)
	private String getFileName(Part part) {
		/* 
			Content-Disposition 헤더
			
			2. multipart body를 위한 헤더정보로 사용되는 경우
			Content-Disposition : form-data
			Content-Disposition : form-data; name="feildName"
			Content-Disposition : form-data; name="feildName"; filename="abc.jpg"
		 */
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
		}
		return null;
	}
}