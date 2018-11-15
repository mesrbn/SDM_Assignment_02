package de.tuda.dmdb.storage;

import de.tuda.dmdb.TestCase;
import de.tuda.dmdb.storage.exercise.RowPage;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import de.tuda.dmdb.storage.types.exercise.SQLVarchar;
import org.junit.Assert;

public class MyTestPageMultiOperation extends TestCase {
    public void testInsert1Record(){
        //insert record1
        AbstractRecord r1 = new Record(2);
        r1.setValue(0, new SQLInteger(1));
        r1.setValue(1, new SQLVarchar("Mehran", 10));

        //insert record2
        AbstractRecord r2 = new Record(2);
        r2.setValue(0, new SQLInteger(2));
        r2.setValue(1, new SQLVarchar("Ali", 10));

        //insert record3
        AbstractRecord r3 = new Record(2);
        r3.setValue(0, new SQLInteger(3));
        r3.setValue(1, new SQLVarchar("Reza", 10));

        //insert record4
        AbstractRecord r4 = new Record(2);
        r4.setValue(0, new SQLInteger(4));
        r4.setValue(1, new SQLVarchar("Test", 10));

        AbstractPage p = new RowPage(r1.getFixedLength());
        p.insert(r1);
        p.insert(r2);
        //p.insert(r3);
        //p.insert(r4);


        //read record1
        AbstractRecord r1cmp = new Record(2);
        r1cmp.setValue(0, new SQLInteger());
        r1cmp.setValue(1, new SQLVarchar(10));
        p.read(0, r1cmp);

        //read record2
        AbstractRecord r2cmp = new Record(2);
        r2cmp.setValue(0, new SQLInteger());
        r2cmp.setValue(1, new SQLVarchar(10));
        p.read(1, r2cmp);

        //read record3
        AbstractRecord r3cmp = new Record(2);
        r3cmp.setValue(0, new SQLInteger());
        r3cmp.setValue(1, new SQLVarchar(10));
        p.read(2, r3cmp);

        //read record4
        AbstractRecord r4cmp = new Record(2);
        r4cmp.setValue(0, new SQLInteger());
        r4cmp.setValue(1, new SQLVarchar(10));
        p.read(3, r4cmp);

        //compare
        Assert.assertEquals(r1, r1cmp);
        Assert.assertEquals(r2, r2cmp);
        //Assert.assertEquals(r3, r3cmp);
        //Assert.assertEquals(r4, r4cmp);


        // Insert record and Replace record
        p.insert(0, r3, false);
        p.read(0, r1cmp);
        p.insert(1, r4, false);
        p.read(1, r2cmp);

        Assert.assertEquals(r3, r1cmp);
        Assert.assertEquals(r4, r2cmp);

        // Insert record and Shift record
        /*
        p.insert(0, r2, true);
        p.insert(0, r1, true);

        r2cmp.setValue(0, new SQLInteger());
        r2cmp.setValue(1, new SQLVarchar(10));
        p.read(1, r2cmp);
        r1cmp.setValue(0, new SQLInteger());
        r1cmp.setValue(1, new SQLVarchar(10));
        p.read(0, r1cmp);

        r3cmp.setValue(0, new SQLInteger());
        r3cmp.setValue(1, new SQLVarchar(10));
        p.read(2, r3cmp);

        r4cmp.setValue(0, new SQLInteger());
        r4cmp.setValue(1, new SQLVarchar(10));
        p.read(3, r4cmp);

        Assert.assertEquals(r1, r1cmp);
        Assert.assertEquals(r2, r2cmp);
        Assert.assertEquals(r3, r3cmp);
        Assert.assertEquals(r4, r4cmp);
        */
    }
}
