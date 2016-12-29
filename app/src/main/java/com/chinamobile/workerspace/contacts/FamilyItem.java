package com.chinamobile.workerspace.contacts;

public class FamilyItem {
	
	
	 private String     m_sfz_no   ;
	 private int        m_xh       ;
	 private String     m_cw       ;
	 private String     m_xm       ;
	 private String     m_mz       ;
	 private String     m_csrq     ;
	 private String     m_zzmm     ;
	 private String     m_gzdw     ;
	 private String     m_zw       ;
	 private String     m_bz       ;
	 
	 
	public String getM_sfz_no() {
		return m_sfz_no;
	}
	public void setM_sfz_no(String m_sfz_no) {
		this.m_sfz_no = m_sfz_no;
	}
	public int getM_xh() {
		return m_xh;
	}
	public void setM_xh(int m_xh) {
		this.m_xh = m_xh;
	}
	public String getM_cw() {
		return m_cw;
	}
	public void setM_cw(String m_cw) {
		this.m_cw = m_cw;
	}
	public String getM_xm() {
		return m_xm;
	}
	public void setM_xm(String m_xm) {
		this.m_xm = m_xm;
	}
	public String getM_mz() {
		return m_mz;
	}
	public void setM_mz(String m_mz) {
		
		if(m_mz == null){
			this.m_mz = " ";
		}else{
			this.m_mz = m_mz;
		}
	}
	public String getM_csrq() {
		return m_csrq;
	}
	public void setM_csrq(String m_csrq) {	
		if(m_csrq == null){
			this.m_csrq = " ";
		}else{
			this.m_csrq = m_csrq;
		}
	}
	public String getM_zzmm() {
		return m_zzmm;
	}
	public void setM_zzmm(String m_zzmm) {
		
		if(m_zzmm == null){
			this.m_zzmm = " ";
		}else{
			this.m_zzmm = m_zzmm;
		}
	}
	public String getM_gzdw() {
		return m_gzdw;
	}
	public void setM_gzdw(String m_gzdw) {
		this.m_gzdw = m_gzdw;
	}
	public String getM_zw() {
		return m_zw;
	}
	public void setM_zw(String m_zw) {
		
		if(m_zw == null){
			this.m_zw = " ";
		}else{
			this.m_zw = m_zw;
		}
	}
	public String getM_bz() {
		return m_bz;
	}
	public void setM_bz(String m_bz) {
	
		if(m_bz == null){
			this.m_bz = " ";
		}else{
			this.m_bz = m_bz;
		}
	}

	   
	
   
   

   
}
