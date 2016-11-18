package com.services.lsr.bookservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.lsr.book.daoImpl.BookDaoImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.dao.wj.bookdao.BookRankingDao;
import com.entity.lsr.book.Book;
import com.entity.lsr.book.BookComment;
import com.entity.lsr.book.BookEntityForRank;
import com.entity.lsr.book.Book_Author;
import com.entity.lsr.book.Book_Publisher;
import com.entity.lsr.book.Book_Type;
import com.entity.lsr.book.Book_Wish_Buy;
import com.entity.lsr.user.User_Info;


/**
 *图书servlet
 * @Ruins7 
 *
 */
@WebServlet(name="BookShowServlet",urlPatterns="/book/bookinfo")
public class BookShowServlet extends HttpServlet {
	
	BookRankingDao bookRankingDao = new BookRankingDao();
	BookDaoImpl bdi = new BookDaoImpl();
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	Book book=new Book();
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("getRankingByCountEntity".equals(method)){
			getRankingByCountEntitys(request,response);
		}else if("getRankingByScoreEntitys".equals(method)){
			getRankingByScoreEntitys(request,response);
		}else if("searchBookByTimeDecs".equals(method)){
			searchBookByTimeDecs(request,response);
		}else if("searchBookType".equals(method)){
			searchBookType(request,response);
		}else if("searchBookRankByType".equals(method)){
			searchBookRankByType(request,response);
		}else if("searchUserWishToBuy".equals(method)){
			searchUserWishToBuy(request,response);
		}else if("searchGreatBookComment".equals(method)){
			searchGreatBookComment(request,response);
		}
	}
	public void getRankingByCountEntitys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bookRankingDao.getRankingByCountEntity(book);
		bookRankingDao.getRankingByCountEntity(book);
           
	}
	public void getRankingByScoreEntitys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bookRankingDao.getRankingByCountEntity(book);
           
	}
	
	// 新书速递
	public void searchBookByTimeDecs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		List<Book> list = bdi.showNewBook();
		if(list != null){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Book book = (Book) iterator.next();
				//查询作者
				Book_Author ba = new Book_Author();
				ba = psifImpl.showOneByR(book.getBook_author()+"", ba);
				book.setB_author(ba);
				//查询出版社
				Book_Publisher bp = new Book_Publisher();
				bp = psifImpl.showOneByR(book.getBook_publisher()+"", bp);
				book.setB_publisher(bp);
				//查询翻译者
				if(book.getTranslator() != 0){
					Book_Author tran = new Book_Author();
					tran = psifImpl.showOneByR(book.getTranslator()+"", tran);
					book.setB_translator(tran);
				}
				//查询书籍类型
				Book_Type bt = new Book_Type();
				bt = psifImpl.showOneByR(book.getType()+"", bt);
				book.setB_type(bt);	
			}
			if(list.size() > 20){
				request.setAttribute("newbook", list.subList(0, 20));//页面显示20本新书
			}else{
				request.setAttribute("newbook", list);
			}
		}else{
			request.setAttribute("newbook", null);
		}
		request.getRequestDispatcher("./bookinfo?method=searchBookType").forward(request, response);
	}
	// 图书类别查询
	public void searchBookType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book_Type> list = bdi.showAllBookType();
		request.setAttribute("book_type", list);
		request.getRequestDispatcher("./bookinfo?method=searchBookRankByType").forward(request, response);
	}
	// 按图书类别排行榜
	public void searchBookRankByType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Object[]> list = bdi.showAllTypeRank();
		//向前端传输的数据格式
		List<BookEntityForRank> rankedlist = new ArrayList<BookEntityForRank>();
		if(list != null){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				BookEntityForRank befr = new BookEntityForRank();
				//查询书籍
				Book book = new Book();				
				book = psifImpl.showOneByR((int)objects[0]+"", book);
				//查询作者
				Book_Author ba = new Book_Author();
				ba = psifImpl.showOneByR(book.getBook_author()+"", ba);
				book.setB_author(ba);
				befr.setBook(book);
				//查询类别
				Book_Type bt = new Book_Type();			 
				bt = psifImpl.showOneByR((long)objects[1]+"", bt);
				befr.setType(bt);
				//平均分 	 
				befr.setScore((Double)objects[2]);
				rankedlist.add(befr);
			}		 
			//查询所有类型 
			List<Book_Type> type_list = bdi.showAllBookType();
			request.setAttribute("rank_type", type_list);
			request.setAttribute("bookranklist", rankedlist);
		}else{
			request.setAttribute("bookranklist", null);
		}
		request.getRequestDispatcher("./bookinfo?method=searchUserWishToBuy").forward(request, response);
	}
	// 用户的购书心愿单
	public void searchUserWishToBuy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User_Info user = (User_Info) session.getAttribute("current_user");
		List<Book_Wish_Buy> list = bdi.showMyWannaBuy(user.getUser_id()+"");
		
		if(list != null){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Book_Wish_Buy book_Wish_Buy = (Book_Wish_Buy) iterator.next();
				//书籍
				Book book = new Book();
				book = psifImpl.showOneByR(book_Wish_Buy.getBook()+"", book);
        		//作者
				Book_Author ba = new Book_Author();
				ba = psifImpl.showOneByR(book.getBook_author()+"", ba);
				book.setB_author(ba);
				book_Wish_Buy.setB(book);
			}
			request.setAttribute("wannebuy", list);
		}else{
			request.setAttribute("wannebuy", null);
		}
		request.getRequestDispatcher("./bookinfo?method=searchGreatBookComment").forward(request, response);
	}
	// 近期精彩书评
	public void searchGreatBookComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BookComment> list = bdi.showAllBookComment();
		if(list != null){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				BookComment bookComment = (BookComment) iterator.next();
				User_Info user = new User_Info();
				user = psifImpl.showOneByR(bookComment.getUser_id()+"", user);
				bookComment.setUser(user);
				Book b = new Book();
				b = psifImpl.showOneByR(bookComment.getBook_id()+"", book);
				bookComment.setBook(b);
			}
			if(list.size() > 10){
				request.setAttribute("bookcomment", list.subList(0, 10));//截取近期的10条书评
			}else{
				request.setAttribute("bookcomment", list);
			}
		}else{
			request.setAttribute("bookcomment", null);
		}
		//下接系统推荐功能(未做)
		request.getRequestDispatcher("/book/douban_read.jsp").forward(request, response);
	}
}
