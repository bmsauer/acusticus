package net.sauertek.acusticus.record;

import java.util.Calendar;
import java.util.HashMap;

public class Record{
    public long id;
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
	id = -1;
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

    public HashMap mapify(){
	HashMap m = new HashMap(); 
	m.put("id", String.valueOf(id));
	m.put("album", album);
	m.put("artist", artist);
	m.put("review", review);
	m.put("island", String.valueOf(island));
	m.put("date_created", "");
	m.put("date_updated", "");
	m.put("user_id", String.valueOf(user_id));
	m.put("recommender_id", String.valueOf(recommender_id));
	m.put("listened_to", String.valueOf(listened_to));
	return m;
    }
    
}
