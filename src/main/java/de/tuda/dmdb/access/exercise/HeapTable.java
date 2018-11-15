package de.tuda.dmdb.access.exercise;

import de.tuda.dmdb.storage.AbstractPage;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.access.RecordIdentifier;
import de.tuda.dmdb.access.HeapTableBase;
import de.tuda.dmdb.storage.PageManager;

public class HeapTable extends HeapTableBase {

	/**
	 * 
	 * Constructs table from record prototype
	 * @param prototypeRecord
	 */
	public HeapTable(AbstractRecord prototypeRecord) {
		super(prototypeRecord);
	}

	@Override
	// Inserts a record in the last Page of the Heap-Table. If no
	// space is left in a page, a new page is created.
	public RecordIdentifier insert(AbstractRecord record) {

		AbstractPage myLastPage = lastPage;
		RecordIdentifier recordIdentifier = new RecordIdentifier(0, 0);

		int sizeOfRecord = (record.getFixedLength() + record.getVariableLength());
		int freeSpace = myLastPage.getFreeSpace();

		if (sizeOfRecord >= freeSpace) {
			myLastPage = PageManager.createDefaultPage(record.getFixedLength());
			pages.put(myLastPage.getPageNumber(), myLastPage);
			recordIdentifier.setPageNumber(myLastPage.getPageNumber());
			recordIdentifier.setSlotNumber(myLastPage.insert(record));
		} else {
			recordIdentifier.setPageNumber(myLastPage.getPageNumber());
			recordIdentifier.setSlotNumber(myLastPage.insert(record));
		}

		return recordIdentifier;
	}

	@Override
	// Returns a record by its pageNumber & slotNumber.
	public AbstractRecord lookup(int pageNumber, int slotNumber) {

		AbstractPage page = pages.get(pageNumber);
		AbstractRecord record = prototype.clone();
		page.read(slotNumber, record);

		return record;
	}

}
