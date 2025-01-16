package emp.assignment;

import java.util.*;
import java.util.regex.*;

abstract class Employee {
    private int empid;
    private String name;
    private int age;
    private float salary;
    private String designation;
    static int uniqueID[] = new int[100];
    static int employeeCount = 0;

    Employee(float salary, String designation) {
        Scanner scan = new Scanner(System.in);
        Boolean flagForId = true;
        Integer validID = Id.checkId();

        while (flagForId) {
            int dum = 0;
            for (int i = 0; i < uniqueID.length; i++) {
                if (uniqueID[i] != validID) dum++;
            }

            if (dum != uniqueID.length) System.out.println("ID already exists");
            else flagForId = false;
        }
        uniqueID[employeeCount] = validID;
        employeeCount++;

        PatternMatching obj = new PatternMatching();
        String str = obj.NameCheck();
        int ageVariable = Age.readAge();

        this.empid = validID;
        this.name = str;
        this.age = ageVariable;
        this.salary = salary;
        this.designation = designation;
    }

    public int getage() { return this.age; }

    public String getname() { return this.name; }

    public float getsalary() { return this.salary; }

    public String getdest() { return this.designation; }

    public int getid() { return this.empid; }

    public void setsalary(float val) { this.salary = val; }

    public static boolean removeEmployee(Employee[] empobj) {
        Scanner scan = new Scanner(System.in);
        Integer deleteid = Id.checkId();
        for (int i = 0; i < empobj.length; i++) {
            if (empobj[i] != null && empobj[i].getid() == deleteid) {
                empobj[i].display();
                System.out.println("Are you sure to delete? Enter yes or no");
                String delstr = scan.next();
                if (delstr.equalsIgnoreCase("yes")) {
                    empobj[i] = null;
                    return true;
                } else {
                    System.out.println(" ");
                }
            }
        }
        return false;
    }

    final public void display() {
        System.out.println("------Employee Details-----");
        System.out.println("Name    :" + this.name);
        System.out.println("Age     :" + this.age);
        System.out.println("Salary  :" + this.salary);
    }

    abstract public void raisesalary();
}

class Clerk extends Employee {
    Clerk() {
        super(20000, "clerk");
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 2000);
    }
}

class Programmer extends Employee {
    Programmer() {
        super(30000, "programmer");
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 5000);
    }
}

class Manager extends Employee {
    Manager() {
        super(100000, "manager");
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 10000);
    }
}


class PatternMatching {
    public static String NameCheck() {
        String s1 = null;
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the name : ");
            s1 = scan.nextLine();
            String s2 = "^[A-Z][a-z]+ [A-Z][a-z]*$";
            Pattern p1 = Pattern.compile(s2);
            Matcher m1 = p1.matcher(s1);

            if (m1.find()) break;
            else System.out.println("Enter the name in the format of first and last word and each word first letter is capital");
        }
        return s1;
    }
}

class Id {
    public static int checkId() {
        while (true) {
            try {
                System.out.println("Enter ID for the Employee :");
                int employeeID = new Scanner(System.in).nextInt();

                if (employeeID < 0) throw new InputMismatchException();

                return employeeID;
            } catch (InputMismatchException e) {
                System.out.println("Enter the correct number ");
            }
        }
    }
}

class Menu {
    private static int maxChoice;

    public static int readChoice(int max) {
        maxChoice = max;
        while (true) {
            try {
                System.out.println("Enter the choice : ");
                int choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > maxChoice) {
                    throw new InputInvalidException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Enter the correct number");
            } catch (InputInvalidException e) {
                e.displayChoice(maxChoice);
            }
        }
    }
}

class Age {
    private static int maxAge;

    public static int readAge() {
        maxAge = 60;
        while (true) {
            try {
                System.out.println("Enter the Age:");
                int choice = new Scanner(System.in).nextInt();
                if (choice < 21 || choice > maxAge) {
                    throw new InputInvalidException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Enter the age in number");
            } catch (InputInvalidException e) {
                e.DisplayAge(maxAge);
            }
        }
    }
}

class InputInvalidException extends Exception {
    InputInvalidException() {
        super();
    }

    InputInvalidException(String msg) {
        super(msg);
    }

    public void displayChoice(int val) {
        System.out.println("Enter the number between the range from 1 to " + val);
    }

    public void DisplayAge(int val) {
        System.out.println("Enter the number between the range from 21 to " + val);
    }
}

final class CEO extends Employee {
    private static CEO obj = null;
    
    private static boolean flagemployee=false;

    private CEO() 
	{
        
		super(500000,"CEO");
        flagemployee=true;
    
	}
    public static boolean getflagCEO() {return flagemployee;}

    public static CEO getobj() {
        if (obj == null) {
            obj = new CEO();
        }
        return obj;
    }

	  public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 50000);
    }
}


class EmployeeFactory {
    public static Employee createEmployee(int choice) {
        if (choice != 1 && !CEO.getflagCEO()) {
            System.out.println("CEO must be created first!");
            return null; 
        }

        switch (choice) {
            case 1: return CEO.getobj();
                
            case 2: return new Clerk();
                
            case 3: return new Programmer();
                 
            case 4: return new Manager();
                 
            default:
                throw new RuntimeException("Invalid choice");
        }
    }
}


class NonExistException extends Exception
{
    public NonExistException()
    {
        super();
    }
    public NonExistException(String msg)
    {
        super(msg);
    }
}

public class Main {
    public static void main(String args[]) {
        int cnt = 0;
        Employee emp[] = new Employee[10];

        boolean flag = true;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            System.out.println("------------");
            System.out.println("1.create");
            System.out.println("2.Display");
            System.out.println("3.Raise salary");
            System.out.println("4.delete");
            System.out.println("5.exit");
            System.out.println("------------");

            int num = Menu.readChoice(5);
            switch (num) {
                case 1: {
                    System.out.println("------------");
					System.out.println("1.CEO");
                    System.out.println("2.clerk");
                    System.out.println("3.Programmer");
                    System.out.println("4.Manager");
                    System.out.println("5.Exit");
                    System.out.println("------------");

                    Integer val = Menu.readChoice(5);
					
                    if (cnt < 10) {
						try{
                            if(EmployeeFactory.createEmployee(val) != null){
                            emp[cnt] = EmployeeFactory.createEmployee(val);
                            cnt++;
                            }
						}
						catch(RuntimeException e)
						{
							System.out.println("Error occur in creating the employee ");
						}
                    }
                    break;
                }
                case 2: {
                    for (int i = 0; i < cnt; i++) {
                        emp[i].display();
                    }
                    break;
                }
                case 3: {
                    for (int i = 0; i < cnt; i++) {
                        emp[i].raisesalary();
                    }
                    break;
                }
                case 4: {
                    boolean employeeEReduce = Employee.removeEmployee(emp);
                    if (employeeEReduce) cnt--;
                    break;
                }
                case 5: {
                    System.out.println("Enter the total number of the employee: " + cnt);
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Enter the choice from 1 to 5");
                    break;
                }
            }
        }
    }
}
