package de.tuda.dmdb.storage.types;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import org.junit.Assert;

public class MyTestSQLIntegerZero extends TestCase {

    public void testSerializeDeserialize1(){
        // Additional test cases
        int myValue1 = 0;

        SQLInteger mySqlInt1A = new SQLInteger(myValue1);
        byte[] myContect1 = mySqlInt1A.serialize();

        SQLInteger mySqlInt1B = new SQLInteger();
        mySqlInt1B.deserialize(myContect1);

        Assert.assertEquals(mySqlInt1A.getValue(), mySqlInt1B.getValue());
    }
}
