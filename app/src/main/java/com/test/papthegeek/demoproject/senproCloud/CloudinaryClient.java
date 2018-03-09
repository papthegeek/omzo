package com.test.papthegeek.demoproject.senproCloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

/**
 * Created by PapTheGeek on 11/25/17.
 */

public class CloudinaryClient {

    public static String getURL(String imgPath)
    {
        Cloudinary cloud = new Cloudinary(MyConfiguration.getMyConfigs());
        Transformation trans = new Transformation();
        trans.radius(60);

        return cloud.url().transformation(trans).generate(imgPath);
    }
}
