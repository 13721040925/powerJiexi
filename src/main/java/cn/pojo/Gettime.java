package cn.pojo;

import org.springframework.stereotype.Component;

@Component("gettime")
public class Gettime {
	private Integer id;
	private Integer second;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "Gettime [id=" + id + ", second=" + second + "]";
	}

}
