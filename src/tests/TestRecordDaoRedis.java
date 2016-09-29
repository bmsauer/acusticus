package net.sauertek.acusticus.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import com.fiftyonred.mock_jedis.*;
import java.util.Map;

import net.sauertek.acusticus.record.RecordDaoRedis;
import net.sauertek.acusticus.record.Record;
import net.sauertek.acusticus.exceptions.RecordNotFoundException;

public class TestRecordDaoRedis {
    private RecordDaoRedis test_subject;
    private MockJedis mock_jedis;
    
    @Before
    public void setUp(){
	test_subject = new RecordDaoRedis();
	mock_jedis = new MockJedis("test");
	test_subject.setJedis(mock_jedis);
    }

    @After
    public void tearDown(){
	test_subject.close();
    }

    @Test
    public void test_putRecord() {
	Record r = new Record();
	r.album = "Test Album";
	r.artist = "Test Artist";

	r.id = test_subject.putRecord(r);

	assertEquals(r.id, 1);
	Map<String,String> redis_record = mock_jedis.hgetAll("record:1:obj");
	assertEquals(redis_record.get("id"), "1");
	assertEquals(redis_record.get("album"), "Test Album");
	assertEquals(redis_record.get("artist"), "Test Artist");
    }
    
    @Test
    public void test_getRecord() throws RecordNotFoundException {
	Record r = new Record();
	r.album = "Test Album";
	r.artist = "Test Artist";
	r.id = test_subject.putRecord(r);
	
	Record r2 = test_subject.getRecord(1);
	assertEquals(r2.id, 1);
	assertEquals(r2.album, "Test Album");
	assertEquals(r2.artist, "Test Artist");
    }

    @Test(expected=RecordNotFoundException.class)
    public void test_getRecord_Notfound() throws RecordNotFoundException {
	Record r2 = test_subject.getRecord(1);
    }
}
