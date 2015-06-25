package com.appspot.cloudserviceapi.service.manager;

import java.util.List;

import com.appspot.cloudserviceapi.dao.HumaDao;
import com.appspot.cloudserviceapi.dto.Huma;

public interface SocialManager {
	
		public HumaDao getDao();

		public List<Huma> getSocial();

        public void setHuma(List<Huma> myBeans);
        
        public void delete(Long id);
        
        public void save(Huma myBean) throws Exception;
        
        public Huma getHuma(Long id);       

}