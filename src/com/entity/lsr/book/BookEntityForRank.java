package com.entity.lsr.book;

/*
 * 为了向前端传输各类型书籍排行榜的临时实体类
 */
public class BookEntityForRank {

	
	private Book book;
	private Double score;
	private Book_Type type;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Book_Type getType() {
		return type;
	}
	public void setType(Book_Type type) {
		this.type = type;
	}
	
	
	
}
