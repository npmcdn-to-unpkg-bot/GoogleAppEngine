package tapp.pages.sci;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.services.RequestGlobals;
import org.ocpsoft.pretty.time.PrettyTime;
import org.springframework.security.core.Authentication;

import tapp.model.ServiceRegistry;
import app.common.Constants;
import app.common.SecurityUtils;

import com.appspot.cloudserviceapi.common.HTMLUtil;
import com.appspot.cloudserviceapi.common.SettingsDBUtils;
import com.appspot.cloudserviceapi.data.AppEngine;
import com.appspot.cloudserviceapi.sci.dao.ServiceRegistryDAO;
import com.appspot.cloudserviceapi.sci.services.manager.ServiceRegistryManager;
import com.appspot.cloudserviceapi.security.spring.UserRole;
import com.appspot.cloudserviceapi.util.Compass;

public class ServiceRegistryStart {

	private String TCOUNT_UUID = "service.registry.count";

	@SessionState
	private Authentication auth;
	
	@Component(id="landscapeSrGrid")
    private Grid _landscapeGrid;

	@Component(id="portraitSrGrid")
    private Grid _portraitGrid;

	@Inject
	private ServiceRegistryManager beanManager;

	private ServiceRegistry myBean;

//	@Inject
//	private BeanModelSource beanModelSource;

	@Inject 
	private Messages messages;
	
//	@Inject 
//	private Grid grid;

	@Inject
	private RequestGlobals requestGlobals;
	
	//=== http://tapestry.apache.org/session-storage.html
	//@SessionState(create=true)	//didn't work
    private Boolean justSaved;
    
    private String currentExcerpt;
	
    private String cachedHostName;
    
    @Component
    private Form form;
//    @Inject 
//    private AlertManager alertManager;

    private long totalRows = -1;
    
    public String getCustomErrorMessage() {
    	return "Error";
    }
    
	public ServiceRegistry getServiceRegistry() {
		return myBean;
	}

	public void setServiceRegistry(ServiceRegistry ServiceRegistry) {
		this.myBean = ServiceRegistry;
	}

	public List<ServiceRegistry> getServiceRegistrys() {
		return beanManager.getServiceRegistrys();
	}

	public boolean isEmptyList() {
		return beanManager.getServiceRegistrys().isEmpty();
	}

