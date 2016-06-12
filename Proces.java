import java.util.*;

public class Proces {

	int strony;
	ArrayList<Integer> odwolania;
	int licznikPomocniczy;
	int licznikPomocniczy2;
	
	public Proces(int strony, ArrayList<Integer> odwolania, int licznikPomocniczy, int licznikPomocniczy2)
	{
		this.strony = strony;
		this.odwolania = odwolania;
		this.licznikPomocniczy = licznikPomocniczy;
		this.licznikPomocniczy2 = licznikPomocniczy2;
	}
	
	public int get(int i)
	{
		return odwolania.get(i);
	}
	
	public void zwiekszLicznik()
	{
		licznikPomocniczy++;
	}
	
	public void zwiekszLicznik2()
	{
		licznikPomocniczy2++;
	}
	
	public void zerojLicznik()
	{
		licznikPomocniczy = 0;
	}
	
	public void zerojLicznik2()
	{
		licznikPomocniczy2 = 0;
	}
}
