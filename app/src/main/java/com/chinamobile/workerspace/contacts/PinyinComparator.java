package com.chinamobile.workerspace.contacts;

import java.util.Comparator;




public class PinyinComparator implements Comparator<UserItem>
{

	public int compare(UserItem o1, UserItem o2)
	{
		if (o1.getM_py_2().substring(0, 1).equals("@") || o2.getM_py_2().substring(0, 1).equals("#")) 
			return -1;
		else if (o1.getM_py_2().substring(0, 1).equals("#") || o2.getM_py_2().substring(0, 1).equals("@")) 
			return 1;
	    else 
			return o1.getM_py_2().substring(0, 1).compareTo(o2.getM_py_2().substring(0, 1));
		
	}

}
