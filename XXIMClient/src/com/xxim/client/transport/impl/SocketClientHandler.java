package com.xxim.client.transport.impl;

import java.io.ByteArrayOutputStream;

import com.xxim.client.entity.AbstractClient;
import com.xxim.client.transport.handler.IMessageHandler;
import com.xxim.client.utils.PBMessageUtil;
import com.xxim.codec.PackageBody;
import com.xxim.codec.protobuf.PBMessage;

public class SocketClientHandler {
	private IMessageHandler messageHandler;
	private AbstractClient client;
	
	public SocketClientHandler(IMessageHandler messageHandler,AbstractClient client){
		this.messageHandler = messageHandler;
		this.client = client;
	}

	public void recive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int len = -1;
					byte[] bytes = new byte[1024];
					ByteArrayOutputStream baos = null;
					while((len=client.read(bytes)) != -1) {
						baos = new ByteArrayOutputStream();
						baos.write(bytes, 0, len);
						messageHandler.reciveMessage(client, baos.toByteArray());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 写数据
	 * @param dstid
	 * @param message
	 * @throws Exception 
	 */
	public void send(String dstid,String message) throws Exception  {
		PBMessage.pbmessage pb = PBMessageUtil.createPBMessage(message, dstid);
		messageHandler.sendMessage(client, PackageBody.encode(pb));
	}

}
