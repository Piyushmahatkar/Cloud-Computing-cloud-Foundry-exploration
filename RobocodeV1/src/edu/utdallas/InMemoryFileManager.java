package edu.utdallas;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import java.io.IOException;
import java.security.SecureClassLoader;

/**
 * Created by danei on 5/27/2016.
 */
public class InMemoryFileManager extends ForwardingJavaFileManager{
	private SimpleJavaClassObj classObj;

	public InMemoryFileManager(StandardJavaFileManager standardJavaFileManager){
		super(standardJavaFileManager);
	}

	@Override
	public JavaFileObject getJavaFileForOutput(Location location,String className,Kind kind,FileObject sibling) throws IOException{
		classObj=new SimpleJavaClassObj(className,kind);
		return classObj;
	}

	@Override
	public ClassLoader getClassLoader(Location location){
		return new SecureClassLoader(){
			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException{
				byte[] b=classObj.getBytes();
				return super.defineClass(name,b,0,b.length);
			}
		};
	}

	public byte[] getClassBytes(){
		return classObj.getBytes();
	}
}
