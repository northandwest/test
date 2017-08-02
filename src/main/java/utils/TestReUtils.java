package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.xiaoleilu.hutool.util.ReUtil;

public class TestReUtils {

	public static void main(String[] args) {
			List<String> list = new ArrayList<String>();
	        String url ="C:\\Users\\wujiang3\\git\\comment-data-base_new\\comment-data-base-web\\src\\main\\resources\\profile\\dev";
	        String url1= "C:\\Users\\wujiang3\\git\\clubicesoa\\sns-club-soa-web\\src\\main\\resources\\props";
	        try {
				String readFileToString = FileUtils.readFileToString(new File(url+"\\important.properties"));
		        List<String> resultFindAll = ReUtil.findAll("192.168.166.30:3359/.*?\\?", readFileToString, 0, new ArrayList<String>());
		        for(String t : resultFindAll)
		        {
		        	if(!list.contains(t))
		        	{
		        		list.add(t);
			        	System.out.println(t);
		        	}
		        }
	        } catch (IOException e) {
				e.printStackTrace();
			}


	        
	}

}
