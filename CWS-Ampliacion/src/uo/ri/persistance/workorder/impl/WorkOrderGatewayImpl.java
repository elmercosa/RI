package uo.ri.persistance.workorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Conf;
import uo.ri.persistance.workorder.WorkOrderGateway;

public class WorkOrderGatewayImpl implements WorkOrderGateway {
	private Connection c;

	@Override
	public String checkWorkOrderStatus(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_CHECK_WORKORDER_STATUS = Conf.getInstance().getProperty("SQL_CHECK_WORKORDER_STATUS");
		try {
			pst = c.prepareStatement(SQL_CHECK_WORKORDER_STATUS);
			pst.setLong(1, workOrderID);
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return "";
			}
			return rs.getString(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void updateWorkorderTotal(Long workOrderID, double total) {
		PreparedStatement pst = null;
		String SQL_UPDATE_WORKORDER_AMOUNT = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_AMOUNT");
		try {
			pst = c.prepareStatement(SQL_UPDATE_WORKORDER_AMOUNT);
			pst.setDouble(1, total);
			pst.setLong(2, workOrderID);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void linkWorkOrderInvoice(long invoiceId, Long workOrderID) {
		String SQL_WORKORDER_INVOICE_ASSOC = Conf.getInstance().getProperty("SQL_WORKORDER_INVOICE_ASSOC");
		PreparedStatement pst = null;
		try {
			pst = c.prepareStatement(SQL_WORKORDER_INVOICE_ASSOC);
				pst.setLong(1, invoiceId);
				pst.setLong(2, workOrderID);
				pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateWorkOrderStatus(Long breakdownId, String status) {
		PreparedStatement pst = null;
		String SQL_UPDATE_WORKORDER_STATUS = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_STATUS");
		try {
			pst = c.prepareStatement(SQL_UPDATE_WORKORDER_STATUS);
			pst.setString(1, status);
			pst.setLong(2, breakdownId);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double checkTotalLabor(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_LABOR_TOTAL = Conf.getInstance().getProperty("SQL_LABOR_TOTAL");
		try {
			pst = c.prepareStatement(SQL_LABOR_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return -1;
			}

			return rs.getDouble(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double checkTotalParts(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_PARTS_TOTAL = Conf.getInstance().getProperty("SQL_PARTS_TOTAL");
		try {
			pst = c.prepareStatement(SQL_PARTS_TOTAL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // There is no part replaced in this breakdown
			}

			return rs.getDouble(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(WorkOrderDto dto) {
		PreparedStatement pst = null;
		String SQL_ADD_WORKORDER = Conf.getInstance().getProperty("SQL_ADD_WORKORDER");
		try {
			pst = c.prepareStatement(SQL_ADD_WORKORDER);
			pst.setLong(1, dto.vehicleId);
			pst.setString(2, dto.description);
			pst.setDate(3,  new java.sql.Date(new java.util.Date().getTime()));
			pst.setString(4, "OPEN");
			pst.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Long findLastWorkOrder() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_LAST_ID = Conf.getInstance().getProperty("SQL_LAST_ID");
		try {
			pst = c.prepareStatement(SQL_LAST_ID);
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			return rs.getLong(1);

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean findWorkOrderByData(WorkOrderDto dto) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_FIND_WORKORDER_BY_DATA = Conf.getInstance().getProperty("SQL_FIND_WORKORDER_BY_DATA");
		try {
			pst = c.prepareStatement(SQL_FIND_WORKORDER_BY_DATA);
			pst.setDate(1,  new java.sql.Date(dto.date.getTime()));
			pst.setLong(2, dto.vehicleId);
			rs = pst.executeQuery();

			return rs.next();

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WorkOrderDto findWorkOrderByID(Long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		WorkOrderDto dto = null;
		String SQL_FIND_WORKORDER_BY_ID = Conf.getInstance().getProperty("SQL_FIND_WORKORDER_BY_ID");
		try {
			pst = c.prepareStatement(SQL_FIND_WORKORDER_BY_ID);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				dto = new WorkOrderDto();
				dto.id = id;
				dto.date = rs.getDate(3);
				dto.description = rs.getString(4);
				dto.status = rs.getString(5);
				dto.invoiceId = rs.getLong(6);
				dto.mechanicId = rs.getLong(7);
				dto.vehicleId = rs.getLong(8);
			}
			return dto;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) {
		PreparedStatement pst = null;
		String SQL_UPDATE_WORKORDER_DESCRIPTION = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_DESCRIPTION");
		try {
			pst = c.prepareStatement(SQL_UPDATE_WORKORDER_DESCRIPTION);
			pst.setString(1, dto.description);
			pst.setLong(2, dto.id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void remove(Long workorderID) {
		String SQL_DELETE_WORKORDER = Conf.getInstance().getProperty("SQL_DELETE_WORKORDER");
		PreparedStatement pst = null;
		try {
			pst = c.prepareStatement(SQL_DELETE_WORKORDER);
			pst.setLong(1, workorderID);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void assignMechanic(Long workorderID, Long mechanicID) {
		System.out.println(mechanicID);
		PreparedStatement pst = null;
		String SQL_UPDATE_WORKORDER_MECHANIC = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_MECHANIC");
		try {
			pst = c.prepareStatement(SQL_UPDATE_WORKORDER_MECHANIC);
			pst.setLong(1, mechanicID);
			pst.setLong(2, workorderID);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
