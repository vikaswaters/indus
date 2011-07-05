package com.indus;

import com.indus.ITEM_SIZE;

public enum ITEM_SIZE {
	SMALL(0), MEDIUM(1), LARGE(2), XL(3), XXL(4), XXXL(5);

	private int policy;

	private ITEM_SIZE(int c) {
		policy = c;
	}

	public int getITEM_SIZE() {
		return policy;
	}
	
	public String getITEM_SIZEName() {
		return this.name();
	}
	
	public int getITEM_SIZEOrdinal() {
		return this.ordinal();
	}
	
	public static ITEM_SIZE getITEM_SIZEForOrdinal(int ordinal){
		ITEM_SIZE vc = null;
		switch (ordinal) {
		case 0:
			vc = SMALL;
			break;		
		case 1:
			vc = MEDIUM;
			break;
		case 2:
			vc = LARGE;
			break;
		case 3:
			vc = XL;
			break;
		case 4:
			vc = XXL;
			break;
		case 5:
			vc = XXXL;
			break;
		default:
			vc = LARGE;
			break;
		}
		return vc;
		
	}
}
