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

		byte[] result = new byte[32];

		for (int i=31; i>=0; i--) {
			if( ((1 << i) & value) != 0) {
				System.out.print(1);
				result[i] = 1;
			}

			else {
				System.out.print(0);
				result[i] = 0;
			}
		}

		return result;
	}

	@Override
	public void deserialize(byte[] data) {

		int result = 0;

		for (int i = data.length - 1; i >= 0; i--) {
			result = result + data[i] * (data[i] << i);
		}

		this.value = result;
	}
	
	
	@Override
	public SQLInteger clone(){
		return new SQLInteger(this.value);
	}

}
