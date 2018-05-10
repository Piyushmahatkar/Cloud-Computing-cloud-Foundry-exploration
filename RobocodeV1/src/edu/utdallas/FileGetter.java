package edu.utdallas;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.rowset.serial.SerialBlob;

import DTO.RobotDTO;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Created by danei on 5/26/2016.
 */
@WebServlet("/robotcode.jar")
public class FileGetter extends HttpServlet{
	protected void doPost(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
		doGet(request,response);
	}

	void insert(JarOutputStream jar,Map<String, Map> path,String parent,long time) throws IOException{
		for(String dir : path.keySet()){
			String full_path=parent+dir+"/";
			JarEntry entry=new JarEntry(full_path);
			entry.setTime(time);
			jar.putNextEntry(entry);
			jar.closeEntry();
			insert(jar,path.get(dir),full_path,time);
		}
	}

	protected void doGet(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
		Manifest manifest=new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,"1.0");
		JarOutputStream jos=new JarOutputStream(response.getOutputStream(),manifest);
		long time=System.currentTimeMillis();
		List<RobotDTO> robots=new ArrayList<>();
		
		Connection conn=null;
		try{
			String url = "jdbc:mysql://localhost:3306/robocode";
			String user = "root";
			String password = "password";
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from robot";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				String robotName=rs.getString("robotID");
				String packageName=rs.getString("packageID");
				Blob file=rs.getBlob("file");
				robots.add(new RobotDTO(robotName,packageName,file.getBytes(1, (int) file.length())));
				file.free();
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
		}
		Map<String, Map> path=new HashMap<>();
		Map<String, byte[]> compiledClass=new HashMap<>();
		for(RobotDTO robot:robots){
			String[] full_path=robot.getPackageId().split("\\.");
			Map<String, Map> current=path;
			for(int i=0;i<full_path.length;++i){
				if(!current.containsKey(full_path[i]))
					current=current.get(full_path[i]);
				else{
					Map<String, Map> next=new HashMap<>();
					current.put(full_path[i],next);
					current=next;
				}
			}
			compiledClass.put(robot.getPackageId().replace(".","/")+"/"+robot.getRobotName()+".class",robot.getCompiledCode());
		}
		insert(jos,path,"",time);
		for(String name : compiledClass.keySet()){
			JarEntry entry=new JarEntry(name);
			entry.setTime(time);
			jos.putNextEntry(entry);
			jos.write(compiledClass.get(name));
			jos.closeEntry();
		}
		jos.flush();
		jos.close();
	}
}
