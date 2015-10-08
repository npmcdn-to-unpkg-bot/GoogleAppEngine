package tapp.pages.sci;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.appspot.cloudserviceapi.sci.dao.VideoDataDAO;
import com.appspot.cloudserviceapi.sci.services.manager.VideoDataManager;

import tapp.model.sci.VideoData;

public class BuyerSave {

	private VideoData myBean;

	private Long id;

	@Inject
	private VideoDataManager beanManager;

	@InjectPage
	private VideoDataStart start;

    @SessionAttribute
    private String email;
    
    public void onActivate(Long id) {
		if (id.equals(0L)) {
			myBean = new VideoData();
		} else {
			myBean = beanManager.getVideoData(id);
		}
		this.id = id;
	}

	public Long onPassivate() {
		return id;
	}

	public Object onSubmit() {
		String videoId = myBean.getVideoId();
		System.out.println("PG video id = '" + videoId + "'");
		VideoData b = (new VideoDataDAO()).findVideoDataByLoginId(videoId);
		if(b == null) {
			beanManager.save(myBean);
		} else {
//			b.setMagicKey(myBean.getMagicKey());
			beanManager.save(b);
		}
	
		return start;
	}

	public VideoData getVideoData() {
		return myBean;
	}

	public void setVideoData(VideoData myBean) {
		this.myBean = myBean;
	}

}