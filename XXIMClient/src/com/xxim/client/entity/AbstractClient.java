package com.xxim.client.entity;

import java.io.IOException;

public abstract class AbstractClient {
	/**
	 * 读取bytes数组中的内容
	 * @param bytes
	 * @return 读取到的总字节数长度，-1为没有读取到
	 * @throws IOException
	 */
	public abstract int read(byte[] bytes) throws IOException;
	/**
	 * 读取bytes数组中的内容
	 * @param bytes
	 * @param off 数组起始下标
	 * @param len 长度
	 * @return 读取到的总字节数长度，-1为没有读取到
	 * @throws IOException
	 */
	public abstract int read(byte[] bytes,int off,int len) throws IOException;
	/**
	 * 向socket中写入数组
	 * @param bytes
	 * @throws IOException
	 */
	public abstract void write(byte[] bytes) throws IOException;
	/**
	 * 向socket中写入数组
	 * @param bytes
	 * @param off 数组起始下标
	 * @param len 长度
	 * @throws IOException
	 */
	public abstract void write(byte[] bytes,int off,int len) throws IOException;
}
