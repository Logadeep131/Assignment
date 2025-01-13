package emp.assigment;

import java.util.*;

abstract class Employee
{
	 private int empid;
	 private String name;
	 private int age;
	 private float salary;
	 private String designation;
	 static int uniqueID[]= new int[100];
	 static int employeeCount=0;
	 
	Employee(float salary,String designation)
	{
		 Scanner scan= new Scanner(System.in);
		 Boolean flagForId=true;
		 Integer validID=0;
		 
		 while(flagForId)
		 { 
			Boolean flagForIdCheck=true;

			while(flagForIdCheck){

					try{
					System.out.println("Enter the id :");
					validID=scan.nextInt();
					if(validID<0) throw new InputMismatchException();
					flagForIdCheck=false;
					}
					catch(InputMismatchException e)
					{
						System.out.println("enter the correct number for the id :");
						scan.nextLine();
						flagForIdCheck=true;
					}

			}
						
			int dum=0;

			for(int i=0;i<uniqueID.length;i++)
				{
				 if(uniqueID[i]!=validID)  dum++;
				}

				if(dum!=uniqueID.length)  System.out.println("id already exist");
				else flagForId=false;
				
		}
		uniqueID[employeeCount]=validID;
		employeeCount++;


		 Integer ageVariable=21;
		 boolean flag=true;

		 while (flag){
			
		  try{
		   
		    System.out.println("Enter the age :");
	         ageVariable=scan.nextInt();
			 if(ageVariable<0) throw new InputMismatchException();
			 if(ageVariable>60 || ageVariable<21) throw new AgeInvalidException();
			 flag=false;
		     }
		    catch(InputMismatchException e)
		    {
			System.out.println("Enter number only ");
			scan.nextLine();
			flag=true;
		    }
			catch(AgeInvalidException e)
		    {
			System.out.println("Enter number between 21-60 ");
			scan.nextLine();
			flag=true;
		    }
		
		 }
		 
	    System.out.println("Enter the name :");
	    String str=scan.next();

		this.empid=validID;
		this.name=str;
		this.age=ageVariable;
		this.salary=salary;
		this.designation=designation;	
	}

	public int getage() {return this.age;}
	public String getname() {return this.name;}
	public float getsalary() {return this.salary;} 
	public String getdest() {return this.designation;}
	public int getid() {return this.empid;}

	public void setsalary(float val) {this.salary=val;} 

	public static boolean removeEmployee(Employee[] empobj)
	{
		Scanner scan = new Scanner(System.in);
		Integer deleteid=0;
		boolean flagForIdDelete=true;
		while(flagForIdDelete){

					try{
					System.out.println("Enter the id to delete:");
					deleteid=scan.nextInt();
					if(deleteid<0) throw new InputMismatchException();
					flagForIdDelete=false;
					}
					catch(InputMismatchException e)
					{
						System.out.println("enter the correct number for the id :");
						scan.nextLine();
						flagForIdDelete=true;
					}
		}

		for(int i=0;i<empobj.length;i++)
			{
				if(empobj[i]!=null && empobj[i].getid()==deleteid) 
				{ 
					empobj[i].display();
					System.out.println("Are you sure to delete enter yes or no");
					String delstr=scan.next();
					if(delstr.equalsIgnoreCase("yes"))
					{
						
						empobj[i]=null;  
						return true;
				     }
					 else 
					 {System.out.println(" ");
					
					 }
			}
		}
		return false;

		
	}

	final public void display() {
    System.out.println("------Employee Details-----");
    System.out.println("Name    :"+ this.name);
    System.out.println("Age     :"+ this.age);
    System.out.println("Salary  :"+ this.salary);
}

	
	abstract public void raisesalary();
		
}

final class Clerk extends Employee
{
	Clerk()
	{
		super(20000,"clerk");
	}
        public void raisesalary()
	{
		float newval=this.getsalary();
		setsalary(newval+2000);
	}
	
}

final class Programmer extends Employee
{
	Programmer()
	{
		super(30000,"programmer");
	}
	 public void raisesalary()
	{
		float newval=this.getsalary();
		setsalary(newval+5000);
	}
	
}
final class Manager extends Employee
{
	Manager()
	{
		super(100000,"manager");
	}
	 public void raisesalary()
	{
		float newval=this.getsalary();
		setsalary(newval+10000);
	}
	
}
class AgeInvalidException extends Exception{
	public AgeInvalidException(){
		super();
	}
	public AgeInvalidException(String msg)
	{
		super(msg);
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
			System.out.println("------------");
			


			Integer num=0; //var for choice
			boolean flagChoice1=true;
		          while (flagChoice1){
		           try{
						System.out.println("Enter the choice:");
						num=scan.nextInt();
						if(num<0 ) throw new InputMismatchException();
						flagChoice1=false;
		              }
		            catch(InputMismatchException e)
		             {
						System.out.println("Enter number between 1 to 5 ");
						scan.nextLine();
			            flagChoice1=true;
		             }	
		        }

			switch(num){
			case 1:{
				 System.out.println("------------");
				 System.out.println("1.clerk");
				 System.out.println("2.Programmer");
				 System.out.println("3.Manager");
				 System.out.println("4.Exit");
				 System.out.println("------------");
				
				  Integer val=0;
				  boolean flagChoice2=true;
		          while (flagChoice2){
			
		           try{		
							System.out.println("Enter the choice:");
							val=scan.nextInt();
							if(val<0) throw new InputMismatchException();
							flagChoice2=false;
		              }
		            catch(InputMismatchException e)
		             {
							System.out.println("Enter number between 1 to 4");
							scan.nextLine();
							flagChoice2=true;
		             }	
		        }
	
					if(cnt<10){
					   switch(val){
						
						case 1:{
							
							emp[cnt]= new Clerk(); 
							cnt++;
							break;
							}
						case 2:{
							emp[cnt]= new Programmer(); 
							cnt++;
							break;
							}
						case 3:{
							emp[cnt]= new Manager();
							cnt++;
							break; 
							}
						case 4: break;
						default:{
							System.out.println("Enter the choice from 1 to 4");
							break;
				                }
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
			case 4:{
						boolean employeeEReduce=Employee.removeEmployee(emp);
						if(employeeEReduce) cnt--;
						break;
				   }
			case 5:{
						System.out.println("Enter the total number of the employee : "+cnt);
						flag=false;
						break;
					}
			default:{
						System.out.println("Enter the choice from 1 to 5");
						break;
					}
			}	
		}
	}
}