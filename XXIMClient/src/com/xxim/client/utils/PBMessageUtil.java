package com.xxim.client.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.protobuf.ByteString;
import com.xxim.codec.protobuf.PBMessage;

public class PBMessageUtil {

	public static PBMessage.pbmessage createPBMessage(String message,String dstid) throws UnknownHostException{
		String localIP = InetAddress.getLocalHost().getHostAddress();
		return createPBMessage(localIP, message, dstid);
	}
	
	
	public static PBMessage.pbmessage createPBMessage(byte[] bytes,String dstid) throws UnknownHostException{
		String localIP = InetAddress.getLocalHost().getHostAddress();
		return createPBMessage(localIP, bytes, dstid);
	}
	
	public static PBMessage.pbmessage createPBMessage(String srcid,String message,String dstid){
		PBMessage.pbmessage pb = PBMessage.pbmessage.newBuilder()
		.setSrcid(srcid)
		.setBody(ByteString.copyFromUtf8(message))
		.setDstid(dstid)
		.build();
		return pb;
	}
	
	public static PBMessage.pbmessage createPBMessage(String srcid,byte[] bytes,String dstid){
		PBMessage.pbmessage pb = PBMessage.pbmessage.newBuilder()
		.setSrcid(srcid)
		.setBody(ByteString.copyFrom(bytes))
		.setDstid(dstid)
		.build();
		return pb;
	}
}
