package kr.or.nextit.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

@Repository
public class FreeBoardDaoImpl implements IFreeBoardDao{

	private String namespace = "kr.or.nextit.free.dao.IFreeBoardDao";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String getFreeBoardKey() {
		
		return sqlSession.selectOne(namespace+".getFreeBoardKey");
	}

	@Override
	public int insertBoard(FreeBoardVO freeBoard) {
		return sqlSession.insert(namespace+".insertBoard", freeBoard);
	}

	@Override
	public int getTotalRowCount(FreeBoardSearchVO searchVO) {
		return sqlSession.selectOne(namespace+".getTotalRowCount", searchVO);
	}

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		return sqlSession.selectList(namespace+".getBoardList", searchVO);
	}

	@Override
	public FreeBoardVO getBoard(String boNo) {
		return sqlSession.selectOne(namespace+".getBoard", boNo);
	}

	@Override
	public int increaseHit(String boNo) {
		return sqlSession.update(namespace+".increaseHit", boNo);
	}

	@Override
	public int updateBoard(FreeBoardVO freeBoard) {
		return sqlSession.update(namespace+".updateBoard", freeBoard);
	}

	@Override
	public int deleteBoard(FreeBoardVO freeBoard) {
		return sqlSession.delete(namespace+".deleteBoard", freeBoard);
	}

	@Override
	public int checkAdmin(FreeBoardVO freeBoard) {
		return sqlSession.selectOne(namespace+".checkAdmin", freeBoard);
	}

}
