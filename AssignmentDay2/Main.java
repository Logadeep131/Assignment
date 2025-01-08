import java.util.*;

class Employee
{
	 private String name;
	 private int age;
	 private float salary;
	 private String destination;

	
	Employee(String name, int age,float salary,String destination)
	{
		this.name=name;
		this.age=age;
		this.salary=salary;
		this.destination=destination;
	}
	public int getage() {return this.age;}
	public String getname() {return this.name;}
	public float getsalary() {return this.salary;}
	public String getdest() {return this.destination;}
	
	public void raisesalary(float amount)
	{
		 this.salary+=amount;
	}	
	
}

class Clerk extends Employee
{
	Clerk(String name, int age)
	{
		super(name,age,20000,"clerk");
	}
}

class Programmer extends Employee
{
	Programmer(String name, int age)
	{
		super(name,age,30000,"programmer");
	}
	
}
class Manager extends Employee
{
	Manager(String name, int age)
	{
		super(name,age,100000,"manager");
	}
	
}
public class Main{
	public static void main(String args[])
	{	
		
		int cnt=0;
		Employee emp[] = new Employee[10];
		boolean flag=true;
		while(flag)
		{
			Scanner scan= new Scanner(System.in);
			System.out.println("------------");
			System.out.println("1.create");
			System.out.println("2.Display");
			System.out.println("3.Raise salary");
			System.out.println("4.Exit");
			System.out.println("Enter the choice :");

			Integer num=scan.nextInt();

			switch(num){
			case 1:{
				 System.out.println("------------");
				 System.out.println("1.clerk");
				 System.out.println("2.Programmer");
				 System.out.println("3.Manager");
				 System.out.println("4.Exit");
				  System.out.println("Enter the choice :");
				  Integer val=scan.nextInt();	

					
					   System.out.println("Enter the age :");
				  	   Integer a=scan.nextInt();
					   
					   System.out.println("Enter the name :");
				  	   String str=scan.next();
					   
				
					
	
					if(cnt<10){
					   switch(val){
						
						case 1:{
							emp[cnt]= new Clerk(str,a); 
							cnt++;
							break;
							}
						case 2:{
							emp[cnt]= new Programmer(str,a); 
							cnt++;
							break;
							}
						case 3:{
							emp[cnt]= new Manager(str,a);
							cnt++;
							break; 
							}
						case 4: break;
						default: break;
						
						}
	
						
					}
                              		break;
				

				}
			case 2:{
					for(int i=0;i<cnt;i++)
				     {
					String var=emp[i].getname();
					System.out.println(var);
					System.out.println("Employee name : "+emp[i].getname()+" Employee age :"+emp[i].getage()+" Employee salary : "+emp[i].getsalary());
				     }
					break;
				}
			case 3:{
				
			
				for(int i=0;i<cnt;i++)
				{
					if(emp[i].getdest()=="clerk") emp[i].raisesalary(2000);
					else if(emp[i].getdest()=="programmer") emp[i].raisesalary(5000);
					else if(emp[i].getdest()=="manager") emp[i].raisesalary(15000);
				}	

				break;
				}
			case 4:{
					
				System.out.println("Enter the total number of the employee : "+cnt);
				flag=false;
				break;
	
				}
			default:{
				System.out.println("Enter the choice from 1 to 4");
				break;
				}
			
			}
	
			
		}


	}
}