package com.xxim.client.transport.impl;

import java.io.IOException;
import java.net.Socket;

import com.xxim.client.entity.AbstractClient;
import com.xxim.client.factory.ClientFactory;
import com.xxim.client.transport.LogicConnection;
import com.xxim.client.transport.handler.IMessageHandler;
import com.xxim.client.utils.StringUtil;

public class ClientSocketConnection extends LogicConnection {
	private Socket socket;
	private String ip;
	private int port;
	private AbstractClient client;
	
	/**
	 * 建立连接
	 */
	@Override
	public AbstractClient connect(String ip,int port) {
		if(socket == null){
			try {
				if(StringUtil.isEmpty(ip) || port <= 0){
					throw new RuntimeException("ip or port error");
				}
				
				socket = new Socket(ip, port);
				this.ip = ip;
				this.port = port;
			} catch (IOException e) {
				e.printStackTrace();
				if(socket == null){
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				return null;
			}
		}
		client = ClientFactory.getClient(socket);
		return client;
	}
	
	/**
	 * 关闭连接
	 */
	@Override
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			socket = null;
		}
	}
	/**
	 * 重启连接
	 */
	@Override
	public void restart(){
		close();
		connect(ip,port);
	}

}
