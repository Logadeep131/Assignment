import java.util.*;

class Race extends Thread
{
	private String name;
	private int startTime;
	private int endTime;
    private int totalTime;
	

	Race(String name)
	{
	this.name=name;
	}

	public String getNameRacer() {return this.name;}

	public int getStartTime() {return this.startTime;}

	public int getEndTime() {return this.endTime;}

    public int getTotalTime() {return this.totalTime;}
	

	public void run()
	{
		this.startTime = (int) System.currentTimeMillis();

		
		for(int i=1;i<=20;i++)
		{
			try
			{
			     int sleepTime=(int)(Math.random()*1000);
                
		         Thread.sleep(sleepTime);
			     if(i==20)
				 {
                    this.endTime = (int)System.currentTimeMillis();
					this.totalTime = this.endTime - this.startTime;
				 }
			}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	public static void WinnerList(Race[] Racer)
	{
		for(int i=0;i<Racer.length-1;i++)
		{
			for(int j=i+1;j<Racer.length;j++)
			{
			int val1=Racer[i].getTotalTime();
			int val2=Racer[j].getTotalTime();
			if(val1>val2)
			   {
				Race obj=Racer[i];
				Racer[i]=Racer[j];
				Racer[j]=obj;
			   }
			}
		}
	}
	public static void display(Race[] Racer)
	{
		for(int i=0;i<Racer.length;i++)
	       {	
		System.out.print("Rank :"+(i+1)+ " Name : "+Racer[i].getNameRacer());

		System.out.println(" Time taken : "+Racer[i].getTotalTime());
	       }
	}

}

class BikeRacing
{
	public static void main(String args[])
	{

	Race Racer[]= new Race[10];
	
	for(int i=0;i<10;i++)
	{
		Racer[i]=new Race("Racer "+(i+1));
	}

	for(int i=10;i>=0;i--)
	{
		try{
		System.out.println(i);
		Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
	}
	System.out.println("Go");
	
	for(int i=0;i<Racer.length;i++)
	{ Racer[i].start();

	}
    for(int i=0;i<Racer.length;i++)
	{
        try{
         Racer[i].join();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

	}
	
	Race.WinnerList(Racer);
	Race.display(Racer);
	
	}

}
