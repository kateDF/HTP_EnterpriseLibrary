package by.htp.library.entity;

import java.time.LocalDate;

public class Record extends DbEntity {

	private Book book;
	private LocalDate returnDate;
	private LocalDate startDate;
	private LocalDate endDate;

	public Record() {

	}

	public Record(int id, LocalDate returnDate, LocalDate startDate, LocalDate endDate) {
		super(id);
		this.returnDate = returnDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Record(int id, Book book, LocalDate returnDate, LocalDate startDate, LocalDate endDate) {
		super(id);
		this.book = book;
		this.returnDate = returnDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " book=" + book + ", returnDate=" + returnDate + ", startDate=" + startDate
				+ ", endDate=" + endDate;
	}

}
