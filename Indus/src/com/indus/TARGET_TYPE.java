package com.indus;

public enum TARGET_TYPE {

	HER(1), HIS(2), SWEET_HOME(3);

	private int policy;

	private TARGET_TYPE(int c) {
		policy = c;
	}

	public int getTARGET_TYPE() {
		return policy;
	}
	
	public String getTARGET_TYPEName() {
		return this.name();
	}
	
	public int getTARGET_TYPEOrdinal() {
		return this.ordinal();
	}
	
	public static TARGET_TYPE getTARGET_TYPEForOrdinal(int ordinal){
		TARGET_TYPE vc = null;
		switch (ordinal) {
		case 1:
			vc = HER;
			break;
		case 2:
			vc = HIS;
			break;
		case 3:
			vc = SWEET_HOME;
			break;

		default:
			vc = HER;
			break;
		}
		return vc;
		
	}
}
