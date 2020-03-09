package com.razan.MyCarTracks.Alarm;

import com.google.gson.Gson;
import com.razan.MyCarTracks.SharedPrefsManager.SharedPrefsManager;


/** Manager Class to save Services Dates in Shared Preference **/
public class ServicesDateManager {

    /** Method to Service in Shared Preference, 2 Arguments ServiceModel and Key for the Service Name **/
    public static void setServicesModel(ServicesModel servicesModel, String key){
        SharedPrefsManager.getInstance().setCollection(key,servicesModel);
    }

    /** Method to Retrieve the Service from Shared Preference by inserting it's name **/
    public static ServicesModel getServicesModel(String key){
        if (SharedPrefsManager.getInstance().getCollection(key,ServicesModel.class)!=null)
            return SharedPrefsManager.getInstance().getCollection(key,ServicesModel.class);
        else
            return new ServicesModel();
    }

}
