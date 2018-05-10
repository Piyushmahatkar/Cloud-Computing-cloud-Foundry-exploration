package robocode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RobocodeCompile {
	public static void CompileFile(String rbtName, String pkgID){
		// TODO Auto-generated method stub
		try
		{
			System.out.println("Caling command prompt commands");
  			System.out.println("--------------------------------");
			Runtime rt = Runtime.getRuntime();
			
			Process ps, ps1 = null;
			
			
			
				//ps = rt.exec("cmd /c java -cp C:/robocode/libs/robocode.jar robocode.Robocode -nodisplay");
				//ps1 = rt.exec("cmd /c java -cp C:/robocode/compilers/ecj.jar org.eclipse.jdt.internal.compiler.batch.Main -deprecation -g -source 1.6 -encoding UTF-8 -classpath \"C:/Program Files/Java/jre1.8.0_25/lib/rt.jar\";C:/robocode/libs/robocode.jar C:/robocode/robots/"+pkgID+"/"+rbtName+".java");
				ps = rt.exec("sh /c java -cp /home/c2-4/codebase/cloud_code/web/common/robocode.jar robocode.Robocode -nodisplay");
				ps1 = rt.exec("sh /c java -cp C:/robocode/compilers/ecj.jar org.eclipse.jdt.internal.compiler.batch.Main -deprecation -g -source 1.6 -encoding UTF-8 -classpath \"/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.144-0.b01.el7_4.x86_64/jre/lib/rt.jar\";/home/c2-4/codebase/cloud_code/web/common/robocode.jar /home/c2-4/codebase/cloud_code/web/common/RoboCode-Applet/fwdforce_com/appweb/src/"+pkgID+"/"+rbtName+".java" );
																																								
			BufferedReader in = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
            String line = null;  
            while ((line = in.readLine()) != null) {  
                System.out.println(line);
            }
            
            BufferedReader in1 = new BufferedReader(new InputStreamReader(ps1.getInputStream()));  
            String line1 = null;  
            while ((line1 = in1.readLine()) != null) {  
                System.out.println(line1);
            }
            
			
  			System.out.println("Completed command prompt commands");
  			System.out.println("--------------------------------");
  			
		}
		catch(Exception e)
		{
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		}

	}
	public static void FileCompile(String rbtName, String pkgID)
	{
		try
		{
			System.out.println("Caling command prompt commands");
  			System.out.println("--------------------------------");
			Runtime rt = Runtime.getRuntime();
			
			Process ps, ps1 = null;
			
			
			
				ps = rt.exec("sh /c java -cp /home/c2-4/codebase/cloud_code/web/common/robocode.jar robocode.Robocode -nodisplay");
				ps1 = rt.exec("sh /c javac -classpath \"/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.144-0.b01.el7_4.x86_64/lib/jre/rt.jar\";\"/home/c2-4/codebase/cloud_code/web/common/robocode.jar\";\"/home/c2-4/codebase/cloud_code/web/common/RoboCode-Applet/fwdforce_com/appweb/src/"+pkgID+"\"/home/c2-4/codebase/cloud_code/web/common/RoboCode-Applet/fwdforce_com/appweb/src/"+pkgID+"/"+rbtName+".java" );
				//-classpath "C:/Program Files (x86)/Java/jre1.8.0_66/lib/rt.jar";C:/robocode/libs/robocode.jar;C:/robocode/robots/Nidhi
																																								
			BufferedReader in = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
            String line = null;  
            while ((line = in.readLine()) != null) {  
                System.out.println(line);
            }
            
            BufferedReader in1 = new BufferedReader(new InputStreamReader(ps1.getInputStream()));  
            String line1 = null;  
            while ((line1 = in1.readLine()) != null) {  
                System.out.println(line1);
            }
            
			
  			System.out.println("Completed command prompt commands");
  			System.out.println("--------------------------------");
  			
		}
		catch(Exception e)
		{
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		}
		
	}


}
