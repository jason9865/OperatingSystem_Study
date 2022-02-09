package java_lang.chap04_04_echo_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{

	private Socket client;

	public ServerThread(Socket client) {
		this.client = client;
	}



	@Override
	public void run() {
		BufferedReader br=null;
		PrintWriter pw = null;
		try {
			String connIp = client.getInetAddress().getHostAddress();
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    	pw = new PrintWriter(client.getOutputStream());
	    	
	    	// Ŭ���̾�Ʈ���� ���� ���� ����
	    	String msg = br.readLine();
	    	
	    	// Ŭ���̾�Ʈ���� ���� ���� ���
	    	System.out.println(connIp + " : " + msg);
	    	
	    	// Ŭ���̾�Ʈ�� ���ڿ� ����
	    	pw.println(msg);
	    	pw.flush();
	    	client.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(br!=null)
				{
					br.close();
				}
				if(pw!=null)
				{
					pw.close();
				}
				if(client!=null)
				{
					client.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	

}
