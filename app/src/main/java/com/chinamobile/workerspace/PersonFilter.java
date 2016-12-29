package com.chinamobile.workerspace;

public class PersonFilter {
	private int nzgzt = 0 ;
	private int nxb   =0 ;
	
	
	private int nmz    =  0; 
	private int nzzmm  = 0 ;
	
	private int nagemin = 0 ;
	private int nagemax = 0;
	private int njobtimebein = 0 ;
	private int njobtimeend  = 0 ;
	
	private int nrdtimebegin = 0 ;
	private int nrdtimeend   = 0 ;
	
	
	
	
	private int nzj_fuke_min =  0 ;
	private int nzj_fuke_max =  0 ;

    private int nzj_zhengke_min = 0 ;
    private int nzj_zhengke_max = 0;
    
    private int nzj_fuxian_min = 0 ;
    private int nzj_fuxian_max = 0;
    private int nzj_zhengxian_min = 0 ;
    private int nzj_zhengxian_max = 0;
    
    
    private int nzj_xianzhi_min = 0 ;
    private int nzj_xianzhi_max = 0;
	
    private int qxl_gaozhong    = 0 ;
    private int qxl_zhong    = 0 ;
	private int qxl_zhuan    = 0 ;
	private int qxl_ben    = 0 ;
	private int qxl_shuo    = 0 ;
	private int qxl_shuo_yan    = 0 ;
	private int qxl_bo    = 0 ;
	
	private int zxl_zhong    = 0 ;
	private int zxl_zhuan    = 0 ;
	private int zxl_ben    = 0 ;
	private int zxl_shuo    = 0 ;
	private int zxl_shuo_yan    = 0 ;
	private int zxl_bo    = 0 ;
	
	
	
	
	private int xrzj_keyuan = 0 ;
	private int xrzj_fuke = 0 ;
	private int xrzj_zhengke = 0 ;
	private int xrzj_fuxian = 0 ;
	private int xrzj_zhengxian = 0 ;
	
	
	private int be_chaopei = 0 ;
	private int be_daiguan = 0;
	private int be_tuixiu  = 0;
	
	private String  m_suoxuezhuanye = new String();
	private String  m_congshigangwei = new String();
	
	
	
	
	public void Reset(){
		
		qxl_gaozhong = 0 ;
		qxl_shuo_yan = 0 ;
		zxl_shuo_yan = 0 ;
		nzgzt = 0 ;             
		nxb   =0 ;              		                        
		nmz    =  0;            
		nzzmm  = 0 ;            	                        
		nagemin = 0 ;           
		nagemax = 0;            
		njobtimebein = 0 ;      
		njobtimeend  = 0 ;      	                        
		nrdtimebegin = 0 ;      
		nrdtimeend   = 0 ;                             
		nzj_fuke_min =  0 ;     
		nzj_fuke_max =  0 ;     	                        
		nzj_zhengke_min = 0 ;   
		nzj_zhengke_max = 0;    
		                        
		nzj_fuxian_min = 0 ;    
		nzj_fuxian_max = 0;     
		nzj_zhengxian_min = 0 ; 
		nzj_zhengxian_max = 0;  	                        
		nzj_xianzhi_min = 0 ;   
		nzj_xianzhi_max = 0;    
	                        
		qxl_zhong    = 0 ;      
		qxl_zhuan    = 0 ;      
		qxl_ben    = 0 ;        
		qxl_shuo    = 0 ;       
		qxl_bo    = 0 ;         
		                        
		zxl_zhong    = 0 ;      
		zxl_zhuan    = 0 ;      
		zxl_ben    = 0 ;        
		zxl_shuo    = 0 ;       
		zxl_bo    = 0 ;         
		                        
		xrzj_keyuan = 0 ;       
		xrzj_fuke = 0 ;         
		xrzj_zhengke = 0 ;      
		xrzj_fuxian = 0 ;       
		xrzj_zhengxian = 0 ;    	                        
		be_chaopei = 0 ;        
		be_daiguan = 0;         
		be_tuixiu  = 0;   
		
		m_suoxuezhuanye = "";
		m_congshigangwei = "";

	}
	
	
	
	public int getQxl_gaozhong() {
		return qxl_gaozhong;
	}



