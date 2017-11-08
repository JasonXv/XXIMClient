package com.imclient.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.imclient.protocol.MsgBody;
import com.imclient.thread.ClientReaderThread;

public class IMClient {
	/**
	 * 启动客户端
	 */
	public static void start() {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9999);
			new Thread(new ClientReaderThread(socket)).start();
			//socket输出流
			OutputStream out = socket.getOutputStream();
			byte[] bytes = new byte[1024];
			
			Scanner scanner = new Scanner(System.in);
			BufferedReader re = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
			String message = null;
			while(!(message=re.readLine()).contains("over")) {
				//MessageBody
				MsgBody.MessageBody.Builder msgBuilder = MsgBody.MessageBody.newBuilder();
				msgBuilder.setFromUser("1");
				msgBuilder.setToUser("1");
				msgBuilder.setMsgContent("hello");
				MsgBody.MessageBody msgBody = msgBuilder.build();
				byte[] byteArray = msgBody.toByteArray();
				out.write(byteArray);
				out.flush();
				out.close();
				
//				MsgBody.MessageBody parseFrom = MsgBody.MessageBody.parseFrom(byteArray);
//				System.out.println(parseFrom.getFromUser()+":"+parseFrom.getMsgContent());
			}
			re.close();
			socket.close();
			
		} catch (Exception e) {
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
