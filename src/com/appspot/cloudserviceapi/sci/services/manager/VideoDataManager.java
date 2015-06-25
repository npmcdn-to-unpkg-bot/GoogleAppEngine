package com.appspot.cloudserviceapi.sci.services.manager;

import java.util.List;

import tapp.model.sci.VideoData;

public interface VideoDataManager {

        public abstract List<VideoData> getVideoDatas();

        public abstract void setVideoDatas(List<VideoData> myBeans);
        
        public void delete(Long id);
        
        public abstract void save(VideoData myBean);
        
        public VideoData getVideoData(Long id);       

}