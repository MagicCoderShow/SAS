package com.xuping.sas.util;

public class CommonJson {

	private boolean success=false;
	private String error_msg;

	public boolean is_success() {
		return success;
	}

	public void set_success(boolean _success) {
		this.success = _success;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.success=false;
		this.error_msg = error_msg;
	}
}
