package com.services.lsr.bookservlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commontools.lsr.EntityAddClass;
import com.commontools.lsr.EntityClass;
import com.commontools.lsr.UserTools;
import com.dao.lsr.book.daoImpl.BookDaoImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.lsr.book.Book;
import com.entity.lsr.book.BookComment;
import com.entity.lsr.book.BookStation;
import com.entity.lsr.book.Book_Author;
import com.entity.lsr.book.Book_Pre_Read;
import com.entity.lsr.book.Book_Publisher;
import com.entity.lsr.book.Book_Type;
import com.entity.lsr.book.Book_Wish_Buy;
import com.entity.lsr.user.User_Info;

@WebServlet(name="BookServlet",urlPatterns="/book/bookmanage")
public class BookServlet extends HttpServlet {
	
	BookDaoImpl bookDaoImpl=new BookDaoImpl();
	UserTools ut = new UserTools();
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String method=request.getParameter("method");
        if("addBooks".equals(method)){
        	addBooks(request,response);
        }else if("delBooks".equals(method)){
        	delBooks(request,response);
        }else if("showOnebook".equals(method)){
        	showOnebook(request,response);
        }else if("showbooksByAuthor".equals(method)){
        	showbooksByAuthor(request,response);
        }else if("showBooksByType".equals(method)){
        	showBooksByType(request,response);
        }else if("addBookToWannaBuy".equals(method)){
        	try {
				addBookToWannaBuy(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if("delBookFromWannaBuy".equals(method)){
        	delBookFromWannaBuy(request,response);
        }else if("modifyBook".equals(method)){
        	modifyBook(request,response);
        }else if("showMyWannaBuy".equals(method)){
        	showMyWannaBuy(request,response);
        }else if("writeBookComment".equals(method)){
        	try {
				writeBookComment(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if("giveBookStation".equals(method)){
        	try {
				giveBookStation(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
		//批量添加书 admin
		public void addBooks(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {	
			Book book = new Book();
			book = EntityAddClass.upLoadAddressAndReturnEntity(book, request);
			List<Book> book_list = null;
			int[] num=bookDaoImpl.addBook(book_list);
		}
		
		//批量删除 admin
		public void delBooks(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {	
			List<String> book_ids=new ArrayList<String>();
			String[] bookids=request.getParameterValues("book_id");
			for (String bookid : bookids) {
				book_ids.add(bookid);
			}
			Map<Integer, Integer> map=bookDaoImpl.delBook(book_ids);
		}

		//一本书的基本信息 user,admin
		public void showOnebook(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {	
			String book_id= request.getParameter("book_id");
			Book book = bookDaoImpl.showOneBook(book_id);
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
			//计算该书平均分
			book.setAvgscore(bookDaoImpl.showOneBookScore(book.getBook_id()+""));
			//查询当前用户对该书是否已经打分以及阅读状态
			HttpSession session = request.getSession();
			User_Info cuser = (User_Info) session.getAttribute("current_user");
			BookStation bookst = bookDaoImpl.showBookStationOfAUserToABook(book.getBook_id()+"", cuser.getUser_id()+"");
			if(bookst != null){
				book.setCurrent_user_book_station(bookst);
			}else{
				book.setCurrent_user_book_station(null);
			}
			//查询该书的预读
			Book_Pre_Read pre_read = bookDaoImpl.preRead(book.getBook_id()+"");
			book.setPre_read(pre_read);	
            //查询短评（打分时的评价）
			List<BookStation> bookstation = bookDaoImpl.showOneBookStation(book.getBook_id()+"");
			if(bookstation != null){
				for (Iterator iterator = bookstation.iterator(); iterator.hasNext();) {
					BookStation bookStation2 = (BookStation) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(bookStation2.getUser_id()+"", user);
					bookStation2.setUser(user);
				}
				book.setBookstation(bookstation);
			}else{
				book.setBookstation(null);
			}
			//查询书评
			List<BookComment> bookcomment = bookDaoImpl.showOneBookComment(book.getBook_id()+"");
			if(bookcomment != null){
				for (Iterator iterator = bookcomment.iterator(); iterator.hasNext();) {
					BookComment bookComment2 = (BookComment) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(bookComment2.getUser_id()+"", user);
					bookComment2.setUser(user);
				}
				book.setBookcoment(bookcomment);
			}else{
				book.setBookcoment(null);
			}
			//查询所有类型 
			List<Book_Type> list = bookDaoImpl.showAllBookType();
			request.setAttribute("book_type", list);
			request.setAttribute("book", book);
			//查询当前用户是否将该书加入心愿购书单
			List<Book_Wish_Buy> listss = bookDaoImpl.showWhetherAddToWish(cuser, book);
			if(listss != null){
				request.setAttribute("alreadywishbuy", "yes");
			}else{				 
				request.setAttribute("alreadywishbuy", "no");
			}
			//查询看该书的所有用户（三个阅读状态都算）
			List<BookStation> bslist = bookDaoImpl.showAllBookStationToOneBook(book_id);
			if(bslist != null){
				for (Iterator iterator = bslist.iterator(); iterator.hasNext();) {
					BookStation bookStation2 = (BookStation) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(bookStation2.getUser_id()+"", user);
					bookStation2.setUser(user);
				}
				request.setAttribute("peopleread", bslist);
			}else{
				request.setAttribute("peopleread", null);
			}
			 
			request.getRequestDispatcher("/book/douban_onebook.jsp").forward(request, response);
		}
	
	    //根据作者查书	user,admin
		public void showbooksByAuthor(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {	
			String author_id= request.getParameter("author_id");
			List<Book> books = bookDaoImpl.showBooksByAuthor(author_id);
			//查询当前作者
			Book_Author ba = new Book_Author();
			ba = psifImpl.showOneByR(author_id, ba);
			request.setAttribute("current_author", ba);
			if(books != null){
				for (Iterator iterator = books.iterator(); iterator.hasNext();) {
					Book book = (Book) iterator.next();			
					//作者
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
					//计算该书平均分
					book.setAvgscore(bookDaoImpl.showOneBookScore(book.getBook_id()+""));
				}
				request.setAttribute("books", books);
			}else{
				request.setAttribute("books", null);
			}
			
			//查询所有作者
			List<Book_Author> list = bookDaoImpl.showAllBookAuthor();
			request.setAttribute("book_author", list);
			request.getRequestDispatcher("/book/douban_bookbytype.jsp").forward(request, response);
		}

	    //根据类型查书	user,admin
		public void showBooksByType(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {	
			String type_id = request.getParameter("type_id");
			List<Book> booksByType = bookDaoImpl.showBooksByType(type_id);
			if(booksByType != null){
				for (Iterator iterator = booksByType.iterator(); iterator.hasNext();) {
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
					//计算该书平均分
					book.setAvgscore(bookDaoImpl.showOneBookScore(book.getBook_id()+""));
				}
				request.setAttribute("books", booksByType);
			}else{
				request.setAttribute("books", null);
			}
			//查询当前图书类型
			Book_Type bt = new Book_Type();
			bt = psifImpl.showOneByR(type_id, bt);
			request.setAttribute("current_type", bt);
			//查询所有书籍类型
			List<Book_Type> list = bookDaoImpl.showAllBookType();
			request.setAttribute("book_type", list);
			request.getRequestDispatcher("/book/douban_bookbytype.jsp").forward(request, response);
		}
		
		 //添加到购书心愿单	user
		public void addBookToWannaBuy(HttpServletRequest request, HttpServletResponse response)
				throws Exception {	
			HttpSession session = request.getSession();
			User_Info user = (User_Info) session.getAttribute("current_user");
			String book_wish_buy_book_id = request.getParameter("book_id");
			Book_Wish_Buy bwb = new Book_Wish_Buy();
			bwb.setBook(Integer.parseInt(book_wish_buy_book_id));
			bwb.setTime(ut.getCurrentAccurateTime());
			bwb.setUser(user.getUser_id());			 
			int result =bookDaoImpl.addBookToWannaBuy(bwb);
			PrintWriter out = response. getWriter();
	           if(result > 0) {
	               out.print(true );
	           } else {
	               out.print(false ); 
	           }
	           out.flush();
	           out.close();
		}
	
	   //删除购书心愿单	user
	     public void delBookFromWannaBuy(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {	
	    	 HttpSession session = request.getSession();
				User_Info user = (User_Info) session.getAttribute("current_user");
			    String bookid = request.getParameter("book_id");
			    Book_Wish_Buy bwb = new Book_Wish_Buy();
			    bwb.setBook(Integer.parseInt(bookid));
			    bwb.setUser(user.getUser_id());
		        int result = bookDaoImpl.delBookFromWannaBuy(bwb);
		         PrintWriter out = response. getWriter();
		           if(result > 0) {
		               out.print(true );
		           } else {
		               out.print(false ); 
		           }
		           out.flush();
		           out.close();
	     }
		//修改图书信息 admin	
		public void modifyBook(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {	
					Book book=new Book();//工具包中方法获取
					int num=bookDaoImpl.modifyBook(book);
				}
		// 查询用户的购书心愿单	user
		public void showMyWannaBuy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
			String user_id = request.getParameter("user_id");
			List<Book_Wish_Buy> book_Wish_Buy = bookDaoImpl.showMyWannaBuy(user_id);
			//查询所有类型 
			List<Book_Type> type_list = bookDaoImpl.showAllBookType();
			request.setAttribute("rank_type", type_list);
			//所有作者
			List<Book_Author> alist = bookDaoImpl.showAllBookAuthor();
			request.setAttribute("book_author", alist);
			if(book_Wish_Buy != null){
		    	for (Iterator iterator = book_Wish_Buy.iterator(); iterator.hasNext();) {
					Book_Wish_Buy book_Wish_Buy2 = (Book_Wish_Buy) iterator.next();
					Book book = new Book();
					book = psifImpl.showOneByR(book_Wish_Buy2.getBook()+"", book);		
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
					//计算该书平均分
					book.setAvgscore(bookDaoImpl.showOneBookScore(book.getBook_id()+""));
					book_Wish_Buy2.setB(book);
		    	}
		    	request.setAttribute("book", book_Wish_Buy);
		    }else{
		    	request.setAttribute("book", null);
		    }
		    //查询该用户
		    User_Info user = new User_Info();
		    user = psifImpl.showOneByR(user_id, user);
		    request.setAttribute("cuser", user);
		    request.getRequestDispatcher("/book/douban_wishbuylist.jsp").forward(request, response);
		}
		
		// 写书评	user
		public void writeBookComment(HttpServletRequest request, HttpServletResponse response)
				throws Exception {	
			HttpSession session = request.getSession();
			User_Info user = (User_Info) session.getAttribute("current_user");
			BookComment book_comment = new BookComment();
			book_comment.setBook_id(Integer.parseInt(request.getParameter("book_id")));
			book_comment.setUser_id(user.getUser_id());
			book_comment.setDate(ut.getCurrentAccurateTime());
			book_comment.setMasg(request.getParameter("content"));
			int result =  bookDaoImpl.writeBookComment(book_comment);
			PrintWriter out = response. getWriter();
	           if(result > 0) {
	               out.print(true );
	           } else {
	               out.print(false ); 
	           }
	           out.flush();
	           out.close();

		}
		
		// 给某书打分,选择阅读状态以及短评	user
		public void giveBookStation(HttpServletRequest request, HttpServletResponse response)
						throws Exception {	
					HttpSession session = request.getSession();
					User_Info user = (User_Info) session.getAttribute("current_user");
					BookStation bs = new BookStation();
					bs = EntityClass.returnEntity(bs, request);
					System.out.println(bs.getScore()+"    ////////////");
					bs.setDate(ut.getCurrentAccurateTime());
					bs.setUser_id(user.getUser_id());
					int result =  bookDaoImpl.giveBookStation(bs);
					PrintWriter out = response. getWriter();
			           if(result > 0) {
			               out.print(true );
			           } else {
			               out.print(false ); 
			           }
			           out.flush();
			           out.close();

				}
		
}
