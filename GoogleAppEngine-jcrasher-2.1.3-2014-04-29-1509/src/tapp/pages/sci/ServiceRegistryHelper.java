package tapp.pages.sci;

import org.datanucleus.util.StringUtils;

import tapp.model.ServiceRegistry;
import app.common.SecurityUtils;

import com.appspot.cloudserviceapi.sci.dao.ServiceRegistryDAO;
import com.appspot.cloudserviceapi.security.spring.UserRole;

public class ServiceRegistryHelper {

	public static boolean isUpdateAllowed(Long id) {
		boolean retVal = false;	//not allowed by default

		if(id > 0) {
			ServiceRegistryDAO dao = new ServiceRegistryDAO();
			ServiceRegistry oldBean = dao.get(id);
			
			if((SecurityUtils.getLoggedInUser() != null && oldBean != null && oldBean.getOid() != null && SecurityUtils.getLoggedInUser().equals(oldBean.getOid())) ||
			SecurityUtils.isAdmin(UserRole.ROLE_ADMIN.toString()) || SecurityUtils.isAdmin(UserRole.ROLE_ADMIN_SCI.toString())
			) {
				retVal = true;
			}
		} else if(id == 0){
			retVal = true;	//newly created, allowed irrespective
		}
		
		return retVal;
	}

}
