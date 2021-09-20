package BirthdayTrackerICTPRG418;


//class to construct data for a single person
public class BirthdayTrackerInfo {
    private String name;
    private String surname;
    private String month;
    private String day;
    private String likes;
    private String dislikes;

    //Default Constructor
    public BirthdayTrackerInfo(){
        name = "Name";
        surname = "Surname";
        month = "Month";
        day = "Day";
        likes = "Likes";
        dislikes = "Dislikes";

    }

    //Secondary Constructor
    public BirthdayTrackerInfo(String name_con, String surname_con, String month_con, String day_con, String likes_con, String dislikes_con){
        name = name_con;
        surname = surname_con;
        month = month_con;
        day = day_con;
        likes = likes_con;
        dislikes = dislikes_con;
    }

    //Method to set object values
    public void setBirthdayTrackerInfo(String nameset, String surnameset, String monthset, String dayset, String likeset, String dislikeset){
        name = nameset;
        surname = surnameset;
        month = monthset;
        day = dayset;
        likes = likeset;
        dislikes = dislikeset;

    }

    //Sets attribute of object(name) one at a time
    public void setBDName(String setname){
        name = setname;
    }

    //Sets attribute of object(surname) one at a time
    public void setBDSurname(String setsurname){
        surname = setsurname;
    }

    //Sets attribute of object(month) one at a time
    public void setBDMonth(String setmonth){
        month = setmonth;
    }

    //Sets attribute of object(day) one at a time
    public void setBDDay(String setday){
        day = setday;
    }

    //Sets attribute of object(likes) one at a time
    public void setBDLikes(String setlikes) {likes = setlikes; }

    //Sets attribute of object(dislikes) one at a time
    public void setBDDislike(String setdislikes) {dislikes = setdislikes; }

    //Below are all getters to return values of object
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getMonth(){
        return month;
    }

    public String getDay(){
        return day;
    }

    public String getLikes() { return likes; }

    public String getDislikes() { return dislikes; }

}
