package cloudserviceapi.service.manager;

import java.util.List;

import com.appspot.cloudserviceapi.dao.SecureDao;
import com.appspot.cloudserviceapi.dto.Secure;

public interface SecuredManager {
	
		public SecureDao getDao();

		public List<Secure> getSecured();

        public void setSecure(List<Secure> myBeans);
        
        public void delete(Long id);
        
        public void save(Secure myBean) throws Exception;
        
        public Secure getSecure(Long id);       

}