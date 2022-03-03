package kr.or.ddit.comm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.comm.vo.AtchFileVO;

public interface IAtchFileService {

	/**
	 * 첨부파일목록을 저장하기 위한 메서드
	 * @param req Part 정보를 꺼내기 위한 요청 객체
	 * @return AtchFileVO 저장한 첨부파일 아이디 정보
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFileList(HttpServletRequest req) throws Exception;
	
	/**
	 * 첨부파일 목록 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
}
