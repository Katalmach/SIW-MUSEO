package it.uniroma3.siw.spring.model;


import java.util.Comparator;

public class ComparatoreOperePerAnno implements Comparator<Opera> {

	@Override
	public int compare(Opera o1, Opera o2) {
		int cmp =o1.getAnno()-o2.getAnno();
		if(cmp==0)
			return o1.getAnno().compareTo(o2.getAnno());
		return o1.getAnno()-o2.getAnno();
	}

}