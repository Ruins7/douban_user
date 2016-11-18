/**
 * 
 */
package com.dao.lsr.book.daoImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.lsr.book.dao.BookDao;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.entity.lsr.book.Book;
import com.entity.lsr.book.BookComment;
import com.entity.lsr.book.BookStation;
import com.entity.lsr.book.Book_Author;
import com.entity.lsr.book.Book_Pre_Read;
import com.entity.lsr.book.Book_Type;
import com.entity.lsr.book.Book_Wish_Buy;
import com.entity.lsr.user.User_Info;

/**
 * @ClassName: BookDaoImpl.java
 * @Description: TODO(用一句话描述该文件做什么)
 * 
 *               读书模块方法实现
 * 
 * @author Ruins7
 * @version V1.0
 * @Date 2014年10月8日 下午5:24:37
 */
public class BookDaoImpl implements BookDao {

	DaoOperationImpl doimpl = new DaoOperationImpl();

	@Override
	public Book_Pre_Read preRead(String book_id) {
		// 预读
		String sql = "select * from book_pre_read where book = ?";
		Object[] bookid = {book_id};
		Book_Pre_Read bpr =  doimpl.qrQueryForOne(sql, new Book_Pre_Read(), bookid);
		return bpr;
	}

	@Override
	public int[] addBook(List<Book> book_list) {
		// 管理员批量添加图书
		String sql = "INSERT INTO `book` ("
				+ "`book_name`, `book_imgs`, `book_author`, "
				+ "`book_publisher`, `sub_title`, `translator`,"
				+ " `publish_date`, `page_num`, `simple_desc`, `version`,"
				+ " `book_type`) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		// 将list换为Object[][]
		Object[][] books = new Object[book_list.size()][11];
		for (int i = 0; i < book_list.size(); i++) {
			Book book = book_list.get(i);
			books[i][0] = book.getBook_name();
			books[i][1] = book.getImgs();
			books[i][2] = book.getBook_author();
			books[i][3] = book.getBook_publisher();
			books[i][4] = book.getSub_title();
			books[i][5] = book.getTranslator();
			books[i][6] = book.getPublish_date();
			books[i][7] = book.getPage_num();
			books[i][8] = book.getSimple_desc();
			books[i][9] = book.getVersion();
			books[i][10] = book.getType();
		}
		int[] success_num = doimpl.qrInsertBatch(sql, books);
		return success_num;
	}

