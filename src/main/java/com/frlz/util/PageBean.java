package com.frlz.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author cz
 */
public class PageBean {

	private int pageCode;
	private int pageSize;
	private int allCount;
	private int allPages;
	
	private List datas = new ArrayList();

	private Set set = new HashSet<>();
	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getAllPages() {
		return  allPages = (allCount-1)/pageSize+1;
	}

	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "PageBean [pageCode=" + pageCode + ", pageSize=" + pageSize + ", allCount=" + allCount + ", allPages="
				+ allPages + ", datas=" + datas + ", set=" + set + "]";
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}
	
	
}