	public void setQxl_gaozhong(int qxl_gaozhong) {
		this.qxl_gaozhong = qxl_gaozhong;
	}



	public int getQxl_shuo_yan() {
		return qxl_shuo_yan;
	}



	public void setQxl_shuo_yan(int qxl_shuo_yan) {
		this.qxl_shuo_yan = qxl_shuo_yan;
	}



	public int getZxl_shuo_yan() {
		return zxl_shuo_yan;
	}



	public void setZxl_shuo_yan(int zxl_shuo_yan) {
		this.zxl_shuo_yan = zxl_shuo_yan;
	}



	public String getM_suoxuezhuanye() {
		return m_suoxuezhuanye;
	}



	public void setM_suoxuezhuanye(String m_suoxuezhuanye) {
		this.m_suoxuezhuanye = m_suoxuezhuanye;
	}



	public String getM_congshigangwei() {
		return m_congshigangwei;
	}



	public void setM_congshigangwei(String m_congshigangwei) {
		this.m_congshigangwei = m_congshigangwei;
	}



	public int getNzj_zhengxian_min() {
		return nzj_zhengxian_min;
	}
	public void setNzj_zhengxian_min(int nzj_zhengxian_min) {
		this.nzj_zhengxian_min = nzj_zhengxian_min;
	}
	public int getNzj_zhengxian_max() {
		return nzj_zhengxian_max;
	}
	public void setNzj_zhengxian_max(int nzj_zhengxian_max) {
		this.nzj_zhengxian_max = nzj_zhengxian_max;
	}
	public int getNzgzt() {
		return nzgzt;
	}
	public void setNzgzt(int nzgzt) {
		this.nzgzt = nzgzt;
	}
	public int getNxb() {
		return nxb;
	}
	public void setNxb(int nxb) {
		this.nxb = nxb;
	}
	public int getNmz() {
		return nmz;
	}
	public void setNmz(int nmz) {
		this.nmz = nmz;
	}
	public int getNzzmm() {
		return nzzmm;
	}
	public void setNzzmm(int nzzmm) {
		this.nzzmm = nzzmm;
	}
	public int getNagemin() {
		return nagemin;
	}
	public void setNagemin(int nagemin) {
		this.nagemin = nagemin;
	}
	public int getNagemax() {
		return nagemax;
	}
	public void setNagemax(int nagemax) {
		this.nagemax = nagemax;
	}
	public int getNjobtimebein() {
		return njobtimebein;
	}
	public void setNjobtimebein(int njobtimebein) {
		this.njobtimebein = njobtimebein;
	}
	public int getNjobtimeend() {
		return njobtimeend;
	}
	public void setNjobtimeend(int njobtimeend) {
		this.njobtimeend = njobtimeend;
	}
	public int getNrdtimebegin() {
		return nrdtimebegin;
	}
	public void setNrdtimebegin(int nrdtimebegin) {
		this.nrdtimebegin = nrdtimebegin;
	}
	public int getNrdtimeend() {
		return nrdtimeend;
	}
	public void setNrdtimeend(int nrdtimeend) {
		this.nrdtimeend = nrdtimeend;
	}
	public int getNzj_fuke_min() {
		return nzj_fuke_min;
	}
	public void setNzj_fuke_min(int nzj_fuke_min) {
		this.nzj_fuke_min = nzj_fuke_min;
	}
	public int getNzj_fuke_max() {
		return nzj_fuke_max;
	}
	public void setNzj_fuke_max(int nzj_fuke_max) {
		this.nzj_fuke_max = nzj_fuke_max;
	}
	public int getNzj_zhengke_min() {
		return nzj_zhengke_min;
	}
	public void setNzj_zhengke_min(int nzj_zhengke_min) {
		this.nzj_zhengke_min = nzj_zhengke_min;
	}
	public int getNzj_zhengke_max() {
		return nzj_zhengke_max;
	}
	public void setNzj_zhengke_max(int nzj_zhengke_max) {
		this.nzj_zhengke_max = nzj_zhengke_max;
	}
	public int getNzj_fuxian_min() {
		return nzj_fuxian_min;
	}
	public void setNzj_fuxian_min(int nzj_fuxian_min) {
		this.nzj_fuxian_min = nzj_fuxian_min;
	}
	public int getNzj_fuxian_max() {
		return nzj_fuxian_max;
	}
	public void setNzj_fuxian_max(int nzj_fuxian_max) {
		this.nzj_fuxian_max = nzj_fuxian_max;
	}
	public int getNzj_xianzhi_min() {
		return nzj_xianzhi_min;
	}
	public void setNzj_xianzhi_min(int nzj_xianzhi_min) {
		this.nzj_xianzhi_min = nzj_xianzhi_min;
	}
	public int getNzj_xianzhi_max() {
		return nzj_xianzhi_max;
	}
	public void setNzj_xianzhi_max(int nzj_xianzhi_max) {
		this.nzj_xianzhi_max = nzj_xianzhi_max;
	}
	public int getQxl_zhong() {
		return qxl_zhong;
	}
	public void setQxl_zhong(int qxl_zhong) {
		this.qxl_zhong = qxl_zhong;
	}
	public int getQxl_zhuan() {
		return qxl_zhuan;
	}
	public void setQxl_zhuan(int qxl_zhuan) {
		this.qxl_zhuan = qxl_zhuan;
	}
	public int getQxl_ben() {
		return qxl_ben;
	}
	public void setQxl_ben(int qxl_ben) {
		this.qxl_ben = qxl_ben;
	}
	public int getQxl_shuo() {
		return qxl_shuo;
	}
	public void setQxl_shuo(int qxl_shuo) {
		this.qxl_shuo = qxl_shuo;
	}
	public int getQxl_bo() {
		return qxl_bo;
	}
	public void setQxl_bo(int qxl_bo) {
		this.qxl_bo = qxl_bo;
	}
	public int getZxl_zhong() {
		return zxl_zhong;
	}
	public void setZxl_zhong(int zxl_zhong) {
		this.zxl_zhong = zxl_zhong;
	}
	public int getZxl_zhuan() {
		return zxl_zhuan;
	}
	public void setZxl_zhuan(int zxl_zhuan) {
		this.zxl_zhuan = zxl_zhuan;
	}
	public int getZxl_ben() {
		return zxl_ben;
	}
	public void setZxl_ben(int zxl_ben) {
		this.zxl_ben = zxl_ben;
	}
	public int getZxl_shuo() {
		return zxl_shuo;
	}
	public void setZxl_shuo(int zxl_shuo) {
		this.zxl_shuo = zxl_shuo;
	}
	public int getZxl_bo() {
		return zxl_bo;
	}
	public void setZxl_bo(int zxl_bo) {
		this.zxl_bo = zxl_bo;
	}
	public int getXrzj_keyuan() {
		return xrzj_keyuan;
	}
	public void setXrzj_keyuan(int xrzj_keyuan) {
		this.xrzj_keyuan = xrzj_keyuan;
	}
	public int getXrzj_fuke() {
		return xrzj_fuke;
	}
	public void setXrzj_fuke(int xrzj_fuke) {
		this.xrzj_fuke = xrzj_fuke;
	}
	public int getXrzj_zhengke() {
		return xrzj_zhengke;
	}
	public void setXrzj_zhengke(int xrzj_zhengke) {
		this.xrzj_zhengke = xrzj_zhengke;
	}
	
	
	public int getXrzj_fuxian() {
		return xrzj_fuxian;
	}
	public void setXrzj_fuxian(int xrzj_fuxian) {
		this.xrzj_fuxian = xrzj_fuxian;
	}
	public int getXrzj_zhengxian() {
		return xrzj_zhengxian;
	}
	public void setXrzj_zhengxian(int xrzj_zhengxian) {
		this.xrzj_zhengxian = xrzj_zhengxian;
	}
	public int getBe_chaopei() {
		return be_chaopei;
	}
	public void setBe_chaopei(int be_chaopei) {
		this.be_chaopei = be_chaopei;
	}
	public int getBe_daiguan() {
		return be_daiguan;
	}
	public void setBe_daiguan(int be_daiguan) {
		this.be_daiguan = be_daiguan;
	}
	public int getBe_tuixiu() {
		return be_tuixiu;
	}
	public void setBe_tuixiu(int be_tuixiu) {
		this.be_tuixiu = be_tuixiu;
	}
	
	
}
