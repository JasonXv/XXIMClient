package com.xxim.client.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.xxim.client.RouteManager;
import com.xxim.client.transport.LogicConnection;
import com.xxim.client.transport.impl.SocketClientHandler;

public class MainTest {

	public static void main(String[] args) {
		//获得总控制器
		RouteManager route = RouteManager.getInstance();
		//建立连接
		LogicConnection connect = route.connect();
		//消息管理器
		SocketClientHandler clientHandler = route.getMessageHandler();
		
		//接收消息
		clientHandler.recive();
		
		//发送消息
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		String [] lineArray  = null;
		try {
			while(!(line=reader.readLine()).contains("over")){
				lineArray = line.split(",");
				clientHandler.send(lineArray[0], lineArray[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			connect.close();
		}
	}
}
