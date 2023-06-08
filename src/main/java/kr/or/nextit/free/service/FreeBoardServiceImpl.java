package kr.or.nextit.free.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.nextit.common.util.NextITSqlSessionFactory;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.free.dao.IFreeBoardDao;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements IFreeBoardService {

	SqlSessionFactory sqlSessionFactory = NextITSqlSessionFactory.getSqlSessionFactory();

	@Override
	public void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		System.out.println("FreeBoardServiceImpl registerBoard");
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		try {
			
			String boNo = freeDao.getFreeBoardKey();
			System.out.println("boNo: "+ boNo);
			freeBoard.setBoNo(boNo);
			
			int resultCnt = freeDao.insertBoard(freeBoard);
			
			if(resultCnt != 1) {
				throw new BizNotEffectedException();
			}
		}finally {
			sqlSession.close();
		}

		
		
	}


	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		//return null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		try {
			
			int totalRowCount = freeDao.getTotalRowCount(searchVO);
			
			searchVO.setTotalRowCount(totalRowCount);
			searchVO.pageSetting();
			System.out.println("searchVO.toString() "+ searchVO.toString());
			
			List<FreeBoardVO> freeBoardList = freeDao.getBaordList(searchVO);
			
			if(freeBoardList == null) {
				throw new BizNotEffectedException();
			}
			return freeBoardList;
		}finally {
			sqlSession.close();
		}
		
		
	}

	@Override
	public FreeBoardVO getBoard(String boNo) throws BizNotEffectedException {
		// TODO Auto-generated method stub
				
		System.out.println("getBoard_boNo: "+ boNo);

		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		try {
			FreeBoardVO freeBoard = freeDao.getBoard(boNo);
			
			if(freeBoard == null ) {
				throw new BizNotEffectedException();
			}
			return freeBoard;
			
		}finally {
			sqlSession.close();
		}
				
	}


	@Override
	public void increaseHit(String boNo) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		try {
			int cnt = freeDao.increaseHit(boNo);
			
			if( cnt != 1) {
				throw new BizNotEffectedException();
			}
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub

		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		
		try {
			FreeBoardVO  vo = freeDao.getBoard(freeBoard.getBoNo());
			if( vo==null) {
				throw new BizNotFoundException();
			}
			if(!vo.getBoPass().equals(freeBoard.getBoPass())) {
				throw new BizPasswordNotMatchedException();
			}

			int resultCnt = freeDao.updateBoard(freeBoard);
			if(resultCnt != 1 ){ 
				throw new BizNotEffectedException(); 
			}
		}finally {
			sqlSession.close();	
		}

	}

	
	@Override
	public void deleteBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		try {
			FreeBoardVO  vo = freeDao.getBoard(freeBoard.getBoNo());
			if( vo==null) {
				throw new BizNotFoundException();
			}
			
			if(!vo.getBoPass().equals(freeBoard.getBoPass())) { 
				throw new BizPasswordNotMatchedException(); 
			}
		
			int resultCnt = freeDao.deleteBoard(freeBoard); 
			if(resultCnt != 1 ){ 
				throw new BizNotEffectedException(); 
			}
		}finally {
			sqlSession.close();	
		}
	}


	@Override
	public void hideBoard(String memId, String boNo) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession(true);  
		IFreeBoardDao freeDao = sqlSession.getMapper(IFreeBoardDao.class);
		try {		
			FreeBoardVO freeBoard = new FreeBoardVO();
			freeBoard.setBoWriter(memId);
			freeBoard.setBoNo(boNo);
			
			int checkAdmin = freeDao.checkAdmin(freeBoard);
			if( checkAdmin != 1) {
				throw new BizNotEffectedException();
			}
			
			int resultCnt = freeDao.deleteBoard(freeBoard); 
			if(resultCnt != 1 ){ 
				throw new BizNotEffectedException(); 
			}
		}finally {
			sqlSession.close();	
		}
	}


	
	
}
