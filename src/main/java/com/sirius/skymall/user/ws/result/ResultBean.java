package com.sirius.skymall.user.ws.result;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Result")
public class ResultBean<T> extends ApiBaseResult {
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
}
