package uo.ri.conf;

import uo.ri.persistance.certificate.CertificateGateway;
import uo.ri.persistance.certificate.impl.CertificateGatewayImpl;
import uo.ri.persistance.interventions.InterventionGateway;
import uo.ri.persistance.interventions.impl.InterventionGatewayImpl;
import uo.ri.persistance.invoice.InvoiceGateway;
import uo.ri.persistance.invoice.impl.InvoiceGatewayImpl;
import uo.ri.persistance.mechanic.MechanicGateway;
import uo.ri.persistance.mechanic.impl.MechanicGatewayImpl;
import uo.ri.persistance.training.CourseGateway;
import uo.ri.persistance.training.DedicationGateway;
import uo.ri.persistance.training.EnrollmentGateway;
import uo.ri.persistance.training.impl.CourseGatewayImpl;
import uo.ri.persistance.training.impl.DedicationGatewayImpl;
import uo.ri.persistance.training.impl.EnrollmentGatewayImpl;
import uo.ri.persistance.vehicle.VehicleGateway;
import uo.ri.persistance.vehicle.VehicleTypeGateway;
import uo.ri.persistance.vehicle.impl.VehicleGatewayImpl;
import uo.ri.persistance.vehicle.impl.VehicleTypeGatewayImpl;
import uo.ri.persistance.workorder.WorkOrderGateway;
import uo.ri.persistance.workorder.impl.WorkOrderGatewayImpl;

public class PersistanceFactory {

	/**
	 * Create a new instance of the implementation of MechanicGateway
	 * 
	 * @return a new MechanicGatewayImpl
	 */
	public MechanicGateway getMechanicCrudService() {
		return new MechanicGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of InvoiceGateway
	 * 
	 * @return a new InvoiceGatewayImpl
	 */
	public InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of WorkOrderGateway
	 * 
	 * @return a new WorkOrderGatewayImpl
	 */
	public WorkOrderGateway getWorkOrderGateway() {
		return new WorkOrderGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of EnrollmentGateway
	 * 
	 * @return a new EnrollmentGatewayImpl
	 */
	public EnrollmentGateway getEnrollmentGateway() {
		return new EnrollmentGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of CourseGateway
	 * 
	 * @return a new CourseGatewayImpl
	 */
	public CourseGateway getCourseGateway() {
		return new CourseGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of DedicationGateway
	 * 
	 * @return a new DedicationGatewayImpl
	 */
	public DedicationGateway getDedicationGateway() {
		return new DedicationGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of VehicleGateway
	 * 
	 * @return a new VehicleGatewayImpl
	 */
	public VehicleGateway getVehicleGateway() {
		return new VehicleGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of InterventionGateway
	 * 
	 * @return a new InterventionGatewayImpl
	 */
	public InterventionGateway getInterventionGateway() {
		return new InterventionGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of VehicleTypeGateway
	 * 
	 * @return a new VehicleTypeGatewayImpl
	 */
	public VehicleTypeGateway getVehicleTypeGateway() {
		return new VehicleTypeGatewayImpl();
	}

	/**
	 * Create a new instance of the implementation of CertificateGateway
	 * 
	 * @return a new MechanicGatewayImpl
	 */
	public CertificateGateway getCertificateGateway() {
		return new CertificateGatewayImpl();
	}

}
