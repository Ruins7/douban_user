package com.dao.wj.bookdao;

import java.util.List;

import com.dao.wj.commondao.GenericCommonDaoImpl;
import com.entity.lsr.book.Book;

/**
 * 图书排行榜
 * @author 汪进
 *
 */
public class BookRankingDao extends GenericCommonDaoImpl<Book, Integer> {
    //图书按点击量(Count)排序
	@Override
	public List<Object[]> getRankingByCountEntity(Book entity) {
		// TODO Auto-generated method stub
		return super.getRankingByCountEntity(entity);
	}
	//图书按平均分(Avg(Score))排序
	@Override
	public List<Object[]> getRankingByScoreEntity(Book entity) {
		// TODO Auto-generated method stub
		return super.getRankingByScoreEntity(entity);
	}
		
}
