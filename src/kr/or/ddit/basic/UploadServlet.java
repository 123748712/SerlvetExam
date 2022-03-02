package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// 자카르타 프로젝트의 fileupload모듈을 이용한 파일 업로드 예제
@WebServlet("/uploadTest.do")
public class UploadServlet extends HttpServlet {
	private static final String UPLOAD_DIR = "upload_files";
	private static final int MEMORY_THREASHOLD = 1024 * 1024 * 3; // 메모리 임계 크기 (3MB)
	// 메모리 임계크기가 넘어가면 레파지토리에 임시파일로 저장됨
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40; // 파일 1개당 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 요청 파일 최대 크기

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) { // isMultipartContent() => 전송된 데이터가 multipart/Form-data 형식 여부 판단
			
			// 폼필드 저장용
			Map<String, String> formMap = new HashMap<String, String>();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 파일이나 메모리를 보관하는 FileItem의 Factory 생성

			factory.setSizeThreshold(MEMORY_THREASHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			// 업로드 요청을 처리하는 ServletFileUpload 생성

			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// 웹 어플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
			String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;
			// separator => 운영체제에 따라 구분자 설정

			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				List<FileItem> formItems = upload.parseRequest(req); // 넘어온 데이터를 처리해서 List 타입으로 return

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) { // 파일인 경우
							String fileName = item.getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);

							item.write(storeFile); // 업로드 파일 저장
							System.out.println("업로드 완료 => " + "파일명 : " + fileName);
						} else { // 폼 데이터인 경우 폼 필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환해 주어야 한다.
							formMap.put(item.getFieldName(), item.getString("UTF-8"));

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setContentType("text/plan");
			PrintWriter out = resp.getWriter();
			
			for(Entry<String, String> entry : formMap.entrySet()) {
				out.println("파라미터명 : " + entry.getKey());
				out.println("파라미터값 : " + entry.getValue());
			}
			out.println("업로드 완료됨.");
		}
	}
}