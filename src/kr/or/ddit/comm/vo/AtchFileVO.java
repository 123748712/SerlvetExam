package kr.or.ddit.comm.vo;

import java.util.Date;

/*
=== 첨부파일 테이블용 시퀀스 생성 ===
CREATE SEQUENCE ATCH_FILE_SEQ;

=== 첨부파일 관련 DDL ===
  CREATE TABLE ATCH_FILE
   (ATCH_FILE_ID NUMBER NOT NULL ENABLE,
	CREAT_DT DATE DEFAULT SYSDATE NOT NULL ENABLE,
	USE_AT CHAR(1 BYTE) DEFAULT 'Y',
	 CONSTRAINT ATCH_FILE_PK PRIMARY KEY (ATCH_FILE_ID)
    );

   COMMENT ON COLUMN ATCH_FILE.ATCH_FILE_ID IS '첨부파일ID';
   COMMENT ON COLUMN ATCH_FILE.CREAT_DT IS '생성일시';
   COMMENT ON COLUMN ATCH_FILE.USE_AT IS '사용여부';

CREATE TABLE ATCH_FILE_DETAIL
   (ATCH_FILE_ID NUMBER NOT NULL ENABLE,
	FILE_SN NUMBER(3) NOT NULL ENABLE,
	FILE_STRE_COURS VARCHAR2(2000 BYTE) NOT NULL ENABLE,
	STRE_FILE_NM VARCHAR2(255 BYTE) NOT NULL ENABLE,
	ORIGNL_FILE_NM VARCHAR2(255 BYTE),
	FILE_EXTSN VARCHAR2(20 BYTE) NOT NULL ENABLE,
	FILE_CN CLOB,
	FILE_SIZE NUMBER(8,0),
	 CONSTRAINT ATCH_FILE_DETAIL_PK PRIMARY KEY (ATCH_FILE_ID, FILE_SN)
   );

   COMMENT ON COLUMN ATCH_FILE_DETAIL.ATCH_FILE_ID IS '첨부파일ID';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.FILE_SN IS '파일순번';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.FILE_STRE_COURS IS '파일저장경로';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.STRE_FILE_NM IS '저장파일명';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.ORIGNL_FILE_NM IS '원본파일명';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.FILE_EXTSN IS '파일확장자';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.FILE_CN IS '파일내용';
   COMMENT ON COLUMN ATCH_FILE_DETAIL.FILE_SIZE IS '파일크기';
 */
public class AtchFileVO {

	private long atchFileId = -1;	// 첨부파일 ID
	private int fileSn = 1;			// 파일 순번
	private String fileStreCours;	// 파일저장경로
	private String streFileNm;		// 저장파일명
	private String orignlFileNm;	// 원본파일명
	private String fileExtsn;		// 파일확장자
	private String fileCn;			// 파일 내용
	private long fileSize = 0;		// 파일크기
	private Date creatDt;			// 생성일자

	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	public int getFileSn() {
		return fileSn;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(Date creatDt) {
		this.creatDt = creatDt;
	}
}
