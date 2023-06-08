package kr.or.nextit.free.dao;

import java.util.List;

import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardDao {

	String getFreeBoardKey();

	int insertBoard(FreeBoardVO freeBoard);

	int getTotalRowCount(FreeBoardSearchVO searchVO);

	List<FreeBoardVO> getBaordList(FreeBoardSearchVO searchVO);

	FreeBoardVO getBoard(String boNo);

	int increaseHit(String boNo);

	int updateBoard(FreeBoardVO freeBoard);

	int deleteBoard(FreeBoardVO freeBoard);

	int checkAdmin(FreeBoardVO freeBoard);

}
