package cn.pojo;

import org.springframework.stereotype.Component;

@Component("cooperate_info")
public class Cooperate_info {
	private Integer id;
	private String imei;
	private String ip;
	private String cooperate_name;
	private String version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCooperate_name() {
		return cooperate_name;
	}

	public void setCooperate_name(String cooperate_name) {
		this.cooperate_name = cooperate_name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Cooperate_info [id=" + id + ", imei=" + imei + ", ip=" + ip + ", cooperate_name=" + cooperate_name
				+ ", version=" + version + "]";
	}

}
