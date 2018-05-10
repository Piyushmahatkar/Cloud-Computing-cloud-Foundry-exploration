package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.DTO.*;

public class MapRoleDAO {

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public MapRoleDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public String add(UserDTO Role) {
		String message = "";
		try {
			String queryString1 = "SELECT distinct(UserId) from Users where UserId = ?";
			Connection connection1 = getConnection();
			ptmt = connection1.prepareStatement(queryString1);
			ptmt.setString(1, Role.getUser());
			ptmt.execute();
			ResultSet rs = ptmt.getResultSet();
			String duplicate = null;
			while (rs.next()) {
				duplicate = rs.getString(1);
			}
			/*if (duplicate == null) {

				String queryString = "INSERT INTO Users(PackageRoleId,RoleDescription) VALUES(?,?)";
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
			}*/

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

	public String add1(List<User_Hier_RoleDTO> pList) {
		String message1 = "";
		try {
		
			for(int i=0;i<pList.size();i++){
				String queryString = "INSERT INTO UserHierarchicalRole(UserId,HierarchicalRoleId,TenantId) VALUES(?,?,?)";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, pList.get(i).getUser());
				ptmt.setString(2, pList.get(i).getRole());
				ptmt.setString(3, pList.get(i).getTenant());
				ptmt.executeUpdate();
				//System.out.println(ptmt.executeUpdate());
				
			}
			
			message1 += "User Hierarchical Role added successfully";
				
					
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

	public List<RoleDTO> findAll() {
		final List<RoleDTO> RoleList = new LinkedList<RoleDTO>();
		try {
			String DescriptionList = "SELECT distinct(HierarchicalRoleId) FROM HRoleTable";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			RoleDTO role  = null;

			while (resultSet.next()) {
				String description = resultSet.getString("HierarchicalRoleId");
				role = new RoleDTO();
				role.setRole(description); 
				RoleList.add(role);
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
		return RoleList;
	}
	public List<User_Hier_RoleDTO> findAll1() {
		final List<User_Hier_RoleDTO> UserList = new LinkedList<User_Hier_RoleDTO>();
		try {
			String DescriptionList = "SELECT distinct(UserId) FROM UserHierarchicalRole";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			resultSet = ptmt.executeQuery();

			com.DTO.User_Hier_RoleDTO user  = null;

			while (resultSet.next()) {
				String description = resultSet.getString("UserId");
				user = new User_Hier_RoleDTO();
				user.setUser(description); 
				UserList.add(user);
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
		return UserList;
	}
	public List<User_Hier_RoleDTO> findAll2(User_Hier_RoleDTO Role2) {
		final List<User_Hier_RoleDTO> RoleList = new LinkedList<User_Hier_RoleDTO>();
		try {
			String DescriptionList = "SELECT HierarchicalRoleId FROM UserHierarchicalRole where UserId= ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(DescriptionList);
			ptmt.setString(1, Role2.getUser());
			System.out.println(ptmt);
			resultSet = ptmt.executeQuery();
			//System.out.println("HelloRole2.pack"+ Role2.getPackageRoleID());			
			//List<Package_Role_PermissionDTO> permissionList = new ArrayList<Package_Role_PermissionDTO>();
			User_Hier_RoleDTO role = null;

			while (resultSet.next()) {
				String roleid = resultSet.getString("HierarchicalRoleId");
				role = new User_Hier_RoleDTO();
				role.setRole(roleid);
				RoleList.add(role);
				
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
		return RoleList;
	}


	
}
