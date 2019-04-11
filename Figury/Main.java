/**
 * Volha Hutouskaya
 * 18.03.2017
 * version: 1.2
 */
import java.util.*;

public class Main
{
	public static void main(String[] args) 
	{	
		if(args.length == 0)
			return;
		
		int k = 0;
		int j = 1;// po argumentach
		int i = 0;//po literkach
		
		String string = args[0];
		int length0 = string.length();
		char[] literki = string.toCharArray();
		
		//CZY WYSTARCZY ARGUMENTOW
		int licze = 0; 
		for (int l = 0; l < length0; l++)
		{
			switch (literki[l])
			{
				case 'c':
					licze += 5;
					break;
				case 'o':
					licze++;
					break;
				case 'p':
					licze++;
					break;
				case 's':
					licze++;
					break;
			}
		}
		if (licze > args.length - 1)
		{
			System.out.println("Mniej niz trzeba argumentow c");
			return;
		}
		
		//FIGURKI
		Figury f[] = new Figury[length0];
		while(i < length0 && j < args.length)
		{
			try{ 
				k = Integer.parseInt(args[j]);
				if(k < 0)		
				{
					System.out.println("ujemny argument");
					return;
				}
			}
			catch (NumberFormatException ex){
				System.out.println(args[j] + " nie jest liczba calkowita");
				return;
			}
			
			switch(literki[i])
			{
				case 'c': 		
						int[] tab = new int[5];	//czworokatu odpowiada 5 liczb
						for(int t = 0; t < 5; t++)	//wczytujemy je do tablicy
						{					
							try{ 
								k = Integer.parseInt(args[j]);
								if(k < 0)		
								{
									System.out.println("Ujemne dane");
									return;
								}
								else
								{
									tab[t] = k;
									j++; 
								}
							}
							catch (NumberFormatException ex){
								System.out.println(args[j] + " nie jest liczba calkowita");
								return;
							}
						}
						
						if(tab[0]==tab[1]&& tab[1]==tab[2]&&tab[2]==tab[3])
						{
							if(tab[4]==90)
							{
								f[i] = new Kwadrat(tab[0], tab[0], tab[4]);
								i++;
							}
							else 
							{
								if(tab[4] > 0 && tab[4] < 180)
								{
									f[i] = new Romb(tab[0], tab[0], tab[4]);
									i++;
								}
								else
								{
									System.out.println(tab[4] + " Bledne dane");
								}
							}
						}
						else
						{
							if(tab[0] == tab[1] && tab[2] == tab[3] && tab[4] == 90)
							{
								f[i] = new Prostokat(tab[0], tab[2], tab[4]);
								i++;
							}
							else 
							{
								System.out.println(tab[0] + " " + tab[1] + " " + tab[2] + " " + tab[3] + " Bledne dane");
							}
						}
						break;
				case 'o':  
						f[i] = new Okrag(k);
						j++; i++;
						break;
				case 'p':  
						f[i] = new Pieciokat(k);
						j++; i++;
						break;
				case 's': 
						f[i] = new Szesciokat(k);
						j++; i++;
						break;
				default: 
						System.out.println("Bledne dane");
						return;
			}
		}
		
		for(j = 0; j < i; j++)
		{
			System.out.println(f[j].policzobwod());
			System.out.println(f[j].policzpole());
		}
	}
}