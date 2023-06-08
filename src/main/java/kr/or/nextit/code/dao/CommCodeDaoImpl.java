package kr.or.nextit.code.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.nextit.code.vo.CodeVO;

@Repository
public class CommCodeDaoImpl implements ICommCodeDao{

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<CodeVO> getCodeListByParent(String commParent) {
		// TODO Auto-generated method stub
		 
		return sqlSession.selectList("kr.or.nextit.code.dao.ICommCodeDao.getCodeListByParent", commParent);
	}

}
