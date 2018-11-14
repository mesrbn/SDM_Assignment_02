package de.tuda.dmdb.storage.exercise;

import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.EnumSQLType;
import de.tuda.dmdb.storage.types.exercise.SQLInteger;
import de.tuda.dmdb.storage.types.exercise.SQLVarchar;


public class RowPage extends AbstractPage {

	/**
	 * Constructir for a row page with a given (fixed) slot size
	 * @param slotSize
	 */
	public RowPage(int slotSize) {
		super(slotSize);
	}
	
	@Override
	// Inserts a record at the specified slot-number. flag doInsert=true should insert while shifting
	// existing records, otherwise an in-place update should occur.
	// Exception is thrown if no space left (same as in insert(AbstractRecord))
	public void insert(int slotNumber, AbstractRecord record, boolean doInsert) {
		//TODO: implement this method
	}
	
	@Override
	// Inserts a record at the end of the current page and updates the slot-size if there is still space left,
	// otherwise throws an exception
	public int insert(AbstractRecord record) {
		//TODO: implement this method
		boolean canFit = this.recordFitsIntoPage(record);
		int numberOfAttributes = record.getValues().length;
		int index = 0;

		byte[] recInt = null;
		byte[] recVarChar = null;
		byte[] rec = new byte[12];
		for (int i = 0; i < numberOfAttributes; i++) {
			if (record.getValues()[i].getType() == EnumSQLType.SqlInteger) {
				recInt = record.getValues()[i].serialize();
				System.arraycopy(recInt, 0, rec, index, recInt.length);
				index += recInt.length;
			}
			else if (record.getValues()[i].getType() == EnumSQLType.SqlVarchar) {
				recVarChar = record.getValues()[i].serialize();
				byte[] metaPart1 = new SQLInteger(this.offsetEnd).serialize();
				System.arraycopy(metaPart1, 0, rec, index, metaPart1.length);
				index += metaPart1.length;
				byte[] metaPart2 = new SQLInteger(recVarChar.length).serialize();
				System.arraycopy(metaPart2, 0, rec, index, metaPart2.length);
				index += metaPart2.length;
			}
			else
				throw new RuntimeException("not suitable data type");
		}
		if (canFit) {
			System.arraycopy(rec, offset, this.data, 0, rec.length);
			System.arraycopy(recVarChar, offset, this.data, this.offsetEnd, recVarChar.length);
			this.offset += 12;
			this.offsetEnd -= recVarChar.length;
			this.numRecords++;
		} else
			throw new RuntimeException("There is not enough space");


		return numRecords;
	}

	@Override
	// Fills the passed record-reference with values from the Page. (The record-reference specifies the SQL-datatypes).
	// An Exception is thrown if the specified slot is empty.
	public void read(int slotNumber, AbstractRecord record){
		//TODO: implement this method
		SQLInteger sqlInteger;
		SQLVarchar sqlVarchar;
		int numberOfAttributes = record.getValues().length;
		for (int i = 0; i < numberOfAttributes; i++) {
			if (record.getValues()[i].getType() == EnumSQLType.SqlInteger) {
				sqlInteger = (SQLInteger) record.getValues()[i];
				byte[] elementInt = new byte[4];
				System.arraycopy(this.data, (slotNumber * 12) + (i*8), elementInt, 0, 4);
				sqlInteger.deserialize(elementInt);
			}
			// else it is varchar
			else {
				sqlVarchar = (SQLVarchar) record.getValues()[i];
				byte[] metaPart1 = new byte[4];
				System.arraycopy(this.data, (slotNumber * 12) + (i*4), metaPart1, 0, 4);
				byte[] metaPart2 = new byte[4];
				System.arraycopy(this.data, (slotNumber * 12) + (i*4) + 4, metaPart2, 0, 4);

				// calculate the length of the varchar
				sqlInteger = new SQLInteger();
				sqlInteger.deserialize(metaPart2);
				int varCharSize = sqlInteger.getValue();
				// calculate the offset of the varchar in page
				sqlInteger.deserialize(metaPart1);
				int varCharBeginAddress = sqlInteger.getValue();

				byte[] elementVarChar = new byte[varCharSize];
				System.arraycopy(this.data, varCharBeginAddress, elementVarChar, 0, varCharSize);
				sqlVarchar.deserialize(elementVarChar);
				SQLVarchar sv = (SQLVarchar) record.getValues()[i];
				sv.deserialize(elementVarChar);
			}
		}
	}
}
