package com.app.java.skill_link.src.utils;

import com.app.java.skill_link.src.entity.Employee;
import com.app.java.skill_link.src.entity.dto.SalaryDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

@Component
public class TaxCalculationUtils {

    private static final double SLAB_1 = 250000;
    private static final double SLAB_2 = 500000;
    private static final double SLAB_3 = 1000000;
    private static final double CESS_THRESHOLD = 2500000;
    private static final double CESS_RATE = 0.02;

    public SalaryDetails calculateTax(Employee empData) {
        double proratedSalary = calculateProratedSalary(empData.getSalary(), LocalDate.parse(empData.getDoj()));

        // Calculate the tax for the prorated salary
        double tax = calculateTaxForSalary(proratedSalary);

        // Calculate the cess if salary exceeds 2,500,000
        double cess = 0;
        if (proratedSalary > CESS_THRESHOLD) {
            cess = (proratedSalary - CESS_THRESHOLD) * CESS_RATE;
        }
        return SalaryDetails.builder().employeeId(empData.getEmployeeId())
                .firstName(empData.getFirstName())
                .lastName(empData.getLastName())
                .yearlySalary(proratedSalary)
                .taxAmount(tax)
                .cessAmount(cess)
                .build();
    }

    private double calculateProratedSalary(Double monthlySalary, LocalDate doj) {
        LocalDate startOfFinancialYear = LocalDate.of(LocalDate.now().getYear(), Month.APRIL, 1);
        LocalDate endOfFinancialYear = LocalDate.of(LocalDate.now().getYear() + 1, Month.MARCH, 31);

        if (doj.isAfter(startOfFinancialYear)) {
            startOfFinancialYear = doj;
        }

        Period period = Period.between(startOfFinancialYear, endOfFinancialYear);
        long monthsWorked = period.getMonths();

        double proratedSalary = monthlySalary * monthsWorked;
        double partialMonthSalary = calculatePartialMonth(doj, LocalDate.now()) * monthlySalary;
        proratedSalary += partialMonthSalary;

        return proratedSalary;
    }

    public double calculatePartialMonth(LocalDate doj, LocalDate currentDate) {
        if (doj.getMonth() == currentDate.getMonth()) {
            long daysWorkedInMonth = currentDate.getDayOfMonth() - doj.getDayOfMonth() + 1;
            int daysInMonth = currentDate.lengthOfMonth();
            return (double) daysWorkedInMonth / daysInMonth;
        }
        return 1.0;
    }

    private double calculateTaxForSalary(double salary) {
        double tax = 0;

        // First 250,000: No tax
        if (salary > SLAB_3) {
            tax += (salary - SLAB_3) * 0.20;
            salary = SLAB_3;
        }
        if (salary > SLAB_2) {
            tax += (salary - SLAB_2) * 0.10;
            salary = SLAB_2;
        }
        if (salary > SLAB_1) {
            tax += (salary - SLAB_1) * 0.05;
        }

        return tax;
    }
}
