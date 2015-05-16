//import java.io.*;
//import java.net.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;


public class ChatHandler extends Thread {
	private ChatServer cs;
	private Socket so;
	private BufferedReader br;
	private PrintWriter pw;
	private int zz = 0;//

	public ChatHandler(ChatServer cs, Socket so) throws java.io.IOException {
			this.cs = cs;
			this.so = so;
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));;
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));
	}

	public void run()  {
		String name = null;
		try{
		name = br.readLine(); //EEEEEEE
		if(name.endsWith("/web")){//EEEEEEE
			name = name.split("/web")[0];//EEEEEEE
			zz = 1;//EEEEEEE
		}else{//EEEEEEE
			cs.register(this);//백터 등록
			name = java.net.URLDecoder.decode(name, "UTF-8");
			cs.broadcast(name+ "님이 가입하셨습니다.");
		}//EEEEEEE

		while(true){
			String data = br.readLine(); //EEEEEEE
			if(data==null || data.toLowerCase().equals("quit"))
				break;

			cs.broadcast("[" + name + "]" + data);//EEEEEEE
			
		}
		}catch(IOException io){
			io.printStackTrace();
		}

		cs.unregister(this);//EEEEEEE
		if(zz!=1){//EEEEEEE
			//String ab = name;
			//String c1 = name;
			//String c2 = name;
			//String c3 = name;
			//String c4 = name;
			try{
			name = java.net.URLDecoder.decode(name, "UTF-8");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			//ab = java.net.URLDecoder.decode(ab);
			cs.broadcast(name+ "Movie Page");
		}//EEEEEEE
		try{
		br.close();//EEEEEEE
		pw.close();
		so.close();
		}catch(Exception ee){
			ee.printStackTrace();
		}

		
	}//run()
	public void a(String line){
		int byteLength = 0;
		int maxLength = 240;
		for(int index = 0 ; index < line.length()-1 ; index++){
		  if(isKorean(line.substring(index, index+1))){
			//EEEEEEE
			if(byteLength + 3 > maxLength){
			  break;
			}
			byteLength += 3;
		  }else{
			//EEEEEEE
			if(byteLength + 1 > maxLength){
			  break;
			}
			byteLength += 1;
		  }
		}//end for
	}
	public boolean isKorean(String data){
	  java.util.regex.Pattern p = java.util.regex.Pattern.compile("");//EEEEEEE2
	  java.util.regex.Matcher m = p.matcher(data);
	  if (m.matches()) {
		  return true;
	  } else {
	//      System.out.println("not match!");
	   return false;
	  }
	}
	public PrintWriter getPrintWriter(){
		return pw;
	}

}
