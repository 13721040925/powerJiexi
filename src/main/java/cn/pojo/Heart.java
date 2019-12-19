package cn.pojo;

import java.net.Socket;


public class Heart {
	private String imei;
	private Socket socket;
	private long time;

	public Heart() {
	}

	public Heart(String imei, Socket socket, long time) {
		this.imei = imei;
		this.socket = socket;
		this.time = time;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Heart [imei=" + imei + ", socket=" + socket + ", time=" + time + "]";
	}

}
