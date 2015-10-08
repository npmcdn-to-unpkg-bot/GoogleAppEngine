package tapp.pages.sci;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appspot.cloudserviceapi.sci.dao.FiOSTokenDAO;
import com.appspot.cloudserviceapi.sci.services.manager.FiOSTokenManager;

import tapp.model.sci.FiOSToken;

public class TokenRegistrationSave {

	private FiOSToken myBean;

	private Long id;

	@Inject
	private FiOSTokenManager beanManager;

	@InjectPage
	private TokenRegistrationStart start;

    @SessionAttribute
    private String email;
    
    public void onActivate(Long id) {
		if (id.equals(0L)) {
			myBean = new FiOSToken();
			myBean.setUserId(email);
		} else {
			myBean = beanManager.getFiOSToken(id);
		}
		this.id = id;
	}

	public Long onPassivate() {
		return id;
	}

	public Object onSubmit() {
		if(myBean != null && (myBean.getUserId() == null && "".equals(myBean.getUserId().trim()))) {
			throw new RuntimeException("User id can not be NULL or empty!");
		}
		String userId = myBean.getUserId();
		System.out.println("FiOS user id = '" + userId + "'");
		FiOSToken b = (new FiOSTokenDAO()).findFiOSTokenByLoginId(userId);
		if(b == null) {
			beanManager.save(myBean);
		} else {
			b.setMagicKey(myBean.getMagicKey());
			beanManager.save(b);
		}
	
		return start;
	}

	public FiOSToken getFiOSToken() {
		return myBean;
	}

	public void setFiOSToken(FiOSToken myBean) {
		this.myBean = myBean;
	}

}