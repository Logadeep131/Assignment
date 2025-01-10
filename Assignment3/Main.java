package emp.assigment;

import java.util.*;

abstract class Employee
{
	 private int empid;
	 private String name;
	 private int age;
	 private float salary;
	 private String designation;
	 
	Employee(int empid,float salary,String designation)
	{
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter the age :");
	        Integer a=scan.nextInt();
					   
	        System.out.println("Enter the name :");
	        String str=scan.next();

		this.empid=empid;
		this.name=str;
		this.age=a;
		this.salary=salary;
		this.designation=designation;	
	}

	public int getage() {return this.age;}
	public String getname() {return this.name;}
	public float getsalary() {return this.salary;} 
	public String getdest() {return this.designation;}
	public int getid() {return this.empid;}

	public void setsalary(float val) {this.salary=val;} 

	final public void display()
	{
	  System.out.println("Employee name : "+this.name+" Employee age :"+this.age+" Employee salary : "+this.salary);
	}
	
	abstract public void raisesalary();
		
}

final class Clerk extends Employee
{
	Clerk(int id)
	{
		super(id,20000,"clerk");
	}
        public void raisesalary()
	{
		float newval=this.getsalary();
		setsalary(newval+2000);
	}
	
}

final class Programmer extends Employee
{
	Programmer(int id)
	{
		super(id,30000,"programmer");
	}
	 public void raisesalary()
	{
		float newval=this.getsalary();
		setsalary(newval+5000);
	}
	
}
final class Manager extends Employee
{
	Manager(int id)
	{
		super(id,100000,"manager");
	}
	 public void raisesalary()
	{
		float newval=this.getsalary();
		setsalary(newval+10000);
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
			System.out.println("4.delete");
			System.out.println("5.exit");
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

				Boolean f=true;
				Integer b=0;
					
				while(f){ 
					    System.out.println("Enter the id :");
				  	    b=scan.nextInt();
						
						int dum=0;

						for(int i=0;i<cnt;i++)
				              {
					        if(emp[i].getid()!=b)  dum++;
				              }

					if(dum!=cnt)  System.out.println("id already exist");
					else f=false;
				        }
					
					if(cnt<10){
					   switch(val){
						
						case 1:{
							
							emp[cnt]= new Clerk(b); 
							cnt++;
							break;
							}
						case 2:{
							emp[cnt]= new Programmer(b); 
							cnt++;
							break;
							}
						case 3:{
							emp[cnt]= new Manager(b);
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
					
					emp[i].display();
				     }
					break;
				}
			case 3:{
				
			
				for(int i=0;i<cnt;i++)
				{
					emp[i].raisesalary();
				}	

				break;
				}
			case 5:{
					
				System.out.println("Enter the total number of the employee : "+cnt);
				flag=false;
				break;
	
				}
			case 4:{
					
					   System.out.println("Enter the id to delete:");
				  	   Integer delid=scan.nextInt();

					   System.out.println("Are you sure to delete enter yes or no");
				  	   String delstr=scan.next();
						
					switch(delstr){
						
						case "yes":{
									for(int i=0;i<cnt;i++)
				                                   {
					                                if(emp[i].getid()==delid) emp[i]=null;
				                                   }
							cnt--;
				
							break;
							}
						case "no":{
							  break;
							  }
						default: break;
						
						}
						
			
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