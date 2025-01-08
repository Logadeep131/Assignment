import java.util.*;

class employee{
	private String name;
 	private int age;
	private float salary;
	private String destination;

	employee(String name,int age,float salary,String destination)
	{
		this.name=name;
		this.age=age;
		this.salary=salary;
		this.destination=destination;
	}

	public void display()
	{
	System.out.println(" Employee name : "+ name + " Employee age : "+ age + " Employee salary : " +salary+" Employee destination : "+ destination);
	}

	public void raisesalary(float amount)
	{
	        this.salary+=amount;
	}
}

class clerk extends employee{
	
	clerk(String name,int age,float salary)
	{
		super(name,age,salary,"clerk");
	}
}

class programmer extends employee{
	
	programmer(String name,int age,float salary)
	{
		super(name,age,salary,"programmer");
	}
}
class manager extends employee{
	
	manager(String name,int age,float salary)
	{
		super(name,age,salary,"manager");
	}
}

class Main{
	public static void main(String args[])
	{
		employee objclerk = new clerk("Ramesh",25,25000);
		employee objprog = new programmer("kumar",29,65000);
		employee objmanager = new manager("Arun",25,95000);

		System.out.println("Before raising the salary");
		objclerk.display();
		System.out.println();
		objprog.display();
		System.out.println();
		objmanager.display();
		System.out.println();

		System.out.println("enter increase salary for clerk :");
		Scanner scan = new Scanner(System.in);
                Integer value=scan.nextInt();
		
		objclerk.raisesalary(value);

		objprog.raisesalary(10000);
		objmanager.raisesalary(25000);

		System.out.println("After raising the salary ");
		objclerk.display();
		System.out.println();
		objprog.display();
		System.out.println();
		objmanager.display();
		
	}
}
