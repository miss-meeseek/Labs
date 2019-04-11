public abstract class Figury
{
	public abstract double policzobwod();
	public abstract double policzpole();
}

class Prostokat extends Cworokat
{
	public Prostokat(int n, int m, int k)
	{
		super(n,m,k);
	}
	
	public double policzobwod()
	{
		return 2*bok1 + 2*bok3;
	}
	public double policzpole()
	{
		return bok1*bok3;
	}
}

class Romb extends Cworokat
{
	public Romb(int n, int m, int k)
	{
		super(n,n,k);
	}
	
	public double policzobwod()
	{
		return 4*bok1;
	}
	public double policzpole()
	{
		kat = Math.toRadians(kat);
		double sin = Math.sin(kat);
        return bok1 * bok1 * sin;
	}
}

class Kwadrat extends Cworokat
{
	public Kwadrat(int n, int m, int k)
	{
		super(n,n,k);
	}
	
	public double policzobwod()
	{
		return 4*bok1;
	}
	public double policzpole()
	{
		return bok1*bok1;
	}
}

class Okrag extends Figury
{
	private double pi = 3.14;
	private int promien;
	public Okrag(int n)
	{
		promien = n;
	}
	
	public double policzobwod()
	{
		return 2*pi*promien;
	}
	public double policzpole()
	{
		return pi*promien*promien;
	}
}

class Pieciokat extends Figury
{
	protected int bok1;		//pirwszy bok
	public Pieciokat(int n)
	{
		bok1 = n;
	}
	
	public double policzobwod()
	{
		return 5*bok1;
	}
	public double policzpole()
	{
		return (bok1*bok1*(Math.sqrt(25+ 10*(Math.sqrt(5)))))/4;
	}
}

class Szesciokat extends Figury
{
	protected int bok1;		//pirwszy bok
	public Szesciokat(int n)
	{
		bok1 = n;
	}
	
	public double policzobwod()
	{
		return 6*bok1;
	}
	public double policzpole()
	{
		return (bok1*bok1*3*Math.sqrt(3))/2;
	}
}
		