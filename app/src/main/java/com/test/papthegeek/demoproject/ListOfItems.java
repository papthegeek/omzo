package com.test.papthegeek.demoproject;

/**
 * Created by PapTheGeek on 11/15/17.
 */

public class ListOfItems {

    public String head;
    public String desc;

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageURl() {
        return imageURl;
    }

    public String imageURl;

    public ListOfItems(String head, String desc, String imageURl) {
        this.head = head;
        this.desc = desc;
        this.imageURl = imageURl;
    }




}
