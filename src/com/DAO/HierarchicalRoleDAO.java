package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class HierarchicalRoleDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public HierarchicalRoleDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public String add(HRoleTableDTO Role) {
		String message = "";
		try {
			String queryString1 = "SELECT distinct(HierarchicalRoleId) from HRoleTable where HierarchicalRoleId = ?";
			Connection connection1 = getConnection();
			ptmt = connection1.prepareStatement(queryString1);
			ptmt.setString(1, Role.getHierarchicalRoleID());
			ptmt.execute();
			ResultSet rs = ptmt.getResultSet();
			String duplicate = null;
			while (rs.next()) {
				duplicate = rs.getString(1);
			}
			if (duplicate == null) {

				String queryString = "INSERT INTO HRoleTable(HierarchicalRoleId,Description) VALUES(?,?)";
				System.out.println(Role.getHierarchicalRoleID()
						+ Role.getDescription());
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, Role.getHierarchicalRoleID());
				ptmt.setString(2, Role.getDescription());
				ptmt.executeUpdate();
				message += "Data added successfully";
			} else {
				System.out.println("Please choose a different username..:");
				message += "HierarchyRoleID";
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

	public String add1(List<Hierarchical_Role_PermissionDTO> pList) {
		String message1 = "";
		try {
		
			for(int i=0;i<pList.size();i++){
				String queryString = "INSERT INTO Hierarchical_Role_Permission(HierarchicalRoleId,HierarchicalPermissionID) VALUES(?,?)";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, pList.get(i).getHierarchicalRoleID());
				ptmt.setString(2, pList.get(i).getHierarchicalPermissionID());
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

	public List<HierarchicalPermissionDTO> findAll() {
		final List<HierarchicalPermissionDTO> PackageList = new LinkedList<HierarchicalPermissionDTO>();
		try {
			String DescriptionList = "SELECT distinct(HierarchicalPermissionID) FROM HierarchicalPermission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			HierarchicalPermissionDTO hierarchy  = null;

			while (resultSet.next()) {
				String description = resultSet.getString("HierarchicalPermissionID");
				hierarchy = new HierarchicalPermissionDTO();
				hierarchy.setHierarchicalPermissionID(description); 
				PackageList.add(hierarchy);
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
	public List<Hierarchical_Role_PermissionDTO> findAll1() {
		final List<Hierarchical_Role_PermissionDTO> PackageList = new LinkedList<Hierarchical_Role_PermissionDTO>();
		try {
			String DescriptionList = "SELECT distinct(HierarchicalRoleId) FROM Hierarchical_Role_Permission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			com.DTO.Hierarchical_Role_PermissionDTO hierarchy  = null;

			while (resultSet.next()) {
				String description = resultSet.getString("HierarchicalRoleId");
				hierarchy = new Hierarchical_Role_PermissionDTO();
				hierarchy.setHierarchicalRoleID(description); 
				PackageList.add(hierarchy);
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
	public List<Hierarchical_Role_PermissionDTO> findAll2(Hierarchical_Role_PermissionDTO Role2) {
		final List<Hierarchical_Role_PermissionDTO> PackageList = new LinkedList<Hierarchical_Role_PermissionDTO>();
		try {
			String DescriptionList = "SELECT HierarchicalPermissionID FROM Hierarchical_Role_Permission where HierarchicalRoleId= ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			ptmt.setString(1, Role2.getHierarchicalRoleID());
			System.out.println(ptmt);
			resultSet = ptmt.executeQuery();
			//System.out.println("HelloRole2.pack"+ Role2.getPackageRoleID());			
			//List<Package_Role_PermissionDTO> permissionList = new ArrayList<Package_Role_PermissionDTO>();
			Hierarchical_Role_PermissionDTO role = null;

			while (resultSet.next()) {
				String permissionID = resultSet.getString("HierarchicalPermissionID");
				role = new Hierarchical_Role_PermissionDTO();
				role.setHierarchicalPermissionID(permissionID);
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