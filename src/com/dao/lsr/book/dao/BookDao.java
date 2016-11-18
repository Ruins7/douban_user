/**
 * 
 */
package com.dao.lsr.book.dao;

import java.util.List;
import java.util.Map;

import com.entity.lsr.book.Book;
import com.entity.lsr.book.BookComment;
import com.entity.lsr.book.BookStation;
import com.entity.lsr.book.Book_Author;
import com.entity.lsr.book.Book_Pre_Read;
import com.entity.lsr.book.Book_Type;
import com.entity.lsr.book.Book_Wish_Buy;
import com.entity.lsr.user.User_Info;

/**
 * @ClassName:     BookDao.java
 * @Description:   TODO(用一句话描述该文件做什么)
 * 
 *                 读书接口
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月8日 下午5:01:54 
 */
public interface BookDao {
	
	public Book_Pre_Read preRead(String book_id);
	public int[] addBook(List<Book> book_list);
	public Map<Integer, Integer> delBook(List<String> book_ids);
	public int modifyBook(Book book);
	public Book showOneBook(String book_id);
	public List<Book> showBooksByAuthor(String author_id);
	public List<Book> showBooksByType(String type_id);
	public int addBookToWannaBuy(Book_Wish_Buy bwb);
	public int delBookFromWannaBuy(Book_Wish_Buy bwb);
	public List<Book_Wish_Buy> showMyWannaBuy(String user_id);
	public List<Book> showNewBook();
	public List<Book_Type> showAllBookType();
	public List<Book_Author> showAllBookAuthor();
	public List<Object[]> showAllTypeRank();
	public List<BookComment> showAllBookComment();
	public Double showOneBookScore(String book_id);
	public List<BookStation> showOneBookStation(String book_id);
	public List<BookComment> showOneBookComment(String book_id);
	public int writeBookComment(BookComment book_comment);
	public BookStation showBookStationOfAUserToABook(String bid, String uid);
	public int giveBookStation(BookStation bs);
	public List<Book_Wish_Buy> showWhetherAddToWish(User_Info user ,Book book);
	public List<BookStation> showAllBookStationToOneBook(String bookid);
	public List<BookStation> showUserReadAllbook(String user_id);
	public List<BookStation> showUserWantReadAllbook(String user_id);
	public List<BookStation> showUserReadingAllbook(String user_id);
	 

}
