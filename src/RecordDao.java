package net.sauertek.acusticus.record;

import net.sauertek.acusticus.exceptions.RecordNotFoundException;

public interface RecordDao{
    public Record getRecord(int id) throws RecordNotFoundException;
    public long putRecord(Record r);
    public void close();
}
