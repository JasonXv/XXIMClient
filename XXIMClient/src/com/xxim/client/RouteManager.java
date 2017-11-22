package com.xxim.client;

import com.xxim.client.contants.ConnectionContant;
import com.xxim.client.entity.AbstractClient;
import com.xxim.client.transport.LogicConnection;
import com.xxim.client.transport.handler.IMessageHandler;
import com.xxim.client.transport.handler.impl.ClientMessageHandler;
import com.xxim.client.transport.impl.ClientSocketConnection;
import com.xxim.client.transport.impl.SocketClientHandler;

public class RouteManager {
	private final static RouteManager xximClientRouteManage = new RouteManager();
	
	private LogicConnection connection;
	private IMessageHandler messageHandler;
	private AbstractClient client;
	private SocketClientHandler clientHandler;
	
	private RouteManager() {}
	public static RouteManager getInstance() {
		return xximClientRouteManage;
	}
	/**
	 * 建立连接
	 * @param socketClient
	 * @param serverIp
	 * @param serverPort
	 */
	public LogicConnection connect() {
		if(connection == null){
			connection = new ClientSocketConnection();
		}
		client = connection.connect(ConnectionContant.SERVIER_IP, ConnectionContant.SERVIER_PORT);
		return connection;
	}
	
	public SocketClientHandler getMessageHandler(){
		if(messageHandler == null){
			messageHandler = new ClientMessageHandler();
		}
		if(connection == null){
			connection = new ClientSocketConnection();
		}
		if(clientHandler == null){
			clientHandler = new SocketClientHandler(messageHandler, client);
		}
		return clientHandler;
	}
}
