import java.util.*;

public class CzestoscBledowStron {

	ArrayList<Proces> procesy = new ArrayList<>();
	ArrayList<Ramka> ramki = new ArrayList<>();
	ArrayList<Integer> przydzial = new ArrayList<>();
	int maxProg;
	int minProg;
	
	public CzestoscBledowStron(ArrayList<Proces> procesy, ArrayList<Ramka> ramki, ArrayList<Integer> przydzial, int maxProg, int minProg)
	{
		this.procesy = procesy;
		this.ramki = ramki;
		this.przydzial = przydzial;
		this.maxProg = maxProg;
		this.minProg = minProg;
	}
	
	public void wykonaj()
	{
		  int sumaB=0;
          for(int i = 0; i < procesy.size(); i++)
          {
            int przydzialPoczatkowy = 10;
            int bit = 0;
            int bit2 = 0;
            int brakStron = 0;
            boolean wykonano = false;
            for(int j = 0; j < procesy.get(i).odwolania.size(); j++)
            {
                if(procesy.get(i).licznikPomocniczy >= maxProg)
                {
                    przydzialPoczatkowy++;
                    bit = 0;
                    Proces pr = new Proces(procesy.get(i).strony, procesy.get(i).odwolania, bit, bit2);
                    procesy.set(i, pr);
                    ramki.get(przydzialPoczatkowy).licznikPomocniczy = 0;
                    ramki.get(przydzialPoczatkowy).numerStrony = 0;
                }
                if(procesy.get(i).licznikPomocniczy2 <= minProg)
                {
                    przydzialPoczatkowy--;
                    bit2=0;
                    Proces pr = new Proces(procesy.get(i).strony, procesy.get(i).odwolania, bit, bit2);
                    procesy.set(i, pr);
                    ramki.get(przydzialPoczatkowy + 1).licznikPomocniczy = 0;
                    ramki.get(przydzialPoczatkowy + 1).numerStrony = 0;
                }
                for(int p = 0; p < przydzialPoczatkowy && !wykonano; p++)
                {
                    if(procesy.get(i).get(j) == ramki.get(p).numerStrony)
                    {
                        bit2--;
                        Proces pr = new Proces(procesy.get(i).strony, procesy.get(i).odwolania, bit, bit2);
                        procesy.set(i, pr);
                        ramki.get(p).licznikPomocniczy = 0;
                        for(int k = 0; k < przydzialPoczatkowy ;k++)
                        {
                            if(procesy.get(i).get(j) != ramki.get(k).numerStrony)
                            {
                            	ramki.get(k).zwiekszLicznik();
                            }
                        }
                        wykonano = true;
                    }
                    if (ramki.get(p).numerStrony == 0)
                    {
                        bit++;
                        Proces pr = new Proces(procesy.get(i).strony, procesy.get(i).odwolania, bit, bit2);
                        procesy.set(i, pr);
                        ramki.get(p).numerStrony = procesy.get(i).get(j);
                        ramki.get(p).licznikPomocniczy = 0;
                        brakStron++;
                        wykonano = true;
                    }
                }
                if(!wykonano)
                {
                    int tem = 0;
                    int ind = 0;
                    for(int l = 0; l < przydzialPoczatkowy; l++)
                    {
                        if(ramki.get(l).licznikPomocniczy > tem)
                        {
                            tem = ramki.get(l).licznikPomocniczy;
                            ind = l;
                        }
                    }
                    bit++;
                    Proces pr = new Proces(procesy.get(i).strony, procesy.get(i).odwolania, bit, bit2);
                    procesy.set(i, pr);
					ramki.get(ind).numerStrony = procesy.get(i).get(j);
					ramki.get(ind).licznikPomocniczy = 0;
                    brakStron++;
                }
                wykonano = false;
            }
            System.out.println("Proces numer " + (i + 1) + ". Posiada " + procesy.get(i).strony + " stron. Spowodowa³ " + brakStron + " b³êdów stron.");
            sumaB += brakStron;
        }
        System.out.println("£¹cznie " + sumaB + " b³êdów stron.");
	}
	
}
