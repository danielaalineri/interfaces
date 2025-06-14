package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	
	private Double pricePerHour;
	private Double pricePerDay;
	
	private TaxService taxService;

	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		
	double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
	double hours = minutes / 60.0;
	
	double basicPayment;
	if (hours <= 12.0) {
		basicPayment = pricePerHour * Math.ceil(hours); //esse math arredonda para cima
	}
	
	else {
		//Dividindo por dias
		basicPayment = pricePerDay * Math.ceil(hours / 24.0);
	}
	
	double tax = taxService.tax(basicPayment);//taxa do imposto.
	
	
	
	carRental.setInvoice(new Invoice(basicPayment, tax));
	
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public TaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(BrazilTaxService taxService) {
		this.taxService = taxService;
	}
	
	

}
