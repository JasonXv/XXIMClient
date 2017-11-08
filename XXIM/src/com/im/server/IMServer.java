package com.im.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.im.constants.ServerConstant;
import com.im.thread.ClientWriterThread;

public class IMServer {
	//线程池
	private static ExecutorService exec;
	
	private static ServerSocket serverSocket;
	//key->socketid,val->socket
	public static Map<String, Socket> imSocketMap;
	static {
		if(imSocketMap == null){
			imSocketMap = new HashMap<String, Socket>();
		}
		exec = Executors.newCachedThreadPool();
	}
	
	public static int i = 0;
	/**
	 * 启动服务
	 */
	public static void start(){
		try {
			if(serverSocket == null){
				serverSocket = new ServerSocket(ServerConstant.IM_PORT);
			}
			while(true){
				getClientReader();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 停止服务
	 */
	public static void stop() {
		for(Map.Entry<String, Socket> entry:imSocketMap.entrySet()) {
			Socket socket = entry.getValue();
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 启动客户端reader线程
	 * @throws Exception
	 */
	private synchronized static void getClientReader() throws Exception{
		Socket clientSocket = serverSocket.accept();
		//给当前socket指定唯一id
		imSocketMap.put((++i)+"", clientSocket);
		exec.execute(new ClientWriterThread(clientSocket,i+""));
	}
}
