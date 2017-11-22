package com.xxim.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient extends AbstractClient{
	private static Socket socket;
	private static SocketClient socketClient;
	private InputStream inputStream;
	private OutputStream outputStream;
	
	private SocketClient(Socket socket) {
		this.socket = socket;
	}
	
	public static SocketClient getInstance(Socket socket){
		if(socket == null){
			throw new RuntimeException("socket is null");
		}
		//懒汉式单例模式
		if(socketClient == null){
			synchronized (SocketClient.class) {
				if(socketClient == null){
					socketClient = new SocketClient(socket);
				}
			}
		}
		return socketClient;
	}
	
	/**
	 * 读取bytes数组中的内容
	 * @param bytes
	 * @return 读取到的总字节数长度，-1为没有读取到
	 * @throws IOException
	 */
	public int read(byte[] bytes) throws IOException{
		if(inputStream == null){
			inputStream = socket.getInputStream();
		}
		return inputStream.read(bytes);
	}
	/**
	 * 读取bytes数组中的内容
	 * @param bytes
	 * @param off 数组起始下标
	 * @param len 长度
	 * @return 读取到的总字节数长度，-1为没有读取到
	 * @throws IOException
	 */
	public int read(byte[] bytes,int off,int len) throws IOException{
		if(inputStream == null){
			inputStream = socket.getInputStream();
		}
		return inputStream.read(bytes,off,len);
	}
	
	/**
	 * 向socket中写入数组
	 * @param bytes
	 * @throws IOException
	 */
	public void write(byte[] bytes) throws IOException{
		if(outputStream == null){
			outputStream = socket.getOutputStream();
		}
		outputStream.write(bytes);
		outputStream.flush();
	}
	/**
	 * 向socket中写入数组
	 * @param bytes
	 * @param off 数组起始下标
	 * @param len 长度
	 * @throws IOException
	 */
	public void write(byte[] bytes,int off,int len) throws IOException{
		if(outputStream == null){
			outputStream = socket.getOutputStream();
		}
		outputStream.write(bytes,off,len);
		outputStream.flush();
	}
}
