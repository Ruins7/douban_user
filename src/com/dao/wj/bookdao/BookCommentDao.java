package com.dao.wj.bookdao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.lsr.book.BookComment;

/**
 * bookCommentDao 
 * @author 汪进
 *
 */
public class BookCommentDao extends GenericDaoImpl<BookComment, Integer> {
	//获取BookComment
	@Override
	public BookComment findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}
    //根据实体id，获取BookComment集合
	@Override
	public List<BookComment> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}
    //获取所有bookComment
	@Override
	public List<BookComment> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
   //添加BookComment
	@Override
	public void save(BookComment entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
    //更新BookComment
	@Override
	public void update(BookComment entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	//删除BookComment
	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(BookComment entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
	

}
