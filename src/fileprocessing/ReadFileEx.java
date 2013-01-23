package fileprocessing;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ReadFileEx {
	
	private static Logger logger = Logger.getLogger(ReadFileEx.class);
	
	public static void setLog() {
		logger.setLevel(Level.DEBUG);
		BasicConfigurator.configure();
	}
	
	public static void main(String... args) throws IOException {
		
		setLog(); 
		
		//1. getClass().getResource와 2. getClass().getClassLoader().getResource()의 차이
		//1은 class의 위치이고, 2는 root의 위치이다.
		//http://stackoverflow.com/questions/3803326/this-getclass-getclassloader-getresource-and-nullpointerexception
		String fileName = "example";
		System.out.println(fileName);
		System.out.println(new ReadFileEx().getClass().getResource(fileName));
	    System.out.println(new ReadFileEx().getClass().getClassLoader().getResource(fileName));
	    System.out.println(System.getProperty("user.dir")+"/"+fileName);
		
		//case 1 : getResource를 이용한 파일 읽기
				URL url = ReadFileEx.class.getClassLoader().getResource("resource"+File.separator+"example");
				BufferedReader br2 = new BufferedReader(new FileReader(url.getPath()));
				String contents2 = null;
				
				while(null != (contents2 = br2.readLine()))
					logger.debug(contents2);
				
				logger.debug("getResource: " + url.getPath());

				
				//case 2 : getResourceAsStream을 이용한 파일읽기
				InputStream inputstream = ReadFileEx.class.getClassLoader().getResourceAsStream("resource/example");
				BufferedReader br = new BufferedReader (new InputStreamReader(inputstream));
				
				String contents = null;
				while(null != (contents = br.readLine()))
					logger.debug(contents);
				
				
				//case 3 : System.getProperty를 이용한 파일읽기
				logger.debug("user.dir: " + System.getProperty("user.dir"));
				
				String filename = System.getProperty("user.dir") + File.separator + "resource" + File.separator +"example";
				File file = new File(filename);
				BufferedReader br3 = new BufferedReader(new FileReader(file));
				String contents3 = null;
				
				while(null != (contents3 = br3.readLine()))
					logger.debug(contents3);
	}
}
