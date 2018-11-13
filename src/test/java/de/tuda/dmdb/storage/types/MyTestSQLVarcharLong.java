package de.tuda.dmdb.storage.types;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.storage.types.exercise.SQLVarchar;
import org.junit.Assert;

public class MyTestSQLVarcharLong extends TestCase {
    public void testSerializeDeserialize1(){
        String value = "Longgggggggggggggg Textttttttttttttttttttttttt";

        SQLVarchar sqlVarchar = new SQLVarchar(value, 255);
        byte[] data = sqlVarchar.serialize();

        SQLVarchar sqlVarchar2 = new SQLVarchar(255);
        sqlVarchar2.deserialize(data);

        Assert.assertEquals(sqlVarchar.getValue(), sqlVarchar2.getValue());
    }
}
