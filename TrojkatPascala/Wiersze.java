/**
 * Olga Hutouskaya
 * 11.03.2017
 * version: 1.2
 */
public class Wiersze 
{ 
	private int [] wiersz;
	
	//konstruktor
	public Wiersze(int n)
	{
		this.wiersz = new int [n];
		wiersz[0] = 1;
		
		while(wiersz[n - 1] != 1)
		{
			for(int i = n - 1; i > 0; i--)
			{
				if(wiersz[i]!= 0)
				{
					wiersz[i] = wiersz[i] + wiersz[i - 1];
					
				}
				else // ( == 0)
				{
					if(wiersz[i - 1]== 1)
					{
						wiersz[i]= 1;
					}
				}
			}
		}
	}
	
	public int getWspolczynnik(int m)
	{
		return wiersz[m];
	}
}
