package kr.or.nextit.code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.nextit.code.dao.ICommCodeDao;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.common.util.NextITSqlSessionFactory;


//@Component
//@Controller
//@Repository
@Service("codeService")
public class CommCodeServiceImpl implements ICommCodeService{

	//SqlSessionFactory sqlSessionFactory= NextITSqlSessionFactory.getSqlSessionFactory(); 
	
	@Autowired
	private ICommCodeDao codeDao;
	
	
	@Override
	public List<CodeVO> getCodeListByParent(String commParent) {
		// TODO Auto-generated method stub
		
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//ICommCodeDao codeDao = sqlSession.getMapper(ICommCodeDao.class);
		
		/*try {
			//List<CodeVO> codeList= commCodeDao.getCodeListByParent(commParent);
			List<CodeVO> codeList= codeDao.getCodeListByParent(commParent);
			return codeList;
		}finally {
			sqlSession.close();
		}*/
		
		List<CodeVO> codeList= codeDao.getCodeListByParent(commParent);
		return codeList;
		
	}

	
	
}
