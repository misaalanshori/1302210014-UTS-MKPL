package lib;

public class TaxFunction {

	static final double taxPercent = 0.05;
	static final int baseNonTaxed = 54000000;
	static final int marriedAddedNonTaxed = 4500000;
	static final int childAddedNonTaxed = 1500000;

	public static int calculateTax(Employee employee) {
		int tax = 0;
		
		if (employee.getMonthWorkingInYear() > 12) {
			System.err.println("More than 12 month working per year");
		}

		int employeeTotalIncome = ((employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * employee.getMonthWorkingInYear());

		int employeeNonTaxed = baseNonTaxed;
		if (employee.isMarried()) {
			employeeNonTaxed = baseNonTaxed + marriedAddedNonTaxed + (Math.min(employee.getNumberOfChildren(), 3) * childAddedNonTaxed);
		}

		tax = (int) Math.round(taxPercent * (employeeTotalIncome - employee.getAnnualDeductible() - employeeNonTaxed));

		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
	}
	
}
