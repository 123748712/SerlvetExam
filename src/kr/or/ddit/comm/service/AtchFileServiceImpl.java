package kr.or.ddit.comm.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService {
	private static final String UPLOAD_DIR = "upload_files";
	private static IAtchFileService atchFileService;
	private IAtchFileDao atchFileDao;
	
	private AtchFileServiceImpl() {
		atchFileDao = AtchFileDaoImpl.getInstance(); 
	}
	
	public static IAtchFileService getInstance() {
		if(atchFileService == null) {
			atchFileService = new AtchFileServiceImpl();
		}
		return atchFileService;
	}

	@Override
	public AtchFileVO saveAtchFileList(HttpServletRequest req) throws Exception {
		
		String uploadPath = "d:/D_Other" + File.separator + UPLOAD_DIR;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		try {
			String fileName = "";
			boolean isFirstFile = true; // 첫번째 파일 여부
			for(Part part : req.getParts()) {
				fileName = getFileName(part);
				if(fileName != null && !fileName.equals("")) {
					if(isFirstFile) { // 첫번째 파일정보인지 체크
						isFirstFile = false;
						
						// 파일기본정보 저장하기
						atchFileVO = new AtchFileVO();
						
						atchFileDao.insertAtchFile(atchFileVO); // selectKey로 저장된 atchFileId 값을 VO에 담기 위함
					}
					
					String orignFileName = fileName; // 원본파일명
					long fileSize = part.getSize(); // 파일사이즈 | part가 현재 갖고 있는 파일의 size를 가져오는 메소드
					String saveFileName = ""; // 저장파일명
					String saveFilePath= ""; // 저장파일경로
					
					File storeFile = null; // 저장 파일 객체
					
					do {
						saveFileName = UUID.randomUUID().toString().replace("-", "");
						saveFilePath = uploadPath + File.separator + saveFileName;
						storeFile = new File(saveFilePath);
						
					} while(storeFile.exists()); // 파일명 중복이 되면 반복을 빠져나온다. (randomUUID이기 때문에 중복 확률 낮음)
					
					// 확장자명 추출
					String fileExtension = orignFileName.lastIndexOf(".") < 0 ? "" : orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
					
					part.write(saveFilePath); // 파일 업로드
					
					atchFileVO.setStreFileNm(saveFileName);
					atchFileVO.setFileSize(fileSize);
					atchFileVO.setOrignlFileNm(orignFileName);
					atchFileVO.setFileStreCours(saveFilePath);
					atchFileVO.setFileExtsn(fileExtension);
					
					atchFileDao.insertAtchFileDetail(atchFileVO);
					
					part.delete(); // 임시 업로드 파일 삭제
					
					System.out.println("업로드 완료");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return atchFileVO;
	}
	
	private String getFileName(Part part) {
		/*
		 * Content-Disposition 헤더
		 * 
		 * 2. multipart body를 위한 헤더정보로 사용되는 경우
		 * Content-Disposition : form-data
		 * Content-Disposition : form-data; name="feildName"
		 * Content-Disposition : form-data; name="feildName"; filename="abc.jpg"
		 */
		for (String content : part.getHeader("Content-Disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null;
		// return => null or filename
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		return atchFileDao.getAtchFileList(atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		return atchFileDao.getAtchFileDetail(atchFileVO);
	}
}
