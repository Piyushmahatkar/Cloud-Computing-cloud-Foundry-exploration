package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.RobotDTO;

public class scoreUpdateServlet extends HttpServlet{

/*	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		System.out.println("In testing phase");
		String robotsPlayInfo = req.getParameter("q");
		System.out.println(robotsPlayInfo);
		String[] RobotRankingsInfo = robotsPlayInfo.split(" ");
		ArrayList final_arraylist = new ArrayList();
		for (int i = 0; i < RobotRankingsInfo.length; i++) { 
		    String[] each_robot_info = RobotRankingsInfo[i].split(":");
		    String[] robot_domain_info = each_robot_info[0].split("\\.");
		    System.out.println("the each robot info is" + Arrays.toString(each_robot_info));
		    System.out.println("The robot domain info is" + Arrays.toString(robot_domain_info));
			try {
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/form?user=naren&password=naren");

				Statement statement = connection.createStatement();
				System.out.println("UPDATE robot SET Robot_Ranking = " + each_robot_info[1] +  " WHERE robot.Package_Id = '"	+ robot_domain_info[0] + "' AND robot.Robot_Name = '"	+ robot_domain_info[1] + "'");
				int resultset = statement.executeUpdate("UPDATE robot SET Robot_Ranking = " + each_robot_info[1] +  " WHERE robot.Package_Id = '"	+ robot_domain_info[0] + "' AND robot.Robot_Name = '"	+ robot_domain_info[1] + "'");
				ResultSet rs = statement
						.executeQuery("SELECT robot.Package_Id, robot.Robot_Name, robot.Robot_Ranking FROM robot");
				ArrayList<String> domains = new ArrayList<String>();
				ArrayList<String> robo_name = new ArrayList<String>();
				ArrayList<String> ranking = new ArrayList<String>();
				while(rs.next()){
					
					domains.add(rs.getString("robot.Package_Id"));
					robo_name.add(rs.getString("robot.Robot_Name"));
					ranking.add(rs.getString("robot.Robot_Ranking"));
				}
				final_arraylist.add(domains);
				final_arraylist.add(robo_name);
				final_arraylist.add(ranking);
				 }catch (Exception e) {
					 e.printStackTrace();
				}
			System.out.println("The array list size is"+ final_arraylist.size());
			HttpSession session = req.getSession();
			session.setAttribute("final_arraylist", final_arraylist);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("robocode_results.jsp");
			requestDispatcher.forward(req, resp);
			return;
		}



	}*/

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//HashMap<Integer, List<String>> robot_rank = new HashMap<Integer, List<String>>();
		Map<Integer, List<String>> robot_rank = new TreeMap<Integer, List<String>>(Collections.reverseOrder());
		Map<String, Integer> transformed_rating = new HashMap<String, Integer>();
		Map<String, Integer> final_value = new HashMap<String, Integer>();
		System.out.println("In testing phase");
		String robotsPlayInfo = req.getParameter("q");
		System.out.println(robotsPlayInfo);
		String[] RobotRankingsInfo = robotsPlayInfo.split(" ");
		ArrayList<ArrayList<String>> final_arraylist = new ArrayList<ArrayList<String>>();
		String package_info = "";
		String robot_name = "";
		String winning_robot = "";
		int winning_robot_value = -1;
		Integer total_current_ratings = 0;
		String query = "SELECT robot.Package_Id, robot.Robot_Name, robot.Robot_Ranking FROM robot where robot.Package_Id ='";
		System.out.println("the robots info length is" + RobotRankingsInfo.length);
		String final_query = "";
		int counter = RobotRankingsInfo.length-1;
		for (int i = 0; i < RobotRankingsInfo.length; i++) { 
		    String[] each_robot_info = RobotRankingsInfo[i].split(":");
		    String[] robot_domain_info = each_robot_info[0].split("\\.");
		    System.out.println("The robot domain info is" + Arrays.toString(robot_domain_info));
		    //package_info = package_info + robot_domain_info[0] + "' or robot.Package_Id = '";
		    //robot_name = robot_name + robot_domain_info[1] + "' or robot.Robot_Name = '";
		    final_query += query + robot_domain_info[0] + "' AND robot.Robot_Name ='" + robot_domain_info[1] + "'";
		    if(counter > 0)
		    {
		    	System.out.println("the counter is "+ counter);
		    	final_query += " union ";
		    }
		    counter -= 1;
		    if (Integer.parseInt(each_robot_info[1]) > winning_robot_value){
		    	winning_robot_value = Integer.parseInt(each_robot_info[1]);
		    	winning_robot = robot_domain_info[1];
		    }
		}
		System.out.println("The package_info for select stmt is " + package_info + robot_name);
		System.out.println("the final query is" + final_query);
		try{
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/form?user=naren&password=naren");

			Statement statement = connection.createStatement();
				System.out.println(final_query);
				ResultSet rs = statement.executeQuery(final_query);
				System.out.println("the result set length is"+ rs);
				int rowcount = 0;
				if (rs.last()) {
				  rowcount = rs.getRow();
				  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
				}
				System.out.println("the rs length is"+ rowcount);
				while(rs.next()){
					
				/*
				 * Apply ELO Rating here
				 * Find the current rating of the robot and find the transformed rating R(robot) = 10^(current_rating / 400)
				 * It depends on how many robots playing in the game. So first find how many robots are playing.
				 * Find the expected score: E(1) = R(1) / (R(1) + R(2))
				 * Find who won the match : If player won then its score is 1.0 and if the match is drawn - 0.5 and lost - 0.0
				 * Now compute the new points of the robot: R'(robot) = R(robot) + K* (S[robot]-E[robot])
				 */
				int current_robot_points = Integer.parseInt(rs.getString("Robot_Ranking"));
				System.out.println("the current robot ranking is"+ current_robot_points);
				if(current_robot_points == 0){
					current_robot_points = 100;
				}
				int trans_rating = 10 ^ (current_robot_points/400);
				System.out.println("The trans-rating" + trans_rating);
				transformed_rating.put(rs.getString("Robot_Name"), trans_rating);
				total_current_ratings += trans_rating;
				}
				/*
				 * Calculate the expected value
				 */
				System.out.println("The total current rating is" + total_current_ratings);
				Set setOfKeys = transformed_rating.keySet();
				Iterator iterator = setOfKeys.iterator();
				while (iterator.hasNext()) {
					String played_robot_name = (String)iterator.next();
					Integer value = transformed_rating.get(played_robot_name) / total_current_ratings;
					Integer s = 0;
					if(played_robot_name.equalsIgnoreCase(winning_robot)){
						System.out.println("The robot who wins the game"+ played_robot_name + winning_robot);
						s = 1;
					}
					Integer f_v =  transformed_rating.get(played_robot_name) + 32 * (s - value);
					System.out.println("The f_v value of robot is"+ played_robot_name + f_v);
					final_value.put(played_robot_name, f_v);
				}
	    	
	    }catch (Exception e) {
			 e.printStackTrace();
		}		
		for (int i = 0; i < RobotRankingsInfo.length; i++) { 
		    String[] each_robot_info = RobotRankingsInfo[i].split(":");
		    String[] robot_domain_info = each_robot_info[0].split("\\.");
		    System.out.println("the each robot info is" + Arrays.toString(each_robot_info));
		    System.out.println("The robot domain info is" + Arrays.toString(robot_domain_info));
			try {
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/form?user=naren&password=naren");

				Statement statement = connection.createStatement();
				System.out.println("UPDATE robot SET Robot_Ranking = " + final_value.get(robot_domain_info[1]) +  " WHERE robot.Package_Id = '"	+ robot_domain_info[0] + "' AND robot.Robot_Name = '"	+ robot_domain_info[1] + "'");
				int resultset = statement.executeUpdate("UPDATE robot SET Robot_Ranking = Robot_Ranking + " + final_value.get(robot_domain_info[1]) +  " WHERE robot.Package_Id = '"	+ robot_domain_info[0] + "' AND robot.Robot_Name = '"	+ robot_domain_info[1] + "'");
			 }catch (Exception e) {
				 e.printStackTrace();
			}
		}
		try {
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://robocodedb.cloudapp.net:3306/form?user=naren&password=naren");

			Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT robot.Package_Id, robot.Robot_Name, robot.Robot_Ranking FROM robot");
				ArrayList<String> domains = new ArrayList<String>();
				ArrayList<String> robo_name = new ArrayList<String>();
				ArrayList<String> ranking = new ArrayList<String>();
				
				while(rs.next()){
					
					/*domains.add(rs.getString("robot.Package_Id"));
					robo_name.add(rs.getString("robot.Robot_Name"));
					ranking.add(rs.getString("robot.Robot_Ranking"));*/
					if(robot_rank.get(Integer.parseInt(rs.getString("robot.Robot_Ranking"))) == null){
						robot_rank.put(Integer.parseInt(rs.getString("robot.Robot_Ranking")), new ArrayList<String>());
						robot_rank.get(Integer.parseInt(rs.getString("robot.Robot_Ranking"))).add(rs.getString("robot.Robot_Name"));
						
					}
					else{
						/*
						 * Apply ELO Rating here
						 * Find the current rating of the robot and find the transformed rating R(robot) = 10^(current_rating / 400)
						 * It depends on how many robots playing in the game. So first find how many robots are playing.
						 * Find the expected score: E(1) = R(1) / (R(1) + R(2))
						 * Find who won the match : If player won then its score is 1.0 and if the match is drawn - 0.5 and lost - 0.0
						 * Now compute the new points of the robot: R'(robot) = R(robot) + K* (S[robot]-E[robot])
						 */
						robot_rank.get(Integer.parseInt(rs.getString("robot.Robot_Ranking"))).add(rs.getString("robot.Robot_Name"));
					}
				}
				
				
			
			
		}catch (Exception e) {
			 e.printStackTrace();
		}
		HttpSession session = req.getSession();
		session.setAttribute("robot_rank", robot_rank);
		System.out.println("The final list is" + robot_rank);
		req.getRequestDispatcher("robocode_results.jsp").forward(req,resp);
	}

	
	
}
