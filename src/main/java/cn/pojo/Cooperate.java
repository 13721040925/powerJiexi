package cn.pojo;

import org.springframework.stereotype.Component;

@Component("cooperate")
public class Cooperate {
	private Long id;
	private String imei;
	private String ip;
	private String cooperate_name;//����������
	private Double fdtime;// �ŵ絹��ʱ
	private Integer totaltime;// �ŵ���ʱ��
	private Integer onlioncount;// �������������
	private String state;
	private String logtime;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	public Double getFdtime() {
		return fdtime;
	}
	public void setFdtime(Double fdtime) {
		this.fdtime = fdtime;
	}
	public Integer getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(Integer totaltime) {
		this.totaltime = totaltime;
	}
	public Integer getOnlioncount() {
		return onlioncount;
	}
	public void setOnlioncount(Integer onlioncount) {
		this.onlioncount = onlioncount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getLogtime() {
		return logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	@Override
	public String toString() {
		return "Cooperate [id=" + id + ", imei=" + imei + ", ip=" + ip + ", cooperate_name=" + cooperate_name
				+ ", fdtime=" + fdtime + ", totaltime=" + totaltime + ", onlioncount=" + onlioncount + ", state="
				+ state + ", logtime=" + logtime + "]";
	}



	
}
