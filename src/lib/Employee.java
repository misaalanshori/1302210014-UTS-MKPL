package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	public enum Gender {
		LAKILAKI,PEREMPUAN
	}

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private LocalDate dateJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private Gender gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private RelatedPerson spouse;

	private List<RelatedPerson> child;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		child = new LinkedList<RelatedPerson>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
		} else if (grade == 2) {
			monthlySalary = 5000000;
		} else if (grade == 3) {
			monthlySalary = 7000000;
		} else {
			return;
		}
		if (isForeigner) {
			monthlySalary = (int) (monthlySalary * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(RelatedPerson spouse) {
		this.spouse = spouse;
	}
	
	public void addChild(RelatedPerson child) {
		this.child.add(child);
	}
	
	public int getMonthWorkingInYear() {
		return monthWorkingInYear;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public int getOtherMonthlyIncome() {
		return otherMonthlyIncome;
	}

	public int getAnnualDeductible() {
		return annualDeductible;
	}

	public boolean isMarried() {
		return spouse != null;
	}

	public int getNumberOfChildren() {
		return child.size();
	}

	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonthValue();
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(this);
	}
}
