package com.xxim.codec;
/**
 * | MAGIC_SOH (1 byte) | Version（1byte）| BodyLength (4byte) | SecType (2byte) | Timeout (1byte) | MAGIC_STX (1 byte)|
 *
 */
public class PackageHeader {
	
	public byte magicHeader_0;
	public byte magicHeader_1;
	public char version;
	public int bodyLength;
	public char secType;
	public short timeout;
	
	public int unpackData(PackageDecoder decoder) throws Exception{
		// magic start
		byte m_0 = decoder.unpackByte();
		if(m_0 != PackageConst.MAGIC_SOH){
			return decoder.cursor;
		}
		magicHeader_0 = m_0;
		//version
		version = (char)decoder.unpackByte();
		//BodyLength
		bodyLength = decoder.unpackInt();
		//SecType
		secType = (char)decoder.unpackByte();
		//Timeout
		timeout = decoder.unpackShort();
		//magic end
		byte m_1 = decoder.unpackByte();
		if(m_1 != PackageConst.MAGIC_STX){
			return decoder.cursor;
		}
		return 0;
	}
	
	public int packData(PackageCoder coder,int bodyLength,char secType,short timeout){
		//magic start
		coder.packByte(PackageConst.MAGIC_SOH);
		// version
		coder.packByte((byte)PackageConst.PACKAGE_HEADER_VERSION);
		//BodyLength
		coder.packInt(bodyLength);
		//SecType
		coder.packByte((byte)secType);
		//Timeout
		coder.packShort(timeout);
		//magic end
		coder.packByte(PackageConst.MAGIC_STX);
		return 0;
	}
}
