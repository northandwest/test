package utils;

public class TestImagesUrl {
	
	
	public static void main(String[] args) {
		String url = "http:////img.jd.co.th//shaidan//jfs//t10//241//0//104991//c525ffc//59b8fcaeNb64e6237.jpg";
		
		isVailImageUrl(url);
	}
    private static  boolean isVailImageUrl(String url ){
    	try{
	    	if( url.indexOf(".")> 0 ){
	    		String imgPrex = url.substring(  url.indexOf(".")+1 ); 
	    		if(imgPrex.indexOf("/")>0){
	    			String domainPrex = imgPrex.substring(0,imgPrex.indexOf("/"));
	        		if(domainPrex.equals("jd.co.th")){ 
	        			return true;
	        		}
	    		} 
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }

}
