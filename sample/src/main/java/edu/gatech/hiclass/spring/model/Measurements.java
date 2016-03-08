package edu.gatech.hiclass.spring.model;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Hashtable;

public abstract class Measurements {

	public String date;
	public String source;

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public int getValue() {
		return 0;
	}

	public String getKey() {
		return getDate() + getSource();
	}

	public static abstract class CombinedMeasurements extends Measurements {

		public int value1;
		public int value2;

		public int getValue1() {
			return value1;
		}
		public void setValue1(int value1) {
			this.value1 = value1;
		}
		public int getValue2() {
			return value2;
		}
		public void setValue2(int value2) {
			this.value2 = value2;
		}

	}

	@SuppressWarnings("unchecked")
	public static <T extends Measurements> T[] cleanData(T[] measurements) {
		Hashtable<String, T> table = new Hashtable<String, T>();
		for(T o : measurements) {
			if(o.getKey() != null)
				table.put(o.getKey(), o);
		}
		Collection<T> values = table.values();
		T[] ts = (T[]) Array.newInstance((measurements[0]).getClass(), values.size());
		T[] cleanData = values.toArray(ts);
		return cleanData;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Measurements, U extends CombinedMeasurements> U[] combineData(T[] glucose, T[] oxygen, U[] combinedData) throws Exception {
		Hashtable<String, CombinedMeasurements> bloodData = new Hashtable<String, CombinedMeasurements>();
		Class<? extends CombinedMeasurements> combinedDataClass = (combinedData[0]).getClass();
		for(Measurements data : glucose) {
			CombinedMeasurements blood = combinedDataClass.newInstance();
			blood.setDate(data.getDate());
			blood.setSource(data.getSource());
			blood.setValue1(data.getValue());
			bloodData.put(data.getDate(), blood);
		}
		for(Measurements data : oxygen) {
			CombinedMeasurements blood = bloodData.get(data.getDate());
			if (blood == null) {
				blood = combinedDataClass.newInstance();
				blood.setDate(data.getDate());
				blood.setSource(data.getSource());
			}
			blood.setValue2(data.getValue());
			bloodData.put(data.getDate(), blood);
		}
		Collection<CombinedMeasurements> values = bloodData.values();
		U[] ts = (U[]) Array.newInstance(combinedDataClass, values.size());
		U[] cleanData = values.toArray(ts);
		return cleanData;
	}

}
