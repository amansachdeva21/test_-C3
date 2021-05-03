package test;

import java.util.StringTokenizer;

class Nl {
	private String key;
	private Nl val;

	public Nl(String key, Nl val) {
		this.key = key;
		this.val = val;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Nl getVal() {
		return val;
	}

	public void setVal(Nl val) {
		this.val = val;
	}

	public String toString() {
		if (this.val == null) {
			return key;
		} else {
			return key + ":[" + val.toString() + "]";
		}
	}
}

public class NestObjectFindVal {
	private static Nl createNestedObject(String v) {
		if (v.indexOf(":") < 0) {
			return new Nl(v, null);
		} else {
			String key = v.substring(1, v.indexOf(":"));
			String val = v.substring(v.indexOf(":") + 1, v.length() - 1);
			return new Nl(key, createNestedObject(val));
		}
	}

	public static void main(String[] args) {
		String object = args[0].replaceAll("\"","");
		System.out.println("nested object : " + object + ", key : " + args[1]);
		Nl nestedObject = createNestedObject(object);
		String key = args[1];
		Nl resp = getValFromNestedObject(nestedObject, key);
		if (resp != null) {
			System.out.println(resp.toString());
		} else {
			System.out.println("key doesn't exist");
		}
	}

	private static Nl getValFromNestedObject(Nl obj, String key) {
		Nl objToReturn = obj;
		StringTokenizer st = new StringTokenizer(key, "/");
		while (st.hasMoreTokens()) {
			String k = st.nextToken();
			if (k.equals(objToReturn.getKey())) {
				objToReturn = objToReturn.getVal();
			} else {
				return null;
			}
		}
		return objToReturn;
	}
}
