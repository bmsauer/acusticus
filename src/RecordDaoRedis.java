package net.sauertek.acusticus.record;

import redis.clients.jedis.Jedis;
import net.sauertek.acusticus.settings.Settings;

public class RecordDaoRedis implements RecordDao {

    private Jedis jedis;
    
    public RecordDaoRedis(){
	Settings settings = Settings.getInstance();
	jedis = new Jedis(settings.REDIS_HOST);
    }

    public void close(){
	jedis.quit();
    }

    public Record getRecord(int id){
	return new Record();
    }
    
}
