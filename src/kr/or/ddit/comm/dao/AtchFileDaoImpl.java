package kr.or.ddit.comm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AtchFileDaoImpl implements IAtchFileDao {
	private SqlMapClient smc;
	private static IAtchFileDao atchFileDao;

	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IAtchFileDao getInstance() {
		if (atchFileDao == null) {
			atchFileDao = new AtchFileDaoImpl();
		}
		return atchFileDao;
	}

	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) {
		int cnt = 0;

		try {
			Object obj = smc.insert("atchFile.insertAtchFile", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertAtchFileDetail(AtchFileVO atchFileVO) {
		int cnt = 0;

		try {
			cnt = smc.update("atchFile.insertAtchFileDetail", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {

		List<AtchFileVO> atchFileList = new ArrayList<AtchFileVO>();

		try {
			atchFileList = smc.queryForList("atchFile.getAtchFileList", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		AtchFileVO fileVO = null;
		try {
			fileVO = (AtchFileVO) smc.queryForObject("atchFile.getAtchFileDetail", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileVO;
	}
}
