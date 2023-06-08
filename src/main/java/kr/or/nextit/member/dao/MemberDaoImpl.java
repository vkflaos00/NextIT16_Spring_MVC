package kr.or.nextit.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

@Repository
public class MemberDaoImpl implements IMemberDao{

	@Inject
	private SqlSession sqlSession;
	
	
	private String namespace = "kr.or.nextit.member.dao.IMemberDao";
	
	@Override
	public MemberVO getMember(String memId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne( namespace+".getMember", memId);
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+".insertMember", member);
	}

	@Override
	public int insertUserRole(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace+".insertUserRole", member);
	}

	@Override
	public MemberVO loginCheck(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".loginCheck", member);
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace+".updateMember", member);
	}

	@Override
	public int deleteMember(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace+".deleteMember", member);
	}

	@Override
	public List<UserRoleVO> getUserRole(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getUserRole", member);
	}

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getMemberList", searchVO);
	}

	@Override
	public int getTotalRowCount(MemberSearchVO searchVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getTotalRowCount", searchVO);
	}

	@Override
	public List<RoleInfoVO> getRoleInfo() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getRoleInfo");
	}

	@Override
	public void deleteUserRole(String memId) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".deleteUserRole", memId);
	}

	@Override
	public void insertMultiRole(String memId, String role) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("memId", memId);
		param.put("role", role);
		
		sqlSession.insert(namespace+".insertMultiRole", param);
	}

}
