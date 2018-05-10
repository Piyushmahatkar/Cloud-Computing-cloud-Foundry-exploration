package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class PackageRoleDAO {

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public PackageRoleDAO() {

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
				message += "PackageRoleID";
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

	public List<PackagePermissionDTO> findAll() {
		final List<PackagePermissionDTO> PackageList = new LinkedList<PackagePermissionDTO>();
		try {
			String DescriptionList = "SELECT distinct(PackagePermissionID) FROM PackagePermission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			PackagePermissionDTO packagee  = null;

			while (resultSet.next()) {
				String description = resultSet.getString("PackagePermissionID");
				packagee = new PackagePermissionDTO();
				packagee.setPackagePermissionID(description); 
				PackageList.add(packagee);
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
	public List<Package_Role_PermissionDTO> findAll1() {
		final List<Package_Role_PermissionDTO> PackageList = new LinkedList<Package_Role_PermissionDTO>();
		try {
			String DescriptionList = "SELECT distinct(PackageRoleId) FROM Package_Role_Permission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			com.DTO.Package_Role_PermissionDTO packagee  = null;

			while (resultSet.next()) {
				String description = resultSet.getString("PackageRoleId");
				packagee = new Package_Role_PermissionDTO();
				packagee.setPackageRoleID(description); 
				PackageList.add(packagee);
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
	public List<Package_Role_PermissionDTO> findAll2(Package_Role_PermissionDTO Role2) {
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
				String permissionID = resultSet.getString("PackagePermissionID");
				role = new Package_Role_PermissionDTO();
				role.setPackagePermissionID1(permissionID);
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
