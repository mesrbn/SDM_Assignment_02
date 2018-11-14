package de.tuda.dmdb.storage;

import de.tuda.dmdb.storage.types.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteStorage extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( "DMDB-Storage" );
    suite.addTestSuite( TestPage.class );
    suite.addTestSuite( TestRecord.class );
    suite.addTestSuite( TestSQLInteger.class );
    suite.addTestSuite( MyTestSQLIntegerZero.class );
    suite.addTestSuite( MyTestSQLIntegerNegative.class );
    suite.addTestSuite( MyTestSQLIntegerPositive.class );
    suite.addTestSuite( TestSQLVarchar.class );
    suite.addTestSuite( MyTestSQLVarcharEmpty.class );
    suite.addTestSuite( MyTestSQLVarcharLong.class );
    return suite;
  }
}
