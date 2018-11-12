package de.tuda.dmdb.storage.types.exercise;

import de.tuda.dmdb.storage.types.SQLVarcharBase;

import java.util.Arrays;

/**
 * SQL varchar value
 * @author cbinnig
 *
 */
public class SQLVarchar extends SQLVarcharBase {	

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with default value and max. length 
	 * @param maxLength
	 */
	public SQLVarchar(int maxLength){
		super(maxLength);

	}
	
	/**
	 * Constructor with string value and max. length 
	 * @param value
	 * @param maxLength
	 */
	public SQLVarchar(String value, int maxLength){
		super(value, maxLength);
	}
	
	@Override
	public byte[] serialize() {
		byte[] result = new byte[value.length() * 8];
		int count = 0;
		for (int index = 0; index < value.length(); index ++) {

			String s = value.substring(index, index + 1);

			for (int i=7; i>=0; i--) {
				byte[] test = s.getBytes();
				if( ((1 << i) & test[0]) != 0) {
					result[count] = 1;
					count ++;
				}

				else {
					result[count] = 0;
					count ++;
				}
			}
		}
		return result;
	}

	@Override
	public void deserialize(byte[] data) {
		//TODO: implement this method
		//this.value = ?
	}
	
	@Override
	public SQLVarchar clone(){
		return new SQLVarchar(this.value, this.maxLength);
	}

}
