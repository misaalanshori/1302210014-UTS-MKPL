package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	public static int calculateTax(Employee employee) {
		int tax = 0;
		
		if (employee.getMonthWorkingInYear() > 12) {
			System.err.println("More than 12 month working per year");
		}

		int employeeTotalIncome = ((employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * employee.getMonthWorkingInYear());

		int employeeNonTaxed = 54000000;
		if (employee.isMarried()) {
			employeeNonTaxed = employeeNonTaxed + 4500000 + (Math.min(employee.getNumberOfChildren(), 3) * 1500000);
		}

		tax = (int) Math.round(0.05 * (employeeTotalIncome - employee.getAnnualDeductible() - employeeNonTaxed));

		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
	}
	
}
