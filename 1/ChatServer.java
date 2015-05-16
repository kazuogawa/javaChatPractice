//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
import java.net.*;
import java.io.*;
//import java.net.*;
import java.util.*;

public class ChatServer {
	ServerSocket ss;
	Vector<ChatHandler> v = new Vector<ChatHandler>();
	public ChatServer()    {
		try{
			ss = new ServerSocket(9001);
			System.out.println("Server start");
			while(true){
				Socket so = ss.accept();
				ChatHandler ch = new ChatHandler(this,so);//Thread make
				ch.start();//Thread Start			
			}
		}catch(Exception ee){
			ee.printStackTrace();
		}
		
	}
	
	public void register(ChatHandler ch){
		v.add(ch);
	}

	public void unregister (ChatHandler ch){
		v.remove(ch);
	}
	public void broadcast (String msg){
		Iterator<ChatHandler> it = v.iterator();
		while(it.hasNext()){
			//ChatHandler ch = (ChatHandler)it.next();
			ChatHandler ch = it.next();
			ch.getPrintWriter().println(msg);
			ch.getPrintWriter().flush();//flush
		}
	}
	public static void main(String[] args){
			new ChatServer();	
	}
}
