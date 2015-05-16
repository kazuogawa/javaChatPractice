import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


public class ChatClient extends JFrame implements ActionListener, Runnable {
	JTextArea output;
	JTextField input;
	JButton sendB;
	
	String serverIP, name;

	Socket so = null;

	BufferedReader br;
	PrintWriter pw;

	Thread t;

	public ChatClient(){
	
		output = new JTextArea();
		JScrollPane op = new JScrollPane(output);
		
		output. setFont(new Font("EEEEEEE",Font.BOLD,15));
		output.setForeground(new Color(0,0,255));
		output.setEditable(false);
		
		input = new JTextField(20);
		input. setFont(new Font("EEEEEEE",Font.BOLD,12));

		
		sendB = new JButton("EEEEEEE");
		
		JPanel b = new JPanel();
		b.setLayout(new BorderLayout());

		//a.add(output);

		//JPanel a = new JPanel();
		b.add("Center",input);
		b.add("East",sendB);


		this.getContentPane().add("Center",op);
		this.getContentPane().add("South",b);

		setBounds(100,100,500,500);
		setVisible(true);
		setTitle("aa");
	//	setDefaultCloseOperation(EXIT_ON_CLOSE);	//EEEEEEE





		//Server IP  Input
		//JOptionPane aaa = new JOptionPane();
		serverIP = JOptionPane.showInputDialog(this,"Server IP ","ServerIP",JOptionPane.QUESTION_MESSAGE) ;

		if(serverIP.trim().length() < 1){ // <1
			serverIP = "14.40.58.189";
		}
		//-- error
		//Nick Name
		name = JOptionPane.showInputDialog(this,"NickName","Talk",JOptionPane.QUESTION_MESSAGE) ;
		
		if(name.trim().length() < 1) name="noname"; 

		//Socket
		try{
		so = new Socket(serverIP,9001);

			//br = new OutputStreamWriter(new PrintWriter(Socket.getOutputStream(so)));//BufferedReader
			//br = Socket.getOutputStream(new BufferedReader(new PrintWriter(so)));
			br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())));
			
			pw.println(name);//EEEEEEE
			pw.flush();//EEEEEEE

			//Thread
			t=new Thread(this); //EEEEEEE
			t.start();//EEEEEEE
			


			}catch(Exception ee){
			ee.printStackTrace();
			}
			



			//pw = //PrintWriter
		//Event
		input.addActionListener(this);
		sendB.addActionListener(this);
	
			this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e) {
			pw.println("quit");
			pw.flush();
			try{
			br.close();
			pw.close();
			so.close();
			
			}catch(Exception eeee){
				eeee.printStackTrace();
			}
		System.exit(0);
		}
		}); 

	}//ChatClient()

	public void actionPerformed(ActionEvent e){
		String data=null;
		data = input.getText();//EEEEEEE
		pw.println(data);//EEEEEEE
		pw.flush();
		input.setText("");//EEEEEEE

	}//ActioonEvent
	public void run(){
		String data=null;
		while(true){
		try{
			data = br.readLine();
			if(data==null || data.toLowerCase()  .equals("quit")){
				br.close();
				pw.close();
				so.close();

				System.exit(0);
			}
		//data = br.readLine();//EEEEEEE
		output.append(data+"\n");//EEEEEEE

		int pos = output.getText().length();
		output.setCaretPosition(pos);

		}catch(Exception ee){
			ee.printStackTrace();
		}

		}
	}
	public static void main(String[] args){
		new ChatClient();
	}
}