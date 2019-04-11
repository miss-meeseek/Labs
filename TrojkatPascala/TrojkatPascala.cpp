/**
* Olga Hutouskaya
* 11.03.2017
* version: 1.11
*/

#include <iostream>
#include <sstream>
using namespace std;

class Wiersz
{
private:
	int *ptablica;
public:
	Wiersz(int n)
	{
		ptablica = new int[n];
		//wyzerowac tablice
		ptablica[0] = 1;
		for (int i = 1; i < n; i++)
		{
			ptablica[i] = 0;
		}
		//wypelnic tablice wsp trojkata Pascala
		while (ptablica[n - 1] != 1)
		{
			for (int i = n - 1; i > 0; i--)
			{
				if (ptablica[i] != 0)
				{
					ptablica[i] = ptablica[i] + ptablica[i - 1];
				}
				else
				{
					if (ptablica[i - 1] == 1)
					{
						ptablica[i] = 1;
					}
				}
			}
		}
	}

	int wspolczynnik(int argument)
	{
		return ptablica[argument];
	}

	~Wiersz() { delete[] ptablica; }
};


int main(int argc, char* argv[])
{
	//if (argc > 1)
	//{
		int dlugosc = 0;
		try
		{
			std::stringstream ss;
			ss << argv[1];
			ss >> dlugosc;
			if (dlugosc > 33 || dlugosc < 0)
				return 0;
		}
		catch (...)
		{
			cout << argv[1] << " bledne dane" << endl;
		}

		if (dlugosc != 0)
		{
			Wiersz tablica(dlugosc + 1);
			int argument = 0;
			for (int i = 2; i < argc; i++)
			{
				try
				{
					stringstream ss;
					ss << argv[i];
					ss >> argument;
					if (argument <= dlugosc && argument >= 0)
						cout << tablica.wspolczynnik(argument) << endl;
					else
						cout << argv[i] << " poza zakresem wiersza" << endl;
				}
				catch (...)
				{
					cout << argv[i] << " bledne dane" << endl;
				}
			}
		}
	//}
	//else
		//cout << "Has no arguments" << endl;
	//system("pause");
	return 0;
}
