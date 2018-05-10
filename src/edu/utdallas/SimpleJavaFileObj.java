package edu.utdallas;


import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by danei on 5/26/2016.
 */
public class SimpleJavaFileObj extends SimpleJavaFileObject{
	private final String code;

	public SimpleJavaFileObj(String name,String code){
		super(URI.create("string:///"+name.replace('.','/')+Kind.SOURCE.extension),Kind.SOURCE);
		this.code=code;
	}

	public CharSequence getCharContent(boolean ignoreEncodingErrors){
		return code;
	}
}
