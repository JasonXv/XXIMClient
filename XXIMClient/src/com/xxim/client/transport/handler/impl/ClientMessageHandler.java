package com.xxim.client.transport.handler.impl;

import java.io.IOException;

import com.xxim.client.entity.AbstractClient;
import com.xxim.client.transport.handler.IMessageHandler;

public class ClientMessageHandler implements IMessageHandler {

	@Override
	public void reciveMessage(AbstractClient client, byte[] bytes) {
		System.out.println(new String(bytes));
	}

	@Override
	public void sendMessage(AbstractClient client, byte[] bytes) throws IOException {
		
		client.write(bytes);
	}

}
