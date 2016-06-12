import java.util.*;

public class ModelStrefowy {

	ArrayList<Proces> procesy = new ArrayList<>();
	ArrayList<Ramka> ramki = new ArrayList<>();
	ArrayList<Integer> przydzial = new ArrayList<>();
	int oknoZbioruR;
	int zrSize;
	
	public ModelStrefowy(ArrayList<Proces> procesy, ArrayList<Ramka> ramki, ArrayList<Integer> przydzial, int oknoZbioruR, int zrSize)
	{
		this.procesy = procesy;
		this.ramki = ramki;
		this.przydzial = przydzial;
		this.oknoZbioruR = oknoZbioruR;
		this.zrSize = zrSize;
	}
	
	public void wykonaj()
	{
		 int sumaB = 0;
	        for(int i = 0; i < procesy.size() ; i++)
	        {
	            int brakStron = 0;
	            boolean wykonano = false;
	            ArrayList<Integer> zr = new ArrayList<Integer>();
	            ArrayList<Integer> iloscR = new ArrayList<Integer>();
	            int iloscPrzydz = procesy.get(i).odwolania.size() / zrSize;
	            int przydz = 0;
	            int przydz2 = zrSize;
	            for(int ilePrzydz = 0; ilePrzydz < iloscPrzydz; ilePrzydz++)
	            {
	                for(int j = przydz; j < przydz2; j++)
	                {
	                    if(zr.size()==0)
	                    {
	                        zr.add(procesy.get(i).get(j));
	                    }
	                    else{
	                        boolean znaleziono = false;
	                        for(int k = 0; k < zr.size(); k++)
	                        {
	                             if(zr.get(k) == procesy.get(i).get(j))
	                             {
	                                 znaleziono = true;
	                                 break;
	                             }
	                        }
	                        if(znaleziono == false)
	                        {
	                            zr.add(procesy.get(i).get(j));
	                        }
	                    }
	                }
	                iloscR.add(zr.size());
	                zr.clear();
	                przydz += zrSize;
	                przydz2 += zrSize;
	            }
	            int hp = 0;
	            int hp2 = 0;
	            przydz = 0;
	            przydz2 = iloscR.get(hp2);
	            for(int j = 0; j < procesy.get(i).odwolania.size(); j++)
	            {
	                if(hp > zrSize)
	                {
	                    hp = 0;
	                    hp2++;
	                    przydz = iloscR.get(hp2);
	                }
	                for(int p = 0; p < przydz && !wykonano ;p++)
	                {
	                    if(procesy.get(i).get(j) == ramki.get(p).numerStrony)
	                    {
	                        ramki.get(p).licznikPomocniczy = 0;
	                        for(int k = 0; k < przydz2 ; k++)
	                        {
	                            if(procesy.get(i).get(j)!= ramki.get(k).numerStrony)
	                            {
	                            	ramki.get(k).zwiekszLicznik();
	                            }
	                        }
	                        wykonano = true;
	                    }
	                    if(ramki.get(p).numerStrony == 0)
	                    {
	                        ramki.get(p).numerStrony = procesy.get(i).get(j);
	                        ramki.get(p).licznikPomocniczy = 0;
	                        brakStron++;
	                        wykonano = true;
	                    }
	                }
	                if(wykonano == false)
	                {
	                    int tem=0;
	                    int ind=0;
	                    for(int l = 0; l < przydz2; l++)
	                    {
	                        if(ramki.get(l).licznikPomocniczy > tem)
	                        {
	                            tem =ramki.get(l).licznikPomocniczy;
	                            ind = l;
	                        }
	                    }
						ramki.get(ind).numerStrony = procesy.get(i).get(j);
						ramki.get(ind).licznikPomocniczy = 0;
	                    brakStron++;
	                }
	                wykonano = false;
	                hp++;
	            }
	            System.out.println("Proces numer " + (i + 1) + ". Posiada " + procesy.get(i).strony + " stron. Spowodowa³ " + brakStron + " b³êdów stron.");
	            sumaB += brakStron;
	        }
	        System.out.println("£¹cznie "+sumaB+" b³êdów stron");
	}
	
}
