package net.sauertek.acusticus.record;

import redis.clients.jedis.Jedis;

public class RecordDaoRedis implements RecordDao {

    private Jedis jedis;
    
    public RecordDaoRedis(){	
	jedis = new Jedis("localhost");
    }

    public void close(){
	jedis.quit();
    }

    public Record getRecord(int id){
	return new Record();
    }
    
}
