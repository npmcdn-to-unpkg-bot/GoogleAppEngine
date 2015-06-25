package com.aurifa.struts2.tutorial.action;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aurifa.struts2.tutorial.service.BusinessDaoService;
import com.aurifa.struts2.tutorial.service.BusinessService;
import com.aurifa.struts2.tutorial.service.PartnerDaoService;
import com.aurifa.struts2.tutorial.service.PartnerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.ypg.db.dto.Business;
import com.ypg.db.dto.Partner;

/**
 * http://struts.apache.org/2.0.14/docs/crud-demo-i.html
 */
public class PartnerAction extends ActionSupport implements Preparable {

	private Log logger = LogFactory.getLog(this.getClass());

	private static PartnerService partnerService = new PartnerDaoService();

	private static BusinessService businessService = new BusinessDaoService();

	private Partner partner;

	private List partners;

	private List<Business> businesses;

	public void validate() {
//		if (getUserName().length() == 0) {
//			addFieldError("userName", "User Name is required");
//		} else if (!getUserName().equals("Mel")) {
//			addFieldError("userName", "Invalid User");
//		}
//		if (getPassword().length() == 0) {
//			addFieldError("password", getText("password.required"));
//		}
	}

	public void prepare() throws Exception {
		businesses = businessService.getAllBusiness();
		if (partner != null && partner.getId() != null) {
			partner = partnerService.getPartner(partner.getId());
		}
	}

	public String doSave() {
		try {
			System.out.println("PartnerAction:doSave " + partner);
			if (partner.getId() == null) {
				partnerService.insertPartner(partner);
			} else {
				partnerService.updatePartner(partner);
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	public String doDelete() {
		try {
			partnerService.deletePartner(partner.getId());
		} catch (Exception e) {
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	public String doList() {
		partners = partnerService.getAllPartners();
		return SUCCESS;
	}

	public String doInput() {
		partner = new Partner();
		return INPUT;
	}

	/**
	 * @return Returns the partner.
	 */
	public Partner getPartner() {
		return partner;
	}

	/**
	 * @param partner
	 *            The partner to set.
	 */
	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	/**
	 * @return Returns the partners.
	 */
	public List getPartners() {
		return partners;
	}

	/**
	 * @return Returns the business.
	 */
	public List<BusinessType> getBusinesses() {
		return Arrays.asList(BusinessType.values());
		// return businesses; //exception: tag 'select', field 'list', name
		// 'partner.business.businessId': The requested list key 'businesses'
		// could not be resolved as a collection/array/map/enumeration/iterator
		// type. Example: people or people.{name} - [unknown location]
	}

}
