package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.*;

import java.io.*;

import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.xpath.*;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

import DAO.LoginDAO;
import DTO.UserDTO;
import Service.LoginRestClientService;

public class LoginServlet extends HttpServlet {
	HttpSession session = null;
	UserDTO userDTO = null;
	private static final long serialVersionUID = 1L;
	private String inclause = "";
	private int nnum = 0;

	public void displaySAMLInfo(Node node, String parent,
			HttpServletRequest req, HttpServletResponse res) throws IOException {
		// session.setAttribute("SAML", "Before try");
		try {
			String nodeName;
			int nChild, i;

			nodeName = node.getNodeName();
			// System.out.println("<br>");
			// out.println("<u>Examining <b>" + parent + nodeName +
			// "</b></u><br>");
			// session.setAttribute("SAML", "After node name");
			// Attributes.
			// session.setAttribute("SAML", "Before if");
			NamedNodeMap attribsMap = node.getAttributes();
			if (null != attribsMap) {
				for (i = 0; i < attribsMap.getLength(); i++) {
					Node attrib = attribsMap.item(i);
					if (attrib
							.getNodeValue()
							.equals("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name")) {
						// System.out.println("Attribute: <b>" +
						// ((org.w3c.dom.Node) attrib).getNodeName() + "</b>: "
						// + ((org.w3c.dom.Node) attrib).getNodeValue() +
						// "<br>");
					}
				}
			}

			// Child nodes.
			NodeList list = node.getChildNodes();
			// session.setAttribute("SAML", "before second if");
			if (null != list) {
				// session.setAttribute("SAML", "List length after if " +
				// list.getLength());
				nChild = list.getLength();
				if (nChild > 0) {

					// session.setAttribute("SAML", nnum+1);
					// If it is a text node, just print the text.
					if (list.item(0).getNodeName() == "#text"
							& list.item(0).getTextContent().toString()
									.contains("@")) {
						System.out.println("Text value: <b>"
								+ list.item(0).getTextContent() + "</b><br>");
						String SAML = list.item(0).getTextContent();

						session.setAttribute("SAML", SAML);
						// res.sendRedirect("test.jsp");
					} else {
						// Print out the child node names.
						// out.print("Contains " + nChild + " child node(s): ");
						for (i = 0; i < nChild; i++) {
							Node temp = (Node) list.item(i);

							// System.out.print("<b>" + ((org.w3c.dom.Node)
							// temp).getNodeName() + "</b>");
							if (i < nChild - 1) {
								// Separate the names.
								// System.out.print(", ");
							} else {
								// Finish the sentence.
								// System.out.print(".");
							}

						}
						// System.out.println("<br>");

						// Process the child nodes.
						for (i = 0; i < nChild; i++) {
							Node temp = (Node) list.item(i);
							displaySAMLInfo(temp, parent + nodeName + "\\",
									req, res);
						}
					}
				}
			}
		} catch (Exception e) {
			// session.setAttribute("SAML", "error");
			// res.sendRedirect("test.jsp");
			System.out.println("Exception encountered.");
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Get");
		System.out.println(request.getParameterMap());
		try {
			if (request.getAttribute("ACSSAML") != null) {
				String data = (String) request.getAttribute("ACSSAML");

				// response.sendRedirect("test.jsp");
				DocumentBuilder docBuilder;
				Document doc = null;
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
						.newInstance();
				docBuilderFactory.setIgnoringElementContentWhitespace(true);
				docBuilder = docBuilderFactory.newDocumentBuilder();
				byte[] xmlDATA = data.getBytes();

				ByteArrayInputStream in = new ByteArrayInputStream(xmlDATA);
				doc = docBuilder.parse(in);
				doc.getDocumentElement().normalize();

				// Iterate the child nodes of the doc.
				NodeList list = doc.getChildNodes();

				session = request.getSession();
				// session.setAttribute("SAML", "Length" + list.getLength());
				// session.setAttribute("SAML", list);
				// response.sendRedirect("test.jsp");
				// response.sendRedirect("test.jsp");
				for (int i = 0; i < list.getLength(); i++) {
					//JspWriter out;
					// session.setAttribute("SAML", "Inside for");
					displaySAMLInfo((Node) list.item(i), "", request, response);
				}
			} else {
				session = request.getSession();
				session.setAttribute("SAML", "test1@test7786.onmicrosoft.com");

			}
			session.setAttribute(
					"userx",
					session.getAttribute("SAML")
							.toString()
							.substring(
									0,
									session.getAttribute("SAML").toString()
											.indexOf("@")));
			session.setAttribute(
					"tenantId",
					session.getAttribute("SAML")
							.toString()
							.substring(
									session.getAttribute("SAML").toString()
											.indexOf("@") + 1));
			/* Ganesh - Start */
			// String roleName = request.getParameter("rolename");
			List<String> userRoles = new ArrayList<String>();
			String roleName = request.getParameter("user_role");
			System.out.println("current role:" + roleName);
			if (roleName == null) {

			} else {
				session.setAttribute("SelectedItem", roleName);
			}
			/* Ganesh - End */

			response.setContentType("text/html");
			String uname = session
					.getAttribute("SAML")
					.toString()
					.substring(
							0,
							session.getAttribute("SAML").toString()
									.indexOf("@"));
			session.setAttribute("userName", uname);
			session.setAttribute("UserId", uname);
			String admina = "";
			// response.sendRedirect("test.jsp");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection(
						"jdbc:mysql://robocodedb.cloudapp.net:3306/Role",
						"naren", "naren");
				String sqle = "SELECT * FROM Pages where HierarchicalRoleID in ";
				String sqll = "SELECT HierarchicalRoleId from UserHierarchicalRole Where UserId = '"
						+ session.getAttribute("userName") + "'";
				PreparedStatement ppl = (PreparedStatement) conn
						.prepareStatement(sqll);
				ResultSet rl = (ResultSet) ppl.executeQuery();
				String rol = "";

				while (rl.next()) {
					userRoles.add(rl.getString("HierarchicalRoleId"));
					rol = rol + rl.getString("HierarchicalRoleId");
				}
				session.setAttribute("rol", rol);
				System.out.println("roles for the user "
						+ session.getAttribute("userName").toString() + " is"
						+ Arrays.toString(userRoles.toArray()));

				String pages = "";

				if (roleName != null) {
					inclause = inclause + "('" + roleName + "',";
					trecurs(conn, roleName);
					inclause = inclause.substring(0, inclause.length() - 1)
							+ ")";
					// System.out.println("Tree traversal" + inclause);
					sqle = sqle + inclause;

				} else {
					session.setAttribute("roleaccess", "");
					inclause = inclause + "('')";
					sqle = sqle + inclause;
				}
				PreparedStatement pps = (PreparedStatement) conn
						.prepareStatement(sqle);
				ResultSet rs = (ResultSet) pps.executeQuery();
				while (rs.next()) {
					// System.out.println(rs.getString("servicename"));
					pages = pages + rs.getString("servicename") + "|";
				}
				// close connection
				System.out.println(pages);
				if (pages != null) {
					session.setAttribute("roleaccess", pages);
				} else {
					session.setAttribute("roleaccess", "");
				}
				if (userRoles != null) {
					session.setAttribute("userRole", userRoles);
				} else {
					session.setAttribute("userRole", "");
				}

				conn.close();
			} catch (Exception ex) {
				System.out.println("Exception" + ex.toString());
			}

			response.sendRedirect("welcome.jsp");

			/* Ganesh - End */
			/*
			 * RequestDispatcher rd = request
			 * .getRequestDispatcher("welcome.jsp"); rd.forward(request,
			 * response);
			 */
			inclause = "";

		} catch (Exception e) {
			session.setAttribute("SAML", e.toString());
			// response.sendRedirect("test.jsp");
			System.out.println("Exception encountered.");
			// e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (userDTO == null) {
			userDTO = new UserDTO();
		}

