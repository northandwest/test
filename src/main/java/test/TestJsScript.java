package test;

import java.util.Date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJsScript {

	public static void main(String[] args) {
		try {
			new TestJsScript().invokeExpression();
			
			new TestJsScript().invokeFunction();
			
			new TestJsScript().invokeFunctionWithParam();

			new TestJsScript().inject();
		} catch (ScriptException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public void invokeExpression() throws ScriptException
	{
	   ScriptEngineManager manager = new ScriptEngineManager();
	   ScriptEngine engine = manager.getEngineByName("js");
	   String js = "function alert(obj){ return obj+' is ok'}";
	   
	     engine.eval(js);
	     Invocable invocable = (Invocable) engine;
	     String result;
		try {
			result = (String) invocable.invokeFunction("alert", "china No 1");
			 System.out.println(result);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	     
	}

	
	public void invokeFunction() throws ScriptException, NoSuchMethodException
	{
	   ScriptEngineManager manager = new ScriptEngineManager();
	   ScriptEngine engine = manager.getEngineByName("js");
	   String js = "function welcom(){return 'welcom jd';}";
	   engine.eval(js);
	   Invocable invocable = (Invocable) engine;
	   String result = (String) invocable.invokeFunction("welcom");
	   System.out.println(result);
	}
	
	  public void invokeFunctionWithParam() throws ScriptException, NoSuchMethodException
	  {
	     ScriptEngineManager manager = new ScriptEngineManager();
	     ScriptEngine engine = manager.getEngineByName("js");
	     String js = "function welcom(name){return 'welcom ' + name;}";
	     engine.eval(js);
	     Invocable invocable = (Invocable) engine;
	     String result = (String) invocable.invokeFunction("welcom", "china No 1");
	     System.out.println(result);
	  }
	  
	  public void inject() throws ScriptException, NoSuchMethodException
	  {
	     ScriptEngineManager manager = new ScriptEngineManager();
	     ScriptEngine engine = manager.getEngineByName("js");
	     Date date = new Date();
	     System.out.println(date.getTime());
	     engine.put("date", date);
	     String js = "function getTime(){return date.getTime();}";
	     engine.eval(js);
	     Invocable invocable = (Invocable) engine;
	     Long result = (Long) invocable.invokeFunction("getTime");
	     System.out.println(result);
	  }
	  
	  public void runThread() throws ScriptException, NoSuchMethodException
	  {
	     ScriptEngineManager manager = new ScriptEngineManager();
	     ScriptEngine engine = manager.getEngineByName("js");
	     engine.put("out", System.out);
	     String js = "var obj=new Object();obj.run=function(){out.println('thread...')}";
	     engine.eval(js);
	     Object obj = engine.get("obj");
	     Invocable inv = (Invocable) engine;
	     Runnable r = inv.getInterface(obj, Runnable.class);
	     Thread t = new Thread(r);
	     t.start();
	  }
}
