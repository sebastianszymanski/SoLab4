import java.util.*;
public class Wykonaj {
	
	public static void clear(ArrayList<Ramka> ram)
	{
		for (int i = 0; i < ram.size(); i++)
		{
			ram.get(i).numerStrony = 0;
			ram.get(i).licznikPomocniczy = 0;
		}
	}
	
	public static void przydzialRowny(ArrayList<Integer> przydzial, int iloscRamek, int iloscProcesow)
	{
		 for (int i = 0; i < iloscProcesow; i++)
         {
             int p= iloscRamek / iloscProcesow;
             przydzial.add(p);
         }
	}
	
	
	public static void przydzialProporcjonalny(ArrayList<Proces> procesy, ArrayList<Integer> przydzial, int iloscRamek, int iloscProcesow)
	{
		int suma = 0;
        for (int i = 0; i < iloscProcesow; i++)
        {
            suma += procesy.get(i).strony;
        }
        for (int j = 0; j < iloscProcesow; j++)
        {
            double p = (procesy.get(j).strony * iloscRamek) / suma;
            przydzial.add((int)p);
        }
	}
	
	public static void main(String[] args) {
		
		Random gen = new Random();
		int iloscRamek = 100;
		int iloscProcesow = 10;
		int iloscOdwolan = 100;
		int maxProg = 2;
		int minProg = -15;
		int oknoZbioruR = 8;
		int zrSize = 20;
		ArrayList<Proces> procesy = new ArrayList<>();
		ArrayList<Ramka> ramki = new ArrayList<>();
		ArrayList<Integer> przydzial = new ArrayList<>();
		
		// Inicjalizacja
		for (int i = 0; i < iloscProcesow; i++)
		{
			int strony = gen.nextInt(12) + 6;
			ArrayList<Integer> odwolania = new ArrayList<>();
			for (int j = 0; j < iloscOdwolan; j++)
			{
				odwolania.add(gen.nextInt(strony) + 1);
			}
			procesy.add(new Proces(strony, odwolania, 0, 0));
		}
		for (int i = 0; i < iloscRamek; i++)
		{
			ramki.add(new Ramka(0 ,0));
		}
		System.out.println("Przydzial rowny:");
		przydzialRowny(przydzial, iloscRamek, iloscProcesow);
		RownyIProporcjonalny ripr = new RownyIProporcjonalny(procesy, ramki, przydzial);
		ripr.wykonaj();
		przydzial.clear();
		clear(ramki);
		System.out.println();
		System.out.println("Przydzial proporcjonalny:");
		przydzialProporcjonalny(procesy, przydzial, iloscRamek, iloscProcesow);
		ripr.wykonaj();
		przydzial.clear();
		clear(ramki);
		System.out.println();
		System.out.println("Czêstoœæ b³êdów strony:");
		CzestoscBledowStron cbl = new CzestoscBledowStron(procesy, ramki, przydzial, maxProg, minProg);
		cbl.wykonaj();
		przydzial.clear();
		clear(ramki);
		System.out.println();
		System.out.println("Model strefowy:");
		ModelStrefowy ms = new ModelStrefowy(procesy, ramki, przydzial, oknoZbioruR, zrSize);
		ms.wykonaj();
	}

}
