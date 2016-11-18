package com.entity.lsr.book;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Book implements Serializable{
	
	private int book_id;
	private String book_name;
	private String imgs;
	private int book_author;
	private Book_Author b_author;
	private int book_publisher;
	private Book_Publisher b_publisher;
	private String sub_title;
	private int translator;
	private Book_Author b_translator;
	private Date publish_date;
	private int page_num;
	private String simple_desc;
	private String version;
	private int type;
	private Book_Type b_type;
	
	private BookStation current_user_book_station;
	private Double avgscore;
	private Book_Pre_Read pre_read;
	private List<BookStation> bookstation;
	private List<BookComment> bookcoment;
	
	
	
	
	 
	 
	 
	public BookStation getCurrent_user_book_station() {
		return current_user_book_station;
	}
	public void setCurrent_user_book_station(BookStation current_user_book_station) {
		this.current_user_book_station = current_user_book_station;
	}
	public List<BookStation> getBookstation() {
		return bookstation;
	}
	public void setBookstation(List<BookStation> bookstation) {
		this.bookstation = bookstation;
	}
	public List<BookComment> getBookcoment() {
		return bookcoment;
	}
	public void setBookcoment(List<BookComment> bookcoment) {
		this.bookcoment = bookcoment;
	}
	public Book_Publisher getB_publisher() {
		return b_publisher;
	}
	public void setB_publisher(Book_Publisher b_publisher) {
		this.b_publisher = b_publisher;
	}
 
	public Book_Author getB_author() {
		return b_author;
	}
	public void setB_author(Book_Author b_author) {
		this.b_author = b_author;
	}
	public Book_Author getB_translator() {
		return b_translator;
	}
	public void setB_translator(Book_Author b_translator) {
		this.b_translator = b_translator;
	}
	public Book_Type getB_type() {
		return b_type;
	}
	public void setB_type(Book_Type b_type) {
		this.b_type = b_type;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	 
	 
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	 
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	public int getPage_num() {
		return page_num;
	}
	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}
	public String getSimple_desc() {
		return simple_desc;
	}
	public void setSimple_desc(String simple_desc) {
		this.simple_desc = simple_desc;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	 
	public int getBook_author() {
		return book_author;
	}
	public void setBook_author(int book_author) {
		this.book_author = book_author;
	}
	public int getBook_publisher() {
		return book_publisher;
	}
	public void setBook_publisher(int book_publisher) {
		this.book_publisher = book_publisher;
	}
	public int getTranslator() {
		return translator;
	}
	public void setTranslator(int translator) {
		this.translator = translator;
	}
	 
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	 
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	public Double getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(Double avgscore) {
		this.avgscore = avgscore;
	}
	public Book_Pre_Read getPre_read() {
		return pre_read;
	}
	public void setPre_read(Book_Pre_Read pre_read) {
		this.pre_read = pre_read;
	}
	 
 
	
	
}
