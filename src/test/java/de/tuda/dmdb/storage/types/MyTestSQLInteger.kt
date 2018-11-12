package de.tuda.dmdb.storage.types

import org.junit.Assert

import de.tuda.dmdb.storage.types.exercise.SQLInteger
import de.tuda.dmdb.TestCase

class MyTestSQLInteger: TestCase() {

    fun testSerializeDeserialize1() {
        // Additional test cases
        val myValue1 = 71

        val mySqlInt1A = SQLInteger(myValue1)
        val myContect1 = mySqlInt1A.serialize()

        val mySqlInt1B = SQLInteger()
        mySqlInt1B.deserialize(myContect1)

        val param1 = mySqlInt1A.getValue().toLong()
        val param2 = mySqlInt1B.getValue().toLong()
        Assert.assertEquals(param1, param2)
    }
}