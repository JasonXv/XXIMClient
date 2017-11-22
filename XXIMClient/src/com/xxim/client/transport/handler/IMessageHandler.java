package com.xxim.client.transport.handler;

import com.xxim.client.entity.AbstractClient;

public interface IMessageHandler {
	/**
	 * 接收消息
	 */
	public void reciveMessage(AbstractClient client,byte[] bytes) throws Exception;
	/**
	 * 发送消息
	 */
	public void sendMessage(AbstractClient client,byte[] bytes) throws Exception;
}
