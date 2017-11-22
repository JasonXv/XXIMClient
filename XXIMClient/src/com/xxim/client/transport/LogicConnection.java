package com.xxim.client.transport;

import com.xxim.client.entity.AbstractClient;

public abstract class LogicConnection {
	/**
	 * 创建连接
	 * @return
	 * @throws Exception
	 */
	public abstract AbstractClient connect(String ip,int port);
	/**
	 * 关闭连接
	 * @return
	 */
	public abstract void close();
	/**
	 * 重启连接
	 */
	public abstract void restart();
}
