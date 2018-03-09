package com.test.papthegeek.demoproject.senproCloud;

import java.util.HashMap;

/**
 * Created by PapTheGeek on 11/25/17.
 */

public class MyConfiguration {

    public static HashMap getMyConfigs()
    {
        HashMap config = new HashMap();
        config.put("cloud_name" , "senpro");

        return config;
    }
}
