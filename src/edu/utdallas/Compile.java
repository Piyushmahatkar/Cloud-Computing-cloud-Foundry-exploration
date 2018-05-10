package edu.utdallas;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

/**
 * Created by danei on 5/26/2016.
 */
public class Compile{
//	static Map<String, byte[]> compiledClass=new HashMap<>();
//	static Map<String, Map> path=new HashMap<>();
	
	public static byte[] compile(String packageName,String className,String src,String webRoot)throws Exception{
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnosticCollector=new DiagnosticCollector<>();
		InMemoryFileManager fileManager=new InMemoryFileManager(compiler.getStandardFileManager(diagnosticCollector,null,null));
		List<String> options=new ArrayList<String>();    // add -cp classpath option, etc here
		options.addAll(Arrays.asList("-classpath",webRoot));
		JavaFileObject java_file=new SimpleJavaFileObj(packageName+"."+className,src);
		Iterable<? extends JavaFileObject> unit=Arrays.asList(java_file);
		JavaCompiler.CompilationTask task=compiler.getTask(null,fileManager,diagnosticCollector,options,null,unit);
		boolean success=task.call();
		if(!success){
			StringBuilder sb=new StringBuilder();
			for(Diagnostic d : diagnosticCollector.getDiagnostics()){
				sb.append(d.getCode());
				sb.append(d.getKind());
				sb.append(d.getPosition());
				sb.append(d.getStartPosition());
				sb.append(d.getEndPosition());
				sb.append(d.getSource());
				sb.append(d.getMessage(null));
			}
			throw new Exception(sb.toString());
		}else{
			return fileManager.getClassBytes();
		}
	}

//	protected void doGet(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
//		ByteArrayOutputStream baos=new ByteArrayOutputStream();
//		PrintStream ps=new PrintStream(baos);
//		try{
//			JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
//			DiagnosticCollector<JavaFileObject> diagnosticCollector=new DiagnosticCollector<>();
//			InMemoryFileManager fileManager=new InMemoryFileManager(compiler.getStandardFileManager(diagnosticCollector,null,null));
//			String java_src=request.getParameter("src");
//			String java_name=request.getParameter("name");
//			List<String> options=new ArrayList<String>();    // add -cp classpath option, etc here
//			options.addAll(Arrays.asList("-classpath",getServletContext().getRealPath("/")+"robocode.jar"));
//			JavaFileObject java_file=new SimpleJavaFileObj(java_name,java_src);
//			Iterable<? extends JavaFileObject> unit=Arrays.asList(java_file);
//			JavaCompiler.CompilationTask task=compiler.getTask(null,fileManager,diagnosticCollector,options,null,unit);
//			boolean success=task.call();
//			ps.println("Compilation "+(success ? "is successful." : "failed!"));
//			for(Diagnostic d : diagnosticCollector.getDiagnostics()){
//				ps.println(d.getCode());
//				ps.println(d.getKind());
//				ps.println(d.getPosition());
//				ps.println(d.getStartPosition());
//				ps.println(d.getEndPosition());
//				ps.println(d.getSource());
//				ps.println(d.getMessage(null));
//			}
//			if(success){
//				compiledClass.put(java_name,fileManager.getClassBytes());
//				String[] full_path=java_name.split("\\.");
//				Map<String, Map> current=path;
//				for(int i=0;i<full_path.length-1;++i){
//					if(!current.containsKey(full_path[i]))
//						current=current.get(full_path[i]);
//					else{
//						Map<String, Map> next=new HashMap<>();
//						current.put(full_path[i],next);
//						current=next;
//					}
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace(ps);
//		}
//		request.setAttribute("log",baos.toString(Charset.defaultCharset().name()));
//		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/result.jsp");
//		dispatcher.forward(request,response);
//	}
}
