package com.xxim.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xxim.codec.protobuf.PBMessage;

public class PackageBody {

	public static PBMessage.pbmessage decode(byte[] data) throws InvalidProtocolBufferException{
		return PBMessage.pbmessage.parseFrom(data);
	}
	
	public static byte[] encode(PBMessage.pbmessage message){
		return message.toByteArray();
	}
}
