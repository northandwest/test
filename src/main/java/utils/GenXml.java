package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class GenXml {

	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<bean id=\"masterDataSource_1\" parent=\"parentDataSource\">")
				.append("<property name=\"url\" value=\"${icomment.jdbc.url.wr_1}\" />")
				.append("<property name=\"username\" value=\"${icomment.jdbc.username.wr_1}\" />")
				.append("<property name=\"password\" value=\"${icomment.jdbc.password.wr_1}\" />").append("</bean>");
		
		StringBuffer xml = new StringBuffer();
		for(int i = 1; i <= 8 ;i ++)
		{
			String temp = sb.toString();
			temp = temp.replace("1", String.valueOf(i));
			xml.append(temp).append("").append("\r\n");
		}
		
		FileUtils.write(new File("d:/temp/datasource.xml"), xml.toString());
	}

}
