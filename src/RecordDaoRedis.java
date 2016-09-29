package net.sauertek.acusticus.record;

import redis.clients.jedis.Jedis;
import net.sauertek.acusticus.settings.Settings;
import java.util.Map;
import java.util.HashMap;

import net.sauertek.acusticus.exceptions.RecordNotFoundException;

public class RecordDaoRedis implements RecordDao {

    private Jedis jedis;
    
    public RecordDaoRedis(){
	Settings settings = new Settings();
	setJedis(new Jedis(settings.REDIS_HOST));
    }

    public void setJedis(Jedis j){
	jedis = j;
    }

    public void close(){
	jedis.quit();
    }

    public long putRecord(Record r){
	HashMap m = r.mapify();
	long id = jedis.incr("acusticus:new_record_id");
	m.put("id", String.valueOf(id));
	jedis.hmset("record:"+Long.toString(id)+":obj", m);
	return id;
    }
    
    public Record getRecord(int id) throws RecordNotFoundException{
	Map<String,String> redis_record = jedis.hgetAll("record:"+Integer.toString(id)+":obj");
	if(redis_record.isEmpty()){
	    throw new RecordNotFoundException("Could not find record with id: " + String.valueOf(id));
	}
	Record record = new Record();
	record.id = Long.parseLong(redis_record.get("id"));
	record.album = redis_record.get("album");
	record.artist = redis_record.get("artist");
	return record;
    }
    
}
