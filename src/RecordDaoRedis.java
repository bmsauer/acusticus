package net.sauertek.acusticus.record;

import redis.clients.jedis.Jedis;
import net.sauertek.acusticus.settings.Settings;
import java.util.Map
    
public class RecordDaoRedis implements RecordDao {

    private Jedis jedis;
    
    public RecordDaoRedis(){
	Settings settings = new Settings();
	jedis = new Jedis(settings.REDIS_HOST);
    }

    public void close(){
	jedis.quit();
    }

    public Record getRecord(int id){
	HashMap<String,String> redis_record = jedis.hgetAll("record:"+Integer.toString(id)+":obj");
	Record record = new Record();
	record.album = redis_record.get("album");
	record.artist = redis_record.get("artist");
	return record;
    }
    
}
