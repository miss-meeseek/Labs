/**
 * Olga Hutouskaya
 * 11.03.2017
 * version: 1.2
 */
import java.util.*;

public class TrojkatPascala
{
	public static void main(String[] args) 
	{	
		int k = 0;
		
		if(args.length == 0)
		{
			return;
		}
		
		try{
			k = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException ex){
			System.out.println(args[0] + " nie jest liczba calkowita");
			return;
		}
		
		if(k > 33 || k < 0)
		{
			System.out.println("wyjdzie poza zakres inta");
			return;
		}
		
		int dlugosc = k + 1;
		Wiersze wiersz = new Wiersze(dlugosc);
		
        for(int i = 1; i < args.length; i++)
		{
			try 
			{ 
				k = Integer.parseInt(args[i]);
				if(k >= dlugosc || k < 0)				//jesli numer podanego wsp jest wiekszy od dlugosci wiersza
					System.out.println("nie ma takiego wspolczynnika");
				else 
					System.out.println("wspolczynnik=" + args[i] + " ---> " + wiersz.getWspolczynnik(k));
			}
			catch (NumberFormatException ex) 
			{
				System.out.println(args[i] + " nie jest liczba calkowita");
				continue;
			}
        }
	}
}