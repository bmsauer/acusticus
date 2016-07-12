package net.sauertek.acusticus.record;

import java.util.Calendar;

public class Record{

    public String album;
    public String artist;
    public String review;
    public boolean island;
    private Calendar date_created;
    private Calendar date_updated;
    public int user_id;
    public int recommender_id;
    public boolean listened_to;

    public Record(){
	album = "";
	artist = "";
	review = "";
	island = false;
	date_created = null;
	date_updated = null;
	user_id = -1;
	recommender_id = -1;
	listened_to = false;
    }
    
}
