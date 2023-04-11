package com.lcomputerstudy.example.domain;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Board {
	private int bIdx;				// b_idx
	private String bTitle;			// b_title
	private String bContent;		// b_content
	private int bViews;				// b_views
	private String bDateTime;		// b_datetime
	private User user;				// User 추가
	private int bGroup;				// b_group
	private int bOrder;				// b_order
	private int bDepth;				// b_depth
	private String bFileName;		// b_filename
	private String bFilePath;		// b_filepath
	
	private int pageSize = Pagination.perPage;		//한 페이지에 나타내는 데이터 개수
	private int currentPage = 1;
	
	
	
	public int getbIdx() {
		return bIdx;
	}
	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public int getbViews() {
		return bViews;
	}
	public void setbViews(int bViews) {
		this.bViews = bViews;
	}
	public String getbDateTime() {
		return bDateTime;
	}
	public void setbDateTime(String bDateTime) {
		this.bDateTime = bDateTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbOrder() {
		return bOrder;
	}
	public void setbOrder(int bOrder) {
		this.bOrder = bOrder;
	}
	public int getbDepth() {
		return bDepth;
	}
	public void setbDepth(int bDepth) {
		this.bDepth = bDepth;
	}
	public String getbFileName() {
		return bFileName;
	}
	public void setbFileName(String bFileName) {
		this.bFileName = bFileName;
	}
	public String getbFilePath() {
		return bFilePath;
	}
	public void setbFilePath(String bFilePath) {
		this.bFilePath = bFilePath;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
}
