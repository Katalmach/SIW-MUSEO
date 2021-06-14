	package it.uniroma3.siw.spring.model;


	import java.util.Comparator;

	public class ComparatoreOperePerArtista implements Comparator<Opera> {

		@Override
		public int compare(Opera o1, Opera o2) {
			int cmp =o1.getArtista().getNome().compareTo(o2.getArtista().getNome());
			if(cmp==0)
				return o1.getArtista().getCognome().compareTo(o2.getArtista().getCognome());
			return  cmp;
		}

	}
