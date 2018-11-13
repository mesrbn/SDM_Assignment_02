package de.tuda.dmdb.storage.types.exercise;

import de.tuda.dmdb.storage.types.SQLVarcharBase;
import sun.text.normalizer.UTF16;

import java.io.UnsupportedEncodingException;
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
		byte[] bytes = value.getBytes();
		int index = 0;
		byte[] result = new byte[value.length() * 8];
		for (byte b : bytes) {
			for (int i=7; i>=0; i--) {
				if( ((1 << i) & b) != 0) {
					result[index] = 1;
					index ++;
				}

				else {
					result[index] = 0;
					index ++;
				}
			}
		}
		return result;
	}

	@Override
	public void deserialize(byte[] data) {
		//TODO: implement this method
		byte[] bytes = new byte[data.length / 8];
		int byteValue = 0;
		int j = 0;
		for (int i = data.length -1 ; i >= 0 ; i--) {
			byteValue = byteValue + data[i] * (data[i] << j);
			j = (++j) % 8 ;
			if (j == 0) {
				bytes[i / 8] = (byte) byteValue;
				byteValue = 0;
			}
		}
		this.value = new String(bytes);
	}
	
	@Override
	public SQLVarchar clone(){
		return new SQLVarchar(this.value, this.maxLength);
	}

}