	@Override
	public Map<Integer, Integer> delBook(List<String> book_ids) {
		// 管理员批量删除书籍
		String sql = "delete from book where book_id = ?";
		Object[] bookid = {};
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < bookid.length; i++) {
			Object[] id = { bookid[i] };
			int resultNum = doimpl.qrUpdate(sql, id);
			map.put(i, resultNum);
		}
		return map;
	}

	@Override
	public Book showOneBook(String book_id) {
		// 查看某一本书的具体信息
		String sql = "select * from book where book_id = ?";
		Object[] params = { book_id };
		Book book = new Book();
		book = doimpl.qrQueryForOne(sql, book, params);
		return book;
	}

	@Override
	public List<Book> showBooksByAuthor(String author_id) {
		// 根据作者查找书
		String sql = "select * from book where book_author = ?";
		Object[] params = { author_id };
		List<Book> list = doimpl.qrQueryForList(sql, new Book(), params);
		return list;
	}

	@Override
	public List<Book> showBooksByType(String type_id) {
		// 根据类型查找书
		String sql = "select * from book where type = ? order by publish_date DESC";
		Object[] params = { type_id };
		List<Book> list = doimpl.qrQueryForList(sql, new Book(), params);
		return list;
	}

	@Override
	public int addBookToWannaBuy(Book_Wish_Buy bwb) {
		// 添加到购书心愿单
		String sql = "insert into book_wish_buy(user,book,time) values(?,?,?)";
		Object[] params = {bwb.getUser(),bwb.getBook(),bwb.getTime()};
		int success_num = doimpl.qrUpdate(sql, params);
		return success_num;
	}

	@Override
	public int delBookFromWannaBuy(Book_Wish_Buy bwb) {
		// 删除购书心愿
		String sql = "delete from book_wish_buy where book = ? and user = ?";
		Object[] params = {bwb.getBook(),bwb.getUser()}; 
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}

	@Override
	public int modifyBook(Book book) {
		// 修改图书信息
		String sql = "update book set book_name = ? , book_imgs = ? , book_author = ? , book_publisher = ? , sub_title = ? , translator = ? , publish_date = ? , page_num = ? , simpl_desc = ? , version = ? , type = ? where book_id = ?";
		Object[] bookid = {book.getBook_name(),book.getImgs(),
				           book.getBook_author(),book.getBook_publisher(),
				           book.getSub_title(),book.getTranslator(),
				           book.getPublish_date(),book.getPage_num(),
				           book.getSimple_desc(),book.getVersion(),
				           book.getType(),book.getBook_id()};
		int result = doimpl.qrUpdate(sql, bookid);
		return result;
	}

	@Override
	public List<Book_Wish_Buy> showMyWannaBuy(String user_id) {
		// 查询用户的购书心愿单
		String sql = "select * from book_wish_buy where user = ?";
		Object[] params = {user_id};
		List<Book_Wish_Buy> list = doimpl.qrQueryForList(sql, new Book_Wish_Buy(), params);
		return list;
	}

	@Override
	public List<Book> showNewBook() {
		// 新书速递
		String sql = "select * from book order by publish_date DESC";
		List<Book> list = doimpl.qrQueryForList(sql, Book.class);
		return list;
	}

	@Override
	public List<Book_Type> showAllBookType() {
		// 查询所有书籍类型
		String sql = "select * from book_type";
		List<Book_Type> list = doimpl.qrQueryForList(sql, Book_Type.class);
		return list;
	}
	
	@Override
	public List<Book_Author> showAllBookAuthor() {
		// 查询所有书籍作者
		String sql = "select * from Book_Author";
		List<Book_Author> list = doimpl.qrQueryForList(sql, Book_Author.class);
		return list;
	}

	@Override
	public List<Object[]> showAllTypeRank() {
		// 查询各类型书籍排行榜
		String sql = "select book_id,(select type from book where book_id = bookstation.book_id),avg(score) from bookstation group by book_id order by avg(score) DESC";
		List<Object[]> list = doimpl.qrQueryForResultSet(sql, null);
		return list;
	}

	@Override
	public List<BookComment> showAllBookComment() {
		// 查询书评
		String sql = "select * from bookcomment order by date DESC";
		List<BookComment> list = doimpl.qrQueryForList(sql, BookComment.class);		
		return list;
	}
	
	@Override
	public Double showOneBookScore(String book_id) {
		// 查询某本书的评分
		String sql = "select avg(score) from bookstation where book_id = ?";
		Object[] params = {book_id};
		List<Object[]> list = doimpl.qrQueryForResultSet(sql, params);
		return (Double) list.get(0)[0];
	}

	@Override
	public List<BookStation> showOneBookStation(String book_id) {
		// 查询某书的短评
		String sql = "select * from bookstation where book_id = ? order by date DESC";
		Object[] params = {book_id};
		List<BookStation> list = doimpl.qrQueryForList(sql, new BookStation(), params);	
		return list;
	}
	
	@Override
	public List<BookComment> showOneBookComment(String book_id) {
		// 查询某书的书评
		String sql = "select * from BookComment where book_id = ? order by date DESC";
		Object[] params = {book_id};
		List<BookComment> list = doimpl.qrQueryForList(sql, new BookComment(), params);	
		return list;
	}
	
	@Override
	public int writeBookComment(BookComment book_comment) {
		// 写书评
		String sql = "insert into BookComment(book_id,user_id,date,masg) values(?,?,?,?)";
		Object[] params = {book_comment.getBook_id(),book_comment.getUser_id(),book_comment.getDate(),book_comment.getMasg()};
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}
	
	@Override
	public BookStation showBookStationOfAUserToABook(String bid, String uid) {
		// 查询当前用户是否对某本书打分以及选择阅读状态
		String sql = "select * from bookstation where book_id = ? and user_id = ?";
		Object[] params = {bid,uid};
		BookStation re = doimpl.qrQueryForOne(sql, new BookStation(), params);
		return re;
	
	}
	
	@Override
	public int giveBookStation(BookStation bs) {
		// 给某书打分,选择阅读状态，写短评
		String sql = "insert into bookstation(book_id,user_id,comment,station,score,date) values(?,?,?,?,?,?)";
		Object[] params = {bs.getBook_id(),bs.getUser_id(),bs.getComment(),bs.getStation(),bs.getScore(),bs.getDate()};
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}
	
	@Override
	public List<Book_Wish_Buy> showWhetherAddToWish(User_Info user ,Book book) {
		// 查询某个用户是否将某本书加入心愿购书单
		String sql = "select * from book_wish_buy where user = ? and book = ?" ;
		Object[] params = {user.getUser_id(),book.getBook_id()};
		List<Book_Wish_Buy> result = doimpl.qrQueryForList(sql, new Book_Wish_Buy(), params);
		return result;
	
	}
	
	@Override
	public List<BookStation> showAllBookStationToOneBook(String bookid) {
		// 查询看某书的所有用户（三种阅读状态）
		String sql = "select * from BookStation where book_id = ?" ;
		Object[] params = {bookid};
		List<BookStation> result = doimpl.qrQueryForList(sql, new BookStation(), params);
		return result;
		
	}
	
	@Override
	public List<BookStation> showUserReadAllbook(String user_id) {
		// 查询某用户读过的所有书
		String sql = "select * from BookStation where user_id = ? and station = '已读'" ;
		Object[] params = {user_id};
		List<BookStation> result = doimpl.qrQueryForList(sql, new BookStation(), params);
		return result;
		
	}
	@Override
	public List<BookStation> showUserWantReadAllbook(String user_id) {
		// 查询某用户想读的所有书
		String sql = "select * from BookStation where user_id = ? and station = '想读'" ;
		Object[] params = {user_id};
		List<BookStation> result = doimpl.qrQueryForList(sql, new BookStation(), params);
		return result;
		
	}
	@Override
	public List<BookStation> showUserReadingAllbook(String user_id) {
		// 查询某用户在读的所有书
		String sql = "select * from BookStation where user_id = ? and station = '在读'" ;
		Object[] params = {user_id};
		List<BookStation> result = doimpl.qrQueryForList(sql, new BookStation(), params);
		return result;
		
	}
	
	
}
