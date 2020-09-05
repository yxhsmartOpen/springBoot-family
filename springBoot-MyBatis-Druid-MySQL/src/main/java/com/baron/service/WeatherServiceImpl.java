package com.baron.service;

import com.baron.dao.Dao;
import com.baron.vo.weather.WeatherInfo;
import com.baron.weather.WeatherUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Baron
 * @date 2020/9/2 0:15
 */
@Service
public class WeatherServiceImpl implements WeatherService{

    @Resource
    private Dao dao;

    @Override
    public Integer insertList(String cityName) {
        String info = WeatherUtils.GetWeatherData(cityName);
        List<WeatherInfo> weatherInfos = WeatherUtils.GetWeather(info);
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        for (WeatherInfo weatherInfo : weatherInfos) {
            WeatherInfo weatherInfo1 = selectOne(weatherInfo.getDateId());
            if(null == weatherInfo1){
                weatherInfoList.add(weatherInfo);
            }
        }
        if (weatherInfoList.size() > 0){
            return dao.insertList(weatherInfoList);
        }
        return null;
    }

    @Override
    public Integer insertOne(String cityName) {
        String info = WeatherUtils.GetWeatherData(cityName);
        List<WeatherInfo> weatherInfos = WeatherUtils.GetWeather(info);
        Integer a = 0;
        for (WeatherInfo weatherInfo : weatherInfos) {
            WeatherInfo weatherInfo1 = selectOne(weatherInfo.getDateId());
            if (null != weatherInfo1 ){
                a =  update(weatherInfo);
            }else {
                System.out.println(weatherInfo.getCityName());
               a =  dao.insertOne(weatherInfo);
            }
        }
        return a ;
    }

    @Override
    public WeatherInfo selectOne(String dateId) {
        return dao.selectOneOrderByDateId(dateId);
    }

    @Override
    public Integer update(WeatherInfo weatherInfo) {
        return dao.updateOne(weatherInfo);
    }

}
