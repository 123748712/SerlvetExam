package kr.or.ddit.comm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long fileId = req.getParameter("fileId") != null ? Long.parseLong(req.getParameter("fileId")) : -1;
		
		int fileSn = req.getParameter("fileSn") != null ? Integer.parseInt(req.getParameter("fileSn")) : 1;
		
		// 파일 정보 조회
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVO fileVO = new AtchFileVO();
		
		fileVO.setAtchFileId(fileId);
		fileVO.setFileSn(fileSn);
		
		fileVO = fileService.getAtchFileDetail(fileVO);
		
		
	//	 파일 다운로드 처리를 위한 응답헤더 정보 설정하기	
	/*
		Content-Disposition : inline (default) => 브라우저 내에서 다운로드 진행 (자동으로 웹에서 열어줌)
		Content-Disposition : attachment
		Content-Disposition : attachment; filename="abc.jpg" => 정해진 filename을 첨부파일로 다운로드함
	*/
		resp.setContentType("application/octet-stream");
		System.out.println("URL인코딩 처리된 파일명 => " + URLEncoder.encode(fileVO.getOrignlFileNm(), "UTF-8"));
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileVO.getOrignlFileNm(), "UTF-8") + "\"");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileVO.getFileStreCours()));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int data = -1;
		
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}