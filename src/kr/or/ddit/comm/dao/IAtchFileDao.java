package kr.or.ddit.comm.dao;

import java.util.List;

import kr.or.ddit.comm.vo.AtchFileVO;

public interface IAtchFileDao {
	/**
	 * 첨부파일 저장
	 * @param atchFileVO 첨부파일 정보
	 * @return 
	 */
	public int insertAtchFile(AtchFileVO atchFileVO);

	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVO 첨부파일정보
	 * @return
	 */
	public int insertAtchFileDetail(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일목록 조회하기
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO 검색할 첨부파일 정보(아이디 및 순번)
	 * @return 첨부파일 세부정보를 담은 AtchFileVO객체
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
}
