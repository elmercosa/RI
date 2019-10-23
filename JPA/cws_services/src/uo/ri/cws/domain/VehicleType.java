package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

public class VehicleType {
	private String name;
	private double pricePerHour;

	/*
	 * Relation lassify
	 */

	private Set<Vehicle> vehicles = new HashSet<Vehicle>();

	public VehicleType(String name) {
		super();
		this.name = name;
	}
	
	

	public VehicleType(String name, double pricePerHour) {
		this(name);
		this.pricePerHour = pricePerHour;
	}



	public String getName() {
		return name;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}
	
	public Set<Vehicle> _getVehicles() {
		return vehicles;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		VehicleType other = (VehicleType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour + "]";
	}

}
