package de.tuda.dmdb.storage.exercise;

import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;


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

		int freeSpace = this.getFreeSpace();
		int numberOfRecords = this.getNumRecords();
		int pageNumner = this.getPageNumber();
		boolean canFit = this.recordFitsIntoPage(record);
		int minNumRecordsFitPage = this.estimateRecords(record);
		int fixedLength = record.getFixedLength();
		int varLength = record.getVariableLength();
		int slotSize = this.slotSize;
		byte[] rec1 = record.getValues()[0].serialize();
		byte[] rec2 = record.getValues()[1].serialize();

		byte[] rec = new byte[rec1.length + rec2.length];
		System.arraycopy(rec1, 0, rec, 0, rec1.length);
		System.arraycopy(rec2, 0, rec, rec1.length, rec2.length);


		if (canFit) {
			System.arraycopy(rec, 0, this.data, 0, rec.length);
			this.offset = 12;
			this.numRecords++;
		} else
			throw new RuntimeException("There is not enough space");

		return 0;
	}

	@Override
	// Fills the passed record-reference with values from the Page. (The record-reference specifies the SQL-datatypes).
	// An Exception is thrown if the specified slot is empty.
	public void read(int slotNumber, AbstractRecord record){
		//TODO: implement this method
	}
}
