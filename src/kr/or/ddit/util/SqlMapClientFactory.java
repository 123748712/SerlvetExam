package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

// SqlMapClient 객체를 제공하는 메서드
public class SqlMapClientFactory {
	
	private static SqlMapClient smc;
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			Reader rd = null;
			
			try {
				// 1-1. xml문서 읽어오기
				// 설정파일의 인코딩 설정
				Charset charset = Charset.forName("UTF-8");
				Resources.setCharset(charset);
				rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성하기
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close(); // 스트림 객체 닫기
			} catch (IOException e) {
				throw new RuntimeException("SqlMapClient객체 생성 실패", e);
			} 
		}
		return smc;
	}
}
