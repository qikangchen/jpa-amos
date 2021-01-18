package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.data.Request;
import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyRepo {

    private final RequestRepository requestRepository;
    private final RequestLocalInfoRepository requestLocalInfoRepository;

    public MyRepo(@Autowired RequestRepository requestRepository, @Autowired RequestLocalInfoRepository requestLocalInfoRepository){
        this.requestRepository = requestRepository;
        this.requestLocalInfoRepository = requestLocalInfoRepository;
    }

    public void insertRequest(Request request){
        requestRepository.save(request);
    }

    public Request getRequest(String cityName, int timeStamp){

        RequestLocalInfo requestLocalInfo = getRequestLocalInfoFromCityName(cityName);

        Optional<Request> request = requestRepository.findByRequestLocalInfoAndRequestTimeStamp(requestLocalInfo, timeStamp);
        if(!request.isPresent()){
            throw new IllegalStateException("Can't find timestamp " + timeStamp + " in this location: " + requestLocalInfo);
        }

        return request.get();
    }

    public List<RequestLocalInfo> getLocalInfos(){

        Iterable<RequestLocalInfo> requestLocalInfos = requestLocalInfoRepository.findAll();
        return ImmutableList.copyOf(requestLocalInfos);

    }

    public RequestLocalInfo getRequestLocalInfoFromCityName(String cityName){

        Optional<RequestLocalInfo> requestLocalInfo = requestLocalInfoRepository.findByCityName(cityName);
        if(!requestLocalInfo.isPresent()){
            throw new IllegalStateException("Can't find Local info with ciy name: " + cityName);
        }

        return requestLocalInfo.get();
    }

    public List<RequestRepository.Timestamp> getTimeStampsFromCityName(String cityName){

        RequestLocalInfo requestLocalInfo = getRequestLocalInfoFromCityName(cityName);

        return requestRepository.findByRequestLocalInfo(requestLocalInfo, RequestRepository.Timestamp.class);
    }

}