	/** cost of this call has not been evaluated!!! */
	public long getTotalRows() {
		long totalRows = 0;
		String t = SettingsDBUtils.getSettings(TCOUNT_UUID);
		if(!StringUtils.isEmpty(t)) {
			try {
				long count = Integer.valueOf(t);
				totalRows = count;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//just ignore it
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalRows;
	}
	
	/**
	 * https://tapestry.apache.org/component-rendering.html
	 * 
	 * @return
	 */
//	@AfterRender
//	public void initTotalRows() {
//		long retVal = -1;
//		if(_portraitGrid != null && _portraitGrid.getDataSource() != null)
//			retVal = _portraitGrid.getDataSource().getAvailableRows();
//		else if(_landscapeGrid != null && _landscapeGrid.getDataSource() != null)
//			retVal = _landscapeGrid.getDataSource().getAvailableRows();
//		
//		totalRows = retVal;	//beanManager.getServiceRegistrys().size(); //TBD PERF expensive operation!
//	}
	
	/**
	 * Thanks to http://apache-tapestry-mailing-list-archives.1045711.n5.nabble.com/delete-confirm-td2421285.html#a2421287
	 */
	@Component(parameters = {"onClick=literal:return confirm(\"OK to delete this item?\");"})
	private ActionLink delete;
			
	public void onActionFromDelete(Long id) throws Exception {
		if(ServiceRegistryHelper.isUpdateAllowed(id)) {
			try {
				beanManager.delete(id);
				decrementTotalCount();
			} catch (Exception e) {
				//e.printStackTrace();
				// TODO is this a real issue or due to improper implementation?
				if(e.getMessage() != null && !e.getMessage().equals("Cannot read fields from a deleted object")) {
					form.recordError("Sorry, not able to delete Service Id '" + id + "' due to an error [" + e.getMessage() + "].");
				} else {
					decrementTotalCount();
				}
			}
		} else {
            form.recordError("Sorry, but delete to Service Id '" + id + "' is not permitted.");
//			AlertManager alertManager = TapestryUtil.getAlertManager();
//			if(alertManager != null) {
//				alertManager.alert(Duration.SINGLE, Severity.INFO, "Sorry, but delete to Service Id '" + id + "' is not permitted.");
//			}
		}
	}

	private void decrementTotalCount() {
		totalRows = totalRows-1;
		SettingsDBUtils.updateSettings(TCOUNT_UUID, String.valueOf(totalRows));
//		initTotalRows();
	}

	public String getHostName() {
		if(cachedHostName == null) {
			ServiceRegistryDAO dao = new ServiceRegistryDAO();
			ServiceRegistry sr = dao.findServiceRegistryByService(Constants.APP_ID_MASK);
			if(sr != null) {
				cachedHostName = sr.getEndpoint();
				if(cachedHostName != null && !cachedHostName.equals("localhost")) {	//TODO future enhancement should avoid this check on PROD	
					cachedHostName += ".appspot.com";
				} else {
					cachedHostName += ":8888";
				}
			} else {
				cachedHostName = AppEngine.getHostName();
			}
			System.out.println("cachedHostName set to [" + cachedHostName + "]");
		}
		
		return cachedHostName;
	}

	/*
	 * Used for sorting the grid after each new entry is inserted.
	 * This method will be called during the Grid rendering.
	 * 
	 * source: http://tapestry.1045711.n5.nabble.com/T5-disabling-column-sorting-programmatically-in-Grid-td2427489.html
	 */
//	public BeanModel getBeanModel() {
//		BeanModel model = beanModelSource.createDisplayModel(ServiceRegistry.class,
//				messages);
//		PropertyModel nameColumn = model.getById("lastUpdated");
//		nameColumn.sortable(true);
//		((Grid)model).getSortModel().updateSort("lastUpdated");
//		return model;
//	}
	
	@SetupRender
	void setupRender() {
		currentExcerpt = "";
		/*
        BeanModel model = _grid.getDataModel(); 
        List<String> propertyNames = model.getPropertyNames(); 
        for (String propName : propertyNames) { 
            PropertyModel propModel = model.get(propName); 
            propModel.sortable(false);
        }
        */
		justSaved = (Boolean)requestGlobals.getHTTPServletRequest().getSession().getAttribute("justSavedServiceRegistry");
		//System.out.println("sr: justSaved " + justSaved);
		if(justSaved != null && !justSaved) {
//	        _grid.getSortModel().updateSort("lastAccessed");
//	        _grid.getSortModel().updateSort("lastAccessed");
		} else {
			//=== these allow a descending sort based on column lastUpdatedDate (I know it seems like it is a hack, but it is not!)
			_portraitGrid.getSortModel().updateSort("lastAccessed");
			_portraitGrid.getSortModel().updateSort("lastAccessed");
			_landscapeGrid.getSortModel().updateSort("lastUpdated");
			_landscapeGrid.getSortModel().updateSort("lastUpdated");
	        requestGlobals.getHTTPServletRequest().getSession().setAttribute("justSavedServiceRegistry", false);
		}

//		if(_landscapeGrid != null && _landscapeGrid.getDataSource() != null) {
//			totalRows = _landscapeGrid.getDataSource().getAvailableRows();
//		} else
//		if(_portraitGrid != null && _portraitGrid.getDataSource() != null) {
//			totalRows = _portraitGrid.getDataSource().getAvailableRows();
//		}

//		initTotalRows();
    }

/*	
	public String getDescriptionExcerpt() {
		String retVal = myBean.getDescription();
//		if(myBean.getUseHtml()) {
			retVal = HTMLUtil.removeTags(myBean.getDescription());	//cause slow startup on the GAEJ cloud
//		}
		retVal = StringUtils.abbreviate(retVal, 100);
		return retVal;
	}
*/

	public String getCurrentExcerpt() {
		return currentExcerpt;
	}

	public String getDescriptionDetails() {
		String retVal = myBean.getDescription();
		try {
//		if(myBean.getUseHtml()) {
			retVal = HTMLUtil.removeTags(myBean.getDescription());	//cause slow startup on the GAEJ cloud
			retVal = retVal.trim();
//		}
				
			currentExcerpt = StringUtils.abbreviate(retVal, 100);
		} catch(Exception e) {
		}
		return retVal;
	}
	
	public String getFriendlyLastUpdatedTime() {
		PrettyTime p = new PrettyTime();
		
		return p.format(myBean.getLastUpdated());
	}	
	
	/**
	 * http://blog.snugsound.com/2008/08/implementing-logout-function-in.html
	 */
	public Object onActionFromLogout() {
//		requestGlobals.getHTTPServletRequest().getSession().invalidate();
		requestGlobals.getRequest().getSession(false).invalidate();

		return Index.class;
	}

	/** Loggedin User's ID */
	public String getLoggedInUser() {
		String retVal = SecurityUtils.getLoggedInUser();

		return retVal;
	}
	
	/** Loggedin User's Role(s) */
	public String getUserAssignedRole() {
		String retVal = SecurityUtils.getLoggedInUserRole();

		return retVal;
	}
	
	//TODO to replace with Spring Security's security annotation/tag
	/** Running lucene index */
	public String getRunIndexDisplay() {
		String retVal = "none";
		
		if(SecurityUtils.isAdmin(UserRole.ROLE_ADMIN_SCI.toString()) || SecurityUtils.isAdmin(UserRole.ROLE_ADMIN.toString())) {
			retVal = "inline";
		}
		return retVal;
	}

	//TODO to replace with Spring Security's security annotation/tag
	/** Delete action link */
	public String getDeleteActionDisplay() {
		String retVal = "none";
//		
//		if(SecurityUtils.isAdmin(UserRole.ROLE_ADMIN_SCI.toString()) || SecurityUtils.isAdmin(UserRole.ROLE_ADMIN.toString())) {
			retVal = "inline";
//		}
		return retVal;
	}

	//TODO to replace with Spring Security's security annotation/tag
	/** Genius start link */
	public String getGeniusStartDisplay() {
		String retVal = "none";
		
		if(SecurityUtils.isAdmin(UserRole.ROLE_ADMIN_SCI.toString()) || SecurityUtils.isAdmin(UserRole.ROLE_ADMIN.toString())) {
			retVal = "inline";
		}
		return retVal;
	}
	
	//TODO to replace with Spring Security's security annotation/tag
	/** Running lucene index */
	public String getSearchBoxDisplay() {
		String retVal = "none";
		
		if(SecurityUtils.isAdmin(UserRole.ROLE_ADMIN_SCI.toString()) || SecurityUtils.isAdmin(UserRole.ROLE_ADMIN.toString())) {
			retVal = "inline";
		}
		return retVal;
	}

	/** t-data-grid-pager */
	public String getDataGridPagerDisplay() {
		String retVal = "none";
		
		if(SecurityUtils.isAdmin(UserRole.ROLE_ADMIN_SCI.toString()) || SecurityUtils.isAdmin(UserRole.ROLE_ADMIN.toString())) {
			retVal = "inline";
		}
		return retVal;
	}
	
	public void onSubmit() { 
		try {
			Compass.index();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}