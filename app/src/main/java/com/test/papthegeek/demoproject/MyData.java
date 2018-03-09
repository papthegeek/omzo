package com.test.papthegeek.demoproject;

/**
 * Created by PapTheGeek on 11/15/17.
 */

public class MyData {
    private int id;
    private String description, image_link;
    private String eventTitle;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String ticketPrice;
    private String nbrOfTicketsLeft;


    public MyData(int id, String description, String image_link, String eventTitle, String eventDate, String eventStartTime, String eventEndTime, String ticketPrice, String nbrOfTicketsLeft) {
        this.id = id;
        this.description = description;
        this.image_link = image_link;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.ticketPrice = ticketPrice;
        this.nbrOfTicketsLeft = nbrOfTicketsLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getEventTitle() { return eventTitle; }

    public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }

    public String getEventDate() { return eventDate; }

    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getEventStartTime() { return eventStartTime; }

    public void setEventStartTime(String eventStartTime) { this.eventStartTime = eventStartTime; }

    public String getEventEndTime() { return eventEndTime; }

    public void setEventEndTime(String eventEndTime) { this.eventEndTime = eventEndTime; }

    public String getTicketPrice() { return ticketPrice; }

    public void setTicketPrice(String ticketPrice) { this.ticketPrice = ticketPrice; }

    public String getNbrOfTicketsLeft() { return nbrOfTicketsLeft; }

    public void setNbrOfTicketsLeft(String nbrOfTicketsLeft) { this.nbrOfTicketsLeft = nbrOfTicketsLeft; }
}
