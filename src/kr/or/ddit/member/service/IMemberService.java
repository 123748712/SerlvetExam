package kr.or.ddit.member.service;

import java.sql.Connection;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

// Service객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
// 받아온 자료를 Controller에게 보내주는 역할을 수행함
public interface IMemberService {

	// MemberVO에 담겨진 자료를 db에 insert하는 메서드
	// mv => DB에 INSERT할 자료가 저장된 MemberVO객체
	// return DB작업이 성공하면 1이상의 값, 실패하면 0
	public int insertMember(MemberVO mv);

	// 주어진 회원 ID를 이용하여 존재여부를 알아내는 메서드
	// memId => 검색할 회원ID
	// return => 해당 회원 아이디가 있으면 true, 없으면 false
	public boolean checkMember(String memId);

	// DB의 mymember테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	// conn => 커넥션 객체
	// MemberVO객체를 담고 있는 List객체
	public List<MemberVO> getAllMemberList();

	// 하나의 MemberVO객체 데이터를 이용하여 DB를 update하는 메서드
	// mv => update할 회원정보가 들어있는 memberVO객체
	// return => 작업성공 : 1, 작업실패 : 0
	public int updateMember(MemberVO mv);

	// 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	// memId => 삭제할 회원ID
	// return => 작업성공 : 1, 작업실패 : 0
	public int deleteMember(String memId);

	//MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	// conn => 커넥션 객체
	// mv => 검색할 자료가 들어있는 MemberVO 객체
	// return => 검색 결과를 담은 List 객체
	public List<MemberVO> searchMember(MemberVO mv);
	
	// 주어진 회원 ID를 이용하여 회원정보를 알아내는 메서드
	// memId => 검색할 회원ID
	// return => 해당 회원 아이디가 있으면 해당 회원 정보
	public MemberVO getMember(String memId);
	
}