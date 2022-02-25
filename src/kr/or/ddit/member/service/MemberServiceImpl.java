package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private static IMemberService memService;
	
	// 사용할 DAO객체를 위한 객체변수 선언
	private IMemberDao memDao;
	// 커넥션 객체를 위한 객체변수 선언
	private Connection conn;

	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}

	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	@Override
	public int insertMember(MemberVO mv) {

		int	cnt = memDao.insertMember(mv);
			
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		return memDao.checkMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
//		try {
//			conn = JDBCUtil2.getConnection();
//		} finally { // catch할게 없기 때문에 catch 문은 넣지 않았다.
//			JDBCUtil2.close(conn, null, null, null);
//		}
		return memDao.searchMember(mv);
	}
}