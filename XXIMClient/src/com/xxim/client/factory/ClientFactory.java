package com.xxim.client.factory;

import java.net.Socket;

import com.xxim.client.entity.AbstractClient;
import com.xxim.client.entity.SocketClient;

public class ClientFactory {
	/**
	 * 创建client对象
	 * @param socket
	 * @return
	 */
	public static AbstractClient getClient(Socket socket){
		return SocketClient.getInstance(socket);
	}
}
