package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {

	private SqlMapClient smc;
	private static IMemberDao memDao;

	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMemberDao getInstance() {
		if (memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;

		try {
			cnt = smc.update("member.insertMember", mv);
		} catch (SQLException e) {
			throw new RuntimeException("회원목록 등록 중 예외 발생.", e);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean chk = false;

		int cnt = 0;

		try {

			cnt = (int) smc.queryForObject("member.checkMember", memId);

			if (cnt > 0) {
				chk = true;
			}

		} catch (SQLException ex) {
			throw new RuntimeException("회원존재여부 체크중 예외발생", ex);
		}

		return chk; // 중복이 있으면 true, 없으면 false
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<>();

		try {

			memList = smc.queryForList("member.getMemberAll");

		} catch (SQLException e) {
			throw new RuntimeException("전체회원목록 조회중 예외발생", e);
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt = 0;

		try {

			cnt = smc.update("member.updateMember", mv);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 수정중 예외발생.", e);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		try {

			cnt = smc.delete("member.deleteMember", memId);

		} catch (SQLException e) {
			throw new RuntimeException("회원정보 삭제중 예외발생.", e);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<>();

		try {

			memList = smc.queryForList("member.searchMember", mv);

		} catch (
		SQLException e) {
			throw new RuntimeException("회원정보 검색 중 예외 발생.", e); // SQLException이 발생되면 RuntimeException을 던진다.
		}
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv;

		try {
			mv = (MemberVO) smc.queryForObject("member.getMember", memId);
		} catch (SQLException ex) {
			throw new RuntimeException("회원정보 조회중 예외발생", ex);
		}

		return mv;
	}
}
