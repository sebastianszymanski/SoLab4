import java.util.*;

public class RownyIProporcjonalny {

	ArrayList<Proces> procesy = new ArrayList<>();
	ArrayList<Ramka> ramki = new ArrayList<>();
	ArrayList<Integer> przydzial = new ArrayList<>();
	
	public RownyIProporcjonalny(ArrayList<Proces> procesy, ArrayList<Ramka> ramki, ArrayList<Integer> przydzial)
	{
		this.procesy = procesy;
		this.ramki = ramki;
		this.przydzial = przydzial;
	}
	
	public void wykonaj()
	{
		int przydz = przydzial.get(0);
		int zakresPocz = 0;
		int zakresKon = przydz;
		int sumaBrakuStron = 0;
		for (int i = 0; i < procesy.size(); i++)
		{
			int brakStron = 0;
			boolean wykonano = false;
			if (i != 0)
			{
				przydz = przydzial.get(i);
				zakresPocz = zakresKon;
				zakresKon += przydz;
			}
			for (int j = 0; j < procesy.get(i).odwolania.size(); j++)
			{
				for (int p = zakresPocz; p < zakresKon && !wykonano; p++)
				{
					if (procesy.get(i).get(j) == ramki.get(p).numerStrony)
					{
						ramki.get(p).zerojLicznik();
						for (int k = 0; k < zakresKon; k++)
						{
							if (procesy.get(i).get(j) != ramki.get(k).numerStrony)
							{
								ramki.get(k).zwiekszLicznik();
							}
						}
						wykonano = true;
					}
					if (ramki.get(p).numerStrony == 0)
					{
						ramki.get(p).numerStrony = procesy.get(i).get(j);
						brakStron++;
						wykonano = true;
					}
				}
				if (!wykonano)
				{
					int tem = 0;
					int ind = 0;
					for (int l = 0; l < zakresKon; l++)
					{
						if (ramki.get(l).licznikPomocniczy > tem)
						{
							tem = ramki.get(l).licznikPomocniczy;
							ind = l;
						}
					}
					ramki.get(ind).numerStrony = procesy.get(i).get(j);
					ramki.get(ind).licznikPomocniczy = 0;
					brakStron++;
				}
				wykonano = false;
			}
			System.out.println("Proces numer " + (i + 1) + ". Posiada " + procesy.get(i).strony + " stron oraz " + przydz + " przydzielonych ramek. Spowodowa³ " + brakStron + " b³êdów stron.");
			sumaBrakuStron += brakStron;
		}
		System.out.println("£¹cznie " + sumaBrakuStron + " b³êdów stron.");
	}
}
