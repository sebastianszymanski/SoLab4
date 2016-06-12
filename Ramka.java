
public class Ramka {

	int numerStrony;
	int licznikPomocniczy;
	
	public Ramka(int numerStrony, int licznikPomocniczy)
	{
		this.numerStrony = numerStrony;
		this.licznikPomocniczy = licznikPomocniczy;
	}
	
	public void zwiekszLicznik()
	{
		licznikPomocniczy++;
	}
	
	public void zerojLicznik()
	{
		licznikPomocniczy = 0;
	}
	
}
