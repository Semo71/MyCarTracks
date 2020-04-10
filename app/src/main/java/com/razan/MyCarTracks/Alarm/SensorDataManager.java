package com.razan.MyCarTracks.Alarm;

import com.razan.MyCarTracks.SensorDataModel;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsManager;


/** Manager Class to save Sensor Data in Shared Preference **/
public class SensorDataManager {

    //Store SensorDataModel for Filter in Shared Preferences
    public static void setFilter(SensorDataModel sensorDataModel){
        SharedPrefsManager.getInstance().setCollection("Filter",sensorDataModel);
    }

    //Get SensorDataModel for Filters from Shared Preferences
    public static SensorDataModel getFilter(){
        if (SharedPrefsManager.getInstance().getCollection("Filter",SensorDataModel.class)!=null)
            return SharedPrefsManager.getInstance().getCollection("Filter",SensorDataModel.class);
        else
            return new SensorDataModel();
    }

    //Store SensorDataModel for Speed Limit in Shared Preferences
    public static void setSpeedLimit(SensorDataModel sensorDataModel){
        SharedPrefsManager.getInstance().setCollection("SpeedLimit",sensorDataModel);
    }

    //Get SensorDataModel for Speed Limit from Shared Preferences
    public static SensorDataModel getSpeedLimit(){
        if (SharedPrefsManager.getInstance().getCollection("SpeedLimit",SensorDataModel.class)!=null)
            return SharedPrefsManager.getInstance().getCollection("SpeedLimit",SensorDataModel.class);
        else
            return new SensorDataModel();
    }

}
