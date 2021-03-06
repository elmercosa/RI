package uo.ri.ui.util;

import java.util.List;

import alb.util.console.Console;
import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.dto.ContractDto;
import uo.ri.business.dto.ContractTypeDto;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.PayrollDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.dto.WorkOrderDto;

public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double importeConIVa = invoice.total;
		double iva = invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Factura nº: %d%n", invoice.number);
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY%n", invoice.date);
		Console.printf("\tTotal: %.2f €%n", importeSinIva);
		Console.printf("\tIva: %.1f %% %n", invoice.vat);
		Console.printf("\tTotal con IVA: %.2f €%n", invoice.total);
		Console.printf("\tEstado: %s%n", invoice.status);
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Medios de pago disponibles");

		Console.printf("\t%s \t%-8.8s \t%s %n", "ID", "Tipo", "Acumulado");
		for (PaymentMeanDto medio : medios) {
			printPaymentMean(medio);
		}
	}

	private static void printPaymentMean(PaymentMeanDto medio) {
		Console.printf("\t%s \t%-8.8s \t%s %n", medio.id, medio.getClass().getName(), // not the best...
				medio.accumulated);
	}

	public static void printRepairing(BreakdownDto rep) {

		Console.printf("\t%d \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f%n", rep.id, rep.description, rep.date,
				rep.status, rep.total);
	}

	public static void printMechanic(MechanicDto m) {

		Console.printf("\t%d %-10.10s %-25.25s %-25.25s%n", m.id, m.dni, m.name, m.surname);
	}

	public static void printContractCategory(ContractCategoryDto c) {

		Console.printf("\t%d %-20.20s %7.2f %3.2f%n", c.id, c.name, c.trieniumSalary, c.productivityPlus);

	}

	public static void printContractType(ContractTypeDto t) {

		Console.printf("\t%d %-20.20s %2d%n", t.id, t.name, t.compensationDays);

	}

	public static void printContract(ContractDto c) {

		Console.printf("\t%d %-10.10s %-20.20s %-20.20s %8.8s %td/%tm/%tY %7.2f %7.2f %td/%tm/%tY %n", c.id, c.dni,
				c.categoryName, c.typeName, c.status, c.startDate, c.startDate, c.startDate, c.yearBaseSalary,
				c.compensation, c.endDate, c.endDate, c.endDate);

	}

	public static void printPayrollSummary(PayrollDto p) {

		Console.printf("\t%d %tm/%tY %7.2f%n", p.id, p.date, p.date, p.netTotal);

	}

	public static void printPayrollDetail(PayrollDto p) {

		Console.printf("\t%d %tm/%tY %7.2f%n %7.2f%n %7.2f%n", p.id, p.date, p.date, p.grossTotal, p.discountTotal,
				p.netTotal);
		Console.println("Abonos");
		Console.printf("/t Salario base: %7.2f%n", p.baseSalary);
		Console.printf("/t        extra: %7.2f%n", p.extraSalary);
		Console.printf("/tproductividad: %7.2f%n", p.productivity);
		Console.printf("/t     trienios: %7.2f%n", p.triennium);
		Console.printf("Descuentos");
		Console.printf("/t         IRPF: %7.2f%n", p.irpf);
		Console.printf("/t    S. social: %7.2f%n", p.socialSecurity);
	}

	public static void printCertifiedMechanic(CertificateDto c) {

		Console.printf("%d\t%-10.10s %-25.25s %-25.25s\n", c.mechanic.id, c.mechanic.dni, c.mechanic.name,
				c.vehicleType.name);
	}

	public static void printWorkOrderDetail(WorkOrderDto wo) {

		Console.printf("%d for vehicle %s\n\t%-25.25s\n\t%tm/%<td/%<tY\n\t%s\n", wo.id, wo.vehicleId, wo.description,
				wo.date, wo.status);
	}

	public static void printVehicleDetail(VehicleDto v) {

		Console.printf("%d\t%-8.8s\t%s\t%s\n", v.id, v.plate, v.make, v.model);
	}

	public static void printTrainingForMechanic(TrainingForMechanicRow row) {

		Console.printf("\t%-20.20s\t%d\t%d\n", row.vehicleTypeName, row.enrolledHours, row.attendedHours);

	}

	public static void printTrainingHoursRow(TrainingHoursRow r) {
		Console.println((r.vehicleTypeName.equals("") ? r.vehicleTypeName: r.vehicleTypeName+"\n") + "\t"+r.mechanicFullName + "\t" +  r.enrolledHours);
		//Console.printf("%-20.20s %-30.30s\t%d hours\n", r.vehicleTypeName.equals("") ? "\n": r.vehicleTypeName+"\n", r.mechanicFullName, r.enrolledHours);
	}
	public static void printVehicleType(VehicleTypeDto vt) {

		Console.printf("\t%d %-10.10s %5.2f %d\n"
				, vt.id
				, vt.name
				, vt.pricePerHour
				, vt.minTrainigHours
			);
	}
	public static void printCourse(CourseDto c) {

		Console.printf("%d\t%s %s %-35.35s %td/%<tm/%<tY %td/%<tm/%<tY %d\n"
				, c.id
				, c.code
				, c.name
				, c.description
				, c.startDate
				, c.endDate
				, c.hours
			);
		c.percentages.forEach((id, percent) ->
			Console.printf("\t %d %d percent\n", id, percent)
		);
	}

}
