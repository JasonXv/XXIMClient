package com.im.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.google.protobuf.ByteString;
import com.im.protocol.MsgBody;
import com.im.protocol.MsgBody.MessageBody;
import com.im.server.IMServer;

public class ClientWriterThread implements Runnable {

	private Socket socket;
	private String id;
	
	public ClientWriterThread(Socket socket,String id){
		this.socket = socket;
		this.id = id;
	}
		
	@Override
	public void run() {
		try{
			Socket toSocket = null;
			OutputStream toOut = null;
			
            //创建当前客户socket读取流缓冲区
            InputStream in = socket.getInputStream();
            Map<String, Socket> socketMap = IMServer.imSocketMap;
            int len = -1;
            byte[] bytes = new byte[1024];
            while((len=in.read(bytes)) != -1){
        		if(socketMap!=null && !socketMap.isEmpty()){
        			MsgBody.MessageBody.Builder msgBuilder = MsgBody.MessageBody.newBuilder();
    				msgBuilder.setFromUser("1");
    				msgBuilder.setToUser("1");
    				msgBuilder.setMsgContent("hello");
    				MsgBody.MessageBody msgBody = msgBuilder.build();
    				byte[] byteArray = msgBody.toByteArray();
        			
        			MsgBody.MessageBody parseFrom = MsgBody.MessageBody.parseFrom(ByteString.readFrom(in));
        			//取得要信息要发送的socket的输出流
        			toSocket = socketMap.get(parseFrom.getToUser());
        			toOut = toSocket.getOutputStream();
        			toOut.write((parseFrom.getFromUser()+":"+parseFrom.getMsgContent()).getBytes("UTF-8"));
        			toOut.flush();
        		}
           }
           socket.close();
        }catch (Exception e) {
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
