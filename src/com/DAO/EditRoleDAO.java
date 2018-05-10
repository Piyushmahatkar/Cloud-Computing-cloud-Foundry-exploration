package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class EditRoleDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public EditRoleDAO() {

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
				System.out.println(ptmt.executeUpdate());
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

	public String add1(Package_Role_PermissionDTO Role1) {
		String message1 = "";
		try {
			if (!Role1.getPackagePermissionID().isEmpty()) {
				System.out.println("size:"+Role1.getPackagePermissionID().size());
				for(int i=0; i< Role1.getPackagePermissionID().size(); i++)
				{
					System.out.println("permission id:"+Role1.getPackagePermissionID().get(i));
					String queryString = "INSERT INTO Package_Role_Permission(PackageRoleId,PackagePermissionID) VALUES(?,?)";
					connection = getConnection();
					ptmt = connection.prepareStatement(queryString);
					ptmt.setString(1, Role1.getPackageRoleID());
					ptmt.setString(2, Role1.getPackagePermissionID().get(i));
					ptmt.executeUpdate();
					System.out.println(ptmt.toString());
										
				}
				
				message1 += "Package_Role_Permission added successfully";
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

		return message1;

	}

	public List<Package_Role_PermissionDTO> findAll1(Package_Role_PermissionDTO Role2) {
		final List<Package_Role_PermissionDTO> PackageList = new LinkedList<Package_Role_PermissionDTO>();
		try {
			String DescriptionList = "SELECT PackagePermissionID FROM Package_Role_Permission where PackageRoleId= ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			ptmt.setString(1, Role2.getPackageRoleID());
			System.out.println(ptmt);
			resultSet = ptmt.executeQuery();
			//System.out.println("HelloRole2.pack"+ Role2.getPackageRoleID());			
			//List<Package_Role_PermissionDTO> permissionList = new ArrayList<Package_Role_PermissionDTO>();
			Package_Role_PermissionDTO role = null;

			while (resultSet.next()) {
				String PermissionID = resultSet.getString(1);
				role = new Package_Role_PermissionDTO ();
				role.setPackageRoleID(PermissionID);
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
	public List<PackageRoleDTO> findAll() {
		final List<PackageRoleDTO> PackageList = new LinkedList<PackageRoleDTO>();
		try {
			String DescriptionList = "SELECT PackageRoleID FROM PackageRole";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			PackageRoleDTO role = null;

			while (resultSet.next()) {
				String description = resultSet.getString("PackageRoleID");
				role = new PackageRoleDTO ();
				role.setPackageRoleId(description);;
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