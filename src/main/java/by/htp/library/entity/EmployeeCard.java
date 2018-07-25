package by.htp.library.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCard extends DbEntity {

	private String fullName;
	private String phoneNumber;
	private String password;
	List<Record> records;

	public EmployeeCard() {

	}

	public EmployeeCard(int id, String fullName, String phoneNumber, String password, List<Record> records) {
		super(id);
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.records = records;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((records == null) ? 0 : records.hashCode());
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
		EmployeeCard other = (EmployeeCard) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", password=" + password
				+ ", records=" + records;
	}

	public List<Record> getRecordsWithoutReturnDate() {
		List<Record> result = new ArrayList<>();
		for (Record rec : records) {
			if (rec.getReturnDate() == null) {
				result.add(rec);
			}
		}
		return result;
	}

	public boolean hasDebt() {
		for (Record rec : records) {
			if (LocalDate.now().isAfter(rec.getEndDate()) && rec.getReturnDate() == null) {
				return true;
			}
		}
		return false;
	}

	public boolean hasMaxNumberOfBooks(int maxNumberOfBooks) {
		int counter = 0;
		for (Record rec : records) {
			LocalDate endDate = rec.getEndDate();
			LocalDate returnDate = rec.getReturnDate();
			if (endDate.isAfter(LocalDate.now()) && returnDate == null) {
				if (++counter >= maxNumberOfBooks) {
					return true;
				}
			}
		}
		return false;
	}

}
