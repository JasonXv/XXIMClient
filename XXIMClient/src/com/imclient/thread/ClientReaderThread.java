package com.imclient.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReaderThread implements Runnable {
	private Socket socket;
	
	public ClientReaderThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			int len = -1;
            byte[] bytes = new byte[1024];
			while((len=in.read(bytes)) != -1) {
				System.out.println(new String(bytes,0,len));
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
