package robocode;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;


import DTO.RobotDTO;

public class CompileRobocode {
	/*
	 * Domain path is changed according to the running machine
	 */
	// static String domainPath = "C:\\robocode\\";
	// static String domainPath = "..\\..\\WebContent\\jar\\";
	/*
	 * Perfect domain path static String domainPath =
	 * "E:\\cloud\\Cloud_Project_OLD\\WebContent\\jar";
	 */

	static String domainPath = "..//..//WebContent//jar";

	/*
	 * The class path is replaced accroding to the running machine
	 */
	// static String classPath =
	// "javac -classpath \"C:\\Program Files\\Java\\jre7\\lib\\rt.jar;C:\\robocode\\libs\\robocode.jar;\"";
	// static String classPath =
	// "javac -classpath \"C:\\Users\\Sriram Kannan\\Desktop\\Third Sem\\Cloud\\Editor\\Cloud_Project_OLD\\WebContent\\jar\\robocode.jar;\"";

	/*
	 * perfect classpath static String classPath =
	 * "javac -classpath \"E:\\cloud\\Cloud_Project_OLD\\WebContent\\jar\\robocode.jar;\""
	 * ;
	 */
	// static String classPath =
	// "javac -classpath \"\\WebContent\\jar\\robocode.jar;\"";

	public static BufferedReader compileRobocode(RobotDTO robotDTO, String domainPath)
			throws IOException {
		BufferedReader br = null;
		System.out.println("Vandhudu: " + domainPath);
		String jarPath = domainPath + "\\robocode.jar";
		String classPath = "javac -classpath " + jarPath;
		System.out.println("Vandhuduchu: " + classPath);
		boolean compileFlag = false;
		// boolean domainFlag = false;
		String robotPackage = robotDTO.getPackageId();
		/*
		 * Changed from domainPath + "robots\\" to domainPath
		 */
		String robotDomain = domainPath;
		// System.out.println("domain:"+robotDomain);
		File domainPackage = new File(robotDomain, robotPackage);

		if (!(domainPackage.exists())) {
			System.out.println("domain created in the folder");
			domainPackage.mkdir();

		}
		System.out.println("Domain Folder created and outside if condition");
		String fileName = robotDTO.getRobotDescription();
		File file = new File(domainPackage, fileName);

		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(robotDTO.getRobotCode());
			fileWriter.flush();

			// compiling the file using javac
			Runtime rt = Runtime.getRuntime();
			System.out.println(classPath + " " + file.getAbsolutePath());
			Process process = rt.exec(classPath + " " + file.getAbsolutePath());
			InputStream stderr = process.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			br = new BufferedReader(isr);
			String line = null;
			//
			/*while ((line = br.readLine()) != null) {
				System.out.println("<ERROR>");
				System.out.println(line);
				System.out.println("</ERROR>");
			}*/
			//
			int exitVal = process.waitFor();
			System.out.println("Process exitValue: " + exitVal);

			if (exitVal == 0)
				compileFlag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}
}
