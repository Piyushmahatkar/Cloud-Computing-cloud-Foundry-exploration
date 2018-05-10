package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class HPermissionDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public HPermissionDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public String add(List<HierarchicalPermissionDTO> pList) {
		String message1 = "";
		try {
			for (int j = 0; j < pList.size(); j++) {
				String queryString1 = "SELECT distinct(HierarchicalPermissionId) from HierarchicalPermission where HierarchicalPermissionId = ?";

				Connection connection1 = getConnection();
				ptmt = connection1.prepareStatement(queryString1);
				ptmt.setString(1, pList.get(j).getHierarchicalPermissionID());
				ptmt.execute();
				ResultSet rs = ptmt.getResultSet();
				String duplicate = null;
				while (rs.next()) {
					duplicate = rs.getString(1);
				}

				if (duplicate == null) {

					for (int i = 0; i < pList.size(); i++) {
						String queryString = "INSERT INTO HierarchicalPermission(HierarchicalPermissionId,servicename) VALUES(?,?)";
						connection = getConnection();
						ptmt = connection.prepareStatement(queryString);
						ptmt.setString(1, pList.get(i)
								.getHierarchicalPermissionID());
						ptmt.setString(2, pList.get(i).getServicename());
						ptmt.executeUpdate();
						// System.out.println(ptmt.executeUpdate());

					}

					message1 += "Package_Role_Permission added successfully";
				} else {
					System.out.println("Please choose a different username..:");
					message1 += "RoleID";
				}
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


	public String add1(List<HierarchicalPermissionDTO> pList) {
		String message1 = "";
		try {
		
			for(int i=0;i<pList.size();i++){
				String queryString = "INSERT INTO HierarchicalPermission(HierarchicalPermissionId,servicename) VALUES(?,?)";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, pList.get(i).getHierarchicalPermissionID());
				ptmt.setString(2, pList.get(i).getServicename());
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
	public List<HierarchicalPermissionDTO> findAll() {
		final List<HierarchicalPermissionDTO> PackageList = new LinkedList<HierarchicalPermissionDTO>();
		try {
			String DescriptionList = "SELECT distinct HierarchicalPermissionID FROM HierarchicalPermission";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			HierarchicalPermissionDTO role = null;

			while (resultSet.next()) {
				String description = resultSet
						.getString("HierarchicalPermissionID");
				role = new HierarchicalPermissionDTO();
				role.setHierarchicalPermissionID(description);
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
	public List<HierarchicalPermissionDTO> findAll1(HierarchicalPermissionDTO Role2) {
		final List<HierarchicalPermissionDTO> PackageList = new LinkedList<HierarchicalPermissionDTO>();
		try {
			String DescriptionList = "SELECT servicename FROM HierarchicalPermission where HierarchicalPermissionId= ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			ptmt.setString(1, Role2.getHierarchicalPermissionID());
			System.out.println(ptmt);
			resultSet = ptmt.executeQuery();
			//System.out.println("HelloRole2.pack"+ Role2.getPackageRoleID());			
			//List<Package_Role_PermissionDTO> permissionList = new ArrayList<Package_Role_PermissionDTO>();
			HierarchicalPermissionDTO role = null;

			while (resultSet.next()) {
				String servicename = resultSet.getString("servicename");
				role = new HierarchicalPermissionDTO();
				role.setServicename(servicename);
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