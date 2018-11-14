package de.tuda.dmdb.storage.types.exercise;

import de.tuda.dmdb.storage.types.SQLIntegerBase;

/**
 * SQL integer value
 * @author cbinnig
 *
 */
public class SQLInteger extends SQLIntegerBase {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with default value
	 */
	public SQLInteger(){
		super();
	}
	
	/**
	 * Constructor with value
	 * @param value Integer value
	 */
	public SQLInteger(int value){
		super(value);
	}
	
	@Override
	public byte[] serialize() {
		byte[] result = new byte[4];
		int mask = 0;
		for (int i = 3; i >= 0; i--) {
			result[i] = (byte) ((0xFF & value >>> mask));
			mask += 8;
		}

		return result;
	}

	@Override
	public void deserialize(byte[] data) {

		this.value = (data[0]<<24)&0xff000000|
					(data[1]<<16)&0x00ff0000|
					(data[2]<< 8)&0x0000ff00|
					(data[3]<< 0)&0x000000ff;
	}
	
	
	@Override
	public SQLInteger clone(){
		return new SQLInteger(this.value);
	}

}