		if (request.getParameter("username") != null) {
			// String userName = request.getParameter("username");
			// String password = request.getParameter("userpass");
			String userName = "arun";
			String password = "arun";
			session = request.getSession();
			userDTO.setUserName(userName);
			userDTO.setPassWord(password);
			session.setAttribute("userx", userName);
		}

		/* Ganesh - Start */
		// String roleName = request.getParameter("rolename");
		List<String> userRoles = new ArrayList<String>();
		String roleName = request.getParameter("user_role");
		System.out.println("current role:" + roleName);
		if (roleName == null) {

		} else {
			session.setAttribute("SelectedItem", roleName);
		}
		/* Ganesh - End */

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// HttpSession session = request.getSession();

		// ObjectMapper mapper = new ObjectMapper();
		// System.out.println(mapper.writeValueAsString(userDTO));

		LoginRestClientService loginRestClientService = new LoginRestClientService();
		List<UserDTO> user_DTO = loginRestClientService.validateUser(userDTO);

		// HttpSession sessions = request.getSession();
		if (user_DTO.size() == 1) {
			if (!(user_DTO.get(0).getUserName().equalsIgnoreCase("Admin"))) {
				session.setAttribute("userDTO", user_DTO.get(0));

				session.setAttribute("userName", user_DTO.get(0).getUserName());
				session.setAttribute("UserId", user_DTO.get(0).getUserId());
				/* Ganesh - Start */
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = (Connection) DriverManager.getConnection(
							"jdbc:mysql://robocodedb.cloudapp.net:3306/Role",
							"naren", "naren");
					String sqle = "SELECT * FROM Pages where HierarchicalRoleID in ";
					String sqll = "SELECT HierarchicalRoleId from UserHierarchicalRole Where UserId = '"
							+ user_DTO.get(0).getUserName() + "'";
					PreparedStatement ppl = (PreparedStatement) conn
							.prepareStatement(sqll);
					ResultSet rl = (ResultSet) ppl.executeQuery();
					while (rl.next()) {
						userRoles.add(rl.getString("HierarchicalRoleId"));
					}

					System.out.println("roles for the user "
							+ user_DTO.get(0).getUserName() + " is"
							+ Arrays.toString(userRoles.toArray()));

					String pages = "";
					inclause = inclause + "('" + roleName + "',";

					trecurs(conn, roleName);
					inclause = inclause.substring(0, inclause.length() - 1)
							+ ")";
					System.out.println("Tree traversal" + inclause);
					sqle = sqle + inclause;
					PreparedStatement pps = (PreparedStatement) conn
							.prepareStatement(sqle);
					ResultSet rs = (ResultSet) pps.executeQuery();
					while (rs.next()) {
						// System.out.println(rs.getString("servicename"));
						pages = pages + rs.getString("servicename") + "|";
					}
					// close connection
					System.out.println(pages);
					session.setAttribute("roleaccess", pages);
					session.setAttribute("userRole", userRoles);
					conn.close();
				} catch (Exception ex) {
					System.out.println("Exception" + ex.toString());
				}
				/* Ganesh - End */
				response.sendRedirect("welcome.jsp");
				/*
				 * RequestDispatcher rd = request
				 * .getRequestDispatcher("welcome.jsp"); rd.forward(request,
				 * response);
				 */
			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Username or password doesnt exist");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}

		inclause = "";
		out.close();
	}

	private void trecurs(Connection c, String rl) {
		try {// TODO Auto-generated method stub
			String sqlr = "SELECT * FROM Hierarchy where ParentHierarchicalRoleId = '"
					+ rl + "'";
			PreparedStatement ppr = (PreparedStatement) c
					.prepareStatement(sqlr);
			ResultSet rr = (ResultSet) ppr.executeQuery();
			while (rr.next()) {
				inclause = inclause + "'" + rr.getString("HierarchicalRoleId")
						+ "',";
				trecurs(c, rr.getString("HierarchicalRoleId"));
			}
		} catch (Exception ex) {
			System.out.println("Exception" + ex.toString());
		}
	}
}