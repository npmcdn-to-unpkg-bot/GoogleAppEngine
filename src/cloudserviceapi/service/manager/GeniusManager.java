package cloudserviceapi.service.manager;

import java.util.List;

import com.appspot.cloudserviceapi.dao.GeniuDao;
import com.appspot.cloudserviceapi.dto.Geniu;

public interface GeniusManager {
	
		public GeniuDao getDao();

		public List<Geniu> getGenius();

        public void setGeniu(List<Geniu> myBeans);
        
        public void delete(Long id);
        
        public void save(Geniu myBean) throws Exception;
        
        public Geniu getGeniu(Long id);

		public void updateCache(Geniu myBean);

}