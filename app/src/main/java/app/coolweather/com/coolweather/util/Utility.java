package app.coolweather.com.coolweather.util;

import android.text.TextUtils;

import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.CoolWeatherDB;
import app.coolweather.com.coolweather.model.County;
import app.coolweather.com.coolweather.model.Province;

/**
 * Created by zhy on 2015/7/31.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的升级数据
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces != null && allProvinces.length > 0){
                for(String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceCode(array[1]);
                    //将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities != null && allCities.length > 0){
                for(String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityName(array[0]);
                    city.setCityCode(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到city类
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if(allCounties != null && allCounties.length > 0){
                for(String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyName(array[0]);
                    county.setCountyCode(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存储到County类
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
