package edu.utdallas;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by danei on 5/27/2016.
 */
public class SimpleJavaClassObj extends SimpleJavaFileObject{
	public SimpleJavaClassObj(String name,Kind kind){
		super(URI.create("string:///"+name.replace('.','/')+kind.extension),kind);
	}

	ByteArrayOutputStream baos=new ByteArrayOutputStream();

	public byte[] getBytes(){
		return baos.toByteArray();
	}

	@Override
	public OutputStream openOutputStream() throws IOException{
		return baos;
	}
}
