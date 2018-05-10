package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class PPermissionDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public PPermissionDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public String add(PackagePermissionDTO Role) {
		String message = "";
		try {
			String queryString = "INSERT INTO PackagePermission(PackagePermissionID, Description, PackageID, RoleRights, TenantId)  VALUES(?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, Role.getPackagePermissionID());
			ptmt.setString(2, Role.getDescription());
			ptmt.setString(3, Role.getPackageID());
			ptmt.setInt(4, Role.getRoleRights());
			ptmt.setString(5, Role.getTenantID());
			ptmt.executeUpdate();
			message += "Data added successfully";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return message;
	}
	public String update(PackagePermissionDTO Role) {
		String message = "";
		try {
			String queryString = "UPDATE PackagePermission SET Description = ?,PackageID = ?, RoleRights = ?, TenantId= ? where PackagePermissionID = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);			
			ptmt.setString(1, Role.getDescription());
			ptmt.setString(2, Role.getPackageID());
			ptmt.setInt(3, Role.getRoleRights());
			ptmt.setString(4, Role.getTenantID());
			ptmt.setString(5, Role.getPackagePermissionID());
			ptmt.executeUpdate();
			message += "Data added successfully";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return message;
	}
	public List<PackagePermissionDTO> findAll() {
		final List<PackagePermissionDTO> PackageList = new LinkedList<PackagePermissionDTO>();
		try {
			String DescriptionList = "SELECT distinct PackagePermissionID FROM PackagePermission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			PackagePermissionDTO role = null;

			while (resultSet.next()) {
				String description = resultSet
						.getString("PackagePermissionID");
				role = new PackagePermissionDTO();
				role.setPackagePermissionID(description);
				PackageList.add(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return PackageList;
	}
	public List<PackagePermissionDTO> findAll1(PackagePermissionDTO Role2) {
		final List<PackagePermissionDTO> PackageList = new LinkedList<PackagePermissionDTO>();
		try {
			String DescriptionList = "SELECT * FROM PackagePermission where PackagePermissionId= ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			ptmt.setString(1, Role2.getPackagePermissionID());
			System.out.println(ptmt);
			resultSet = ptmt.executeQuery();
			//System.out.println("HelloRole2.pack"+ Role2.getPackageRoleID());			
			//List<Package_Role_PermissionDTO> permissionList = new ArrayList<Package_Role_PermissionDTO>();
			PackagePermissionDTO role = null;

			while (resultSet.next()) {
				String Description = resultSet.getString("Description");
				String PackageID = resultSet.getString("PackageID");
				String RoleRights = resultSet.getString("RoleRights");
				String TenantID = resultSet.getString("TenantID");
				int roleRights = Integer.parseInt(RoleRights);
				role = new PackagePermissionDTO();
				role.setDescription(Description);
				role.setPackageID(PackageID);
				role.setRoleRights(roleRights);
				role.setTenantID(TenantID);
				PackageList.add(role);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return PackageList;
	}


}