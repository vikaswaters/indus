package com.indus;

import java.util.Comparator;

import com.indus.dao.hibernate.Country;

public class CountryComparator implements Comparator{
		public int compare(Object o1, Object o2)
		{
			if((o1 instanceof Country) && (o2 instanceof Country))
			{
				Country rtVO1 = (Country)o1;
				Country rtVO2 = (Country)o2;
				if(rtVO1.getCountryid().equals(rtVO2.getCountryid()))
					return  0;
				else if(rtVO1.getCountryid() < (rtVO2.getCountryid()))
					return -1;
				else
					return 1;
			}
			else
			{
				return o1.hashCode() - o2.hashCode();
			}
		}

		public boolean equals(Object o)
		{
			return (compare(this, o) == 0);
		}
	}
