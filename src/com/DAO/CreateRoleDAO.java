package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class CreateRoleDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public CreateRoleDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public String add(PackageRoleDTO Role) {
		String message = "";
		try {
			String queryString1 = "SELECT distinct(PackageRoleId) from PackageRole where PackageRoleId = ?";
			Connection connection1 = getConnection();
			ptmt = connection1.prepareStatement(queryString1);
			ptmt.setString(1, Role.getPackageRoleId());
			ptmt.execute();
			ResultSet rs = ptmt.getResultSet();
			String duplicate = null;
			while (rs.next()) {
				duplicate = rs.getString(1);
			}
			if (duplicate == null) {

				String queryString = "INSERT INTO PackageRole(PackageRoleId,RoleDescription) VALUES(?,?)";
				System.out.println(Role.getPackageRoleId()
						+ Role.getRoleDescription());
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, Role.getPackageRoleId());
				ptmt.setString(2, Role.getRoleDescription());
				ptmt.executeUpdate();
				message += "Data added successfully";
			} else {
				System.out.println("Please choose a different username..:");
				message += "RoleID";
			}

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

	public String add1(List<Package_Role_PermissionDTO> pList) {
		String message1 = "";
		try {
		
			for(int i=0;i<pList.size();i++){
				String queryString = "INSERT INTO Package_Role_Permission(PackageRoleId,PackagePermissionID) VALUES(?,?)";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, pList.get(i).getPackageRoleID());
				ptmt.setString(2, pList.get(i).getPackagePermissionID1());
				ptmt.executeUpdate();
				//System.out.println(ptmt.executeUpdate());
				
			}
			
			message1 += "Package_Role_Permission added successfully";
				
					
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

		return message1;

	}

	/*
	 * public void update(StudentBean studentBean) {
	 * 
	 * try { String queryString = "UPDATE student SET Name=? WHERE RollNo=?";
	 * connection = getConnection(); ptmt =
	 * connection.prepareStatement(queryString); ptmt.setString(1,
	 * studentBean.getName()); ptmt.setInt(2, studentBean.getRollNo());
	 * ptmt.executeUpdate(); System.out.println("Table Updated Successfully"); }
	 * catch (SQLException e) { e.printStackTrace(); } finally { try { if (ptmt
	 * != null) ptmt.close(); if (connection != null) connection.close(); }
	 * 
	 * catch (SQLException e) { e.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace();
	 * 
	 * } } }
	 * 
	 * public void delete(int rollNo) {
	 * 
	 * try { String queryString = "DELETE FROM student WHERE RollNo=?";
	 * connection = getConnection(); ptmt =
	 * connection.prepareStatement(queryString); ptmt.setInt(1, rollNo);
	 * ptmt.executeUpdate(); System.out.println("Data deleted Successfully"); }
	 * catch (SQLException e) { e.printStackTrace(); } finally { try { if (ptmt
	 * != null) ptmt.close(); if (connection != null) connection.close(); }
	 * catch (SQLException e) { e.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 */
	public List<PackagePermissionDTO> findAll() {
		final List<PackagePermissionDTO> PackageList = new LinkedList<PackagePermissionDTO>();
		try {
			String DescriptionList = "SELECT Description FROM PackagePermission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			PackagePermissionDTO role = null;

			while (resultSet.next()) {
				String description = resultSet.getString("Description");
				role = new PackagePermissionDTO();
				role.setDescription(description);
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