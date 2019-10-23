package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import alb.util.assertion.Argument;

public class WorkOrder {
	public enum WorkOrderStatus {
		OPEN,
		ASSIGNED,
		FINISHED,
		INVOICED		
	}
	
	private Date date;
	private String description;
	private double amount = 0.0;
	private WorkOrderStatus status = WorkOrderStatus.OPEN;
	
	private Vehicle vehicle;
	
	/*
	 * Relacion assign
	 */
	private Mechanic mechanic;
	public Mechanic getMechanic() {
		return mechanic;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}
	
	/*
	 * Relacion invoice
	 */
	private Invoice invoice;
	public Invoice getInvoice() {
		return invoice;
	}
	
	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	/*
	 * Relacion intervenir
	 */
	private Set<Intervention> interventions = new HashSet<Intervention>();

	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
		
	}
	 Set<Intervention> _getInterventions() {
		 return interventions;
	}
	 
	 

	public WorkOrder(Vehicle vehicle2, String string) {
		Argument.isTrue(vehicle2 != null);
		this.date = new Date();
		this.description = string;
		Associations.Order.link(vehicle2,this);
	}



	/**
	 * Changes it to INVOICED state given the right conditions
	 * This method is called from Invoice.addWorkOrder(...)
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not FINISHED, or
	 *  - The work order is not linked with the invoice
	 */
	public void markAsInvoiced() {

	}

	/**
	 * Changes it to FINISHED state given the right conditions and 
	 * computes the amount
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in ASSIGNED state, or
	 *  - The work order is not linked with a mechanic
	 */
	public void markAsFinished() {
		//calcular amount
	}

	/**
	 * Changes it back to FINISHED state given the right conditions
	 * This method is called from Invoice.removeWorkOrder(...)
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not INVOICED, or
	 *  - The work order is still linked with the invoice
	 */
	public void markBackToFinished() {

	}

	/**
	 * Links (assigns) the work order to a mechanic and then changes its status
	 * to ASSIGNED
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in OPEN status, or
	 *  - The work order is already linked with another mechanic
	 */
	public void assignTo(Mechanic mechanic) {

	}

	/**
	 * Unlinks (deassigns) the work order and the mechanic and then changes 
	 * its status back to OPEN
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in ASSIGNED status
	 */
	public void desassign() {

	}

	/**
	 * In order to assign a work order to another mechanic is first have to
	 * be moved back to OPEN state and unlinked from the previous mechanic.  
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if
	 * 	- The work order is not in FINISHED status
	 */
	public void reopen() {

	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	void _setVehicle(Vehicle vehicle2) {
		this.vehicle = vehicle2;
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public WorkOrderStatus getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkOrder other = (WorkOrder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkOrder [date=" + date + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ ", vehicle=" + vehicle + "]";
	}
	

}
