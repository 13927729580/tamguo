package com.tamguo.common.utils;

import java.util.ArrayList;


public class UploaderMessage {
    private Status status;
    private String statusMsg = "";
    private ArrayList<Integer> errorKys;
    private String error = "";
    private String filePath = "";
    private String fileDomain = "";

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public ArrayList<Integer> getErrorKys() {
        return errorKys;
    }

    public void setErrorKys(ArrayList<Integer> errorKys) {
        this.errorKys = errorKys;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDomain() {
		return fileDomain;
	}

	public void setFileDomain(String fileDomain) {
		this.fileDomain = fileDomain;
	}
    
    
}




