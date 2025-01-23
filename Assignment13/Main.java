package emp.assignment;

import java.util.*;
import java.util.regex.*;
import java.io.*;

abstract class Employee {
    private int empid;
    private String name;
    private int age;
    private float salary;
    private String designation;
    public static Set<Integer> uniqueId= new HashSet<>();

    public int getage() {
        return this.age;
    }

    public String getname() {
        return this.name;
    }

    public float getsalary() {
        return this.salary;
    }

    public String getdest() {
        return this.designation;
    }

    public int getid() {
        return this.empid;
    }

    public void setsalary(float val) {
        this.salary = val;
    }

    public void setid(int empid) {
        this.empid = empid;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setdest(String designation) {
        this.designation = designation;
    }

    public void setage(int age) {
        this.age = age;
    }

  public static boolean removeEmployee(Map<Integer, Employee> empobj) {
    Scanner scan = new Scanner(System.in);
    Integer deleteid = Id.checkId();  
    if (empobj.containsKey(deleteid)) {
        empobj.get(deleteid).display();  
        System.out.println("Are you sure you want to delete");
        String delstr = scan.next();
        if (delstr.equalsIgnoreCase("yes")) {
            empobj.remove(deleteid); 
            System.out.println("Employee has been deleted.");
            return true; 
        } else {
            System.out.println("Employee not deleted.");
        }
    } else {
        System.out.println("Employee not found" + deleteid);
    }
    return false;
}


    final public void display() {
        System.out.println("------Employee Details-----");
        System.out.println("Id      :" + this.empid);
        System.out.println("Name    :" + this.name);
        System.out.println("Age     :" + this.age);
        System.out.println("Salary  :" + this.salary);
        System.out.println("Designation  :" + this.designation);
    }

    public static String toString(Employee obj) {
        return obj.getid() + " , " + obj.getage() + " , " + obj.getname() + " , " + obj.getdest() + " , " + obj.getsalary() + "\r\n";
    }

    abstract public void raisesalary();
}

class Input
{
      
        public static String getinputname()
        {
            PatternMatching obj = new PatternMatching();
            String str = obj.NameCheck();
            return str;
        }

        public static int getinputage()
        {
            int ageVariable = Age.readAge();
            return ageVariable;
        }

        public static int getinputid()
        {
        Boolean flagForId = true;
        Integer validID = 0;

        while (flagForId) {
            validID = Id.checkId();
            if (Employee.uniqueId.contains(validID)) {
                System.out.println("ID already exists");
            } else {
                flagForId = false;
                Employee.uniqueId.add(validID);
            }
        }
    
            return validID;
        }     
}

final class Clerk extends Employee {
    private Clerk(float salary,int age,String name,int id) {
         setsalary(salary);
         setdest("clerk");
         setage(age);
         setid(id);
         setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 2000);
    }

    public static Clerk createobj()
    {
        return new Clerk(20000f,Input.getinputage(),Input.getinputname(),Input.getinputid());
    }

     public static Clerk createobj(float salary,int age,String name,int id)
    {
        return new Clerk(salary,age,name,id);
    }

}

final class Programmer extends Employee {
    private Programmer(float salary,int age,String name,int id) {
         setsalary(salary);
         setdest("programmer");
         setage(age);
         setid(id);
         setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 5000);
    }

    public static Programmer createobj()
    {
        return new Programmer(40000f,Input.getinputage(),Input.getinputname(),Input.getinputid());
    }

    public static Programmer createobj(float salary,int age,String name,int id)
    {
        return new Programmer(salary,age,name,id);
    }


}

final class Manager extends Employee {
    private Manager(float salary,int age,String name,int id) {
         setsalary(salary);
         setdest("manager");
         setage(age);
         setid(id);
         setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 10000);
    }

    public static Manager createobj()
    {
        return new Manager(80000f,Input.getinputage(),Input.getinputname(),Input.getinputid());
    }

    public static Manager createobj(float salary,int age,String name,int id)
    {
        return new Manager(salary,age,name,id);
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
    private static boolean flagemployee = false;

    private CEO(float salary,int age,String name,int id) {
         setsalary(salary);
         setdest("CEO");
         setage(age);
         setid(id);
         setname(name);
        flagemployee = true;
    }

    public static boolean getflagCEO() {
        return flagemployee;
    }

    public static CEO getobj() {
        if (obj == null) {
            obj = new CEO(100000f,Input.getinputage(),Input.getinputname(),Input.getinputid());
        }
        return obj;
    }

    public static CEO getobj(float salary,int age,String name,int id) {
        if (obj == null) {
            obj = new CEO(salary,age,name,id);
        }
        return obj;
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 50000);
    }
}

class NonExistException extends Exception {
    public NonExistException() {
        super();
    }

    public NonExistException(String msg) {
        super(msg);
    }
}

interface EmployeeFactory {
    Employee createEmployee(int choice) throws NonExistException;
}

class CreateEmployee implements EmployeeFactory {
    public Employee createEmployee(int choice) throws NonExistException {
        if (choice != 1 && !CEO.getflagCEO()) {
            throw new NonExistException("CEO must be created first!");
        }

        switch (choice) {
            case 1: 
                return CEO.getobj();
            case 2: 
                return Clerk.createobj();
            case 3: 
                return Programmer.createobj();
            case 4: 
                return Manager.createobj();
            default: 
                throw new NonExistException("Invalid choice!"); 
        }
    }
}


class sortid implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return Integer.compare(e1.getid(), e2.getid());
    }
}

class sortname implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.getname().compareTo(e2.getname());
    }
}

class sortsalary implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return Float.compare(e1.getsalary(), e2.getsalary());
    }
}

class sortage implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return Integer.compare(e1.getage(), e2.getage());
    }
}

class sortdesignation implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.getdest().compareTo(e2.getdest());
    }
}



public class Main {
    public static void main(String args[]) {
        Map<Integer, Employee> emp = new HashMap<>();
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile("employees.csv", "rw");
            loadDataFromFile(emp);
            EmployeeFactory factory = new CreateEmployee();

            boolean flag = true;
            while (flag) {
                Scanner scan = new Scanner(System.in);
                System.out.println("------------");
                System.out.println("1.create");
                System.out.println("2.Display");
                System.out.println("3.Raise salary");
                System.out.println("4.delete");
                System.out.println("5.Search");
                System.out.println("6.exit");
                System.out.println("------------");

                int num = Menu.readChoice(6);
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

                        try {
                            Employee newEmp = factory.createEmployee(val);
                            emp.put(newEmp.getid(), newEmp);
                        } catch (NonExistException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 2: {
                        List<Employee> employeeList = new ArrayList<>(emp.values());

                        System.out.println("------------");
                        System.out.println("1. Sort by ID");
                        System.out.println("2. Sort by Name");
                        System.out.println("3. Sort by Age");
                        System.out.println("4. Sort by Designation");
                        System.out.println("5. Sort by Salary");
                        System.out.println("------------");

                        int numDisplay = Menu.readChoice(5);
                        switch (numDisplay) {
                            case 1:
                                Collections.sort(employeeList, new sortid());
                                break;
                            case 2:
                                Collections.sort(employeeList, new sortname());
                                break;
                            case 3:
                                Collections.sort(employeeList, new sortage());
                                break;
                            case 4:
                                Collections.sort(employeeList, new sortdesignation());
                                break;
                            case 5:
                                Collections.sort(employeeList, new sortsalary());
                                break;
                        }

                        for (Employee e : employeeList) {
                            e.display();
                        }

                        break;
                    }
                    case 3: {
                        for (Employee e : emp.values()) {
                            e.raisesalary();
                        }
                        System.out.println("Salaries updated");
                        break;
                    }
                    case 4: {
                        boolean employeeEReduce = Employee.removeEmployee(emp);
                        break;
                    }
                    case 5: {
                        System.out.println("------------");
                        System.out.println("1. Search by ID");
                        System.out.println("2. Search by Designation");
                        System.out.println("3. Search by Name");
                        System.out.println("------------");

                        int numforsearch = Menu.readChoice(3);

                        boolean found = false;

                        switch (numforsearch) {
                            case 1: {
                                System.out.println("Enter the employee id: ");
                                int sid = new Scanner(System.in).nextInt();
                                Employee obj = emp.get(sid);
                                if (obj != null) {
                                    obj.display();
                                } else {
                                    System.out.println("Employee not found.");
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("Enter the designation to search: ");
                                String designationToSearch = new Scanner(System.in).nextLine();

                                for (Employee e : emp.values()) {
                                    if (e.getdest().equalsIgnoreCase(designationToSearch)) {
                                        e.display();
                                        found = true;
                                    }
                                }

                                if (!found) {
                                    System.out.println("No employees found with the designation ");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("Enter the name to search: ");
                                String nameToSearch = new Scanner(System.in).nextLine();

                                for (Employee e : emp.values()) {
                                    if (e.getname().equals(nameToSearch)) {
                                        e.display();
                                        found = true;
                                    }
                                }

                                if (!found) {
                                    System.out.println("No employees found");
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 6: {
                        saveDataToFile(emp);
                        System.out.println("Data saved successfully!");
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Enter the choice from 1 to 6");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void loadDataFromFile(Map<Integer, Employee> emp) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("employees.csv")));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" , ");
            int id = Integer.parseInt(parts[0].trim());
            int age = Integer.parseInt(parts[1].trim());
            String name = parts[2].trim();
         
            String designation = parts[3].trim().toLowerCase(); 

            float salary = Float.parseFloat(parts[4].trim());

        
              switch(designation)
             {
               case "ceo" -> emp.put(id, CEO.getobj(salary, age, name, id));
                case "clerk" -> emp.put(id, Clerk.createobj(salary, age, name, id));
                case "programmer" -> emp.put(id, Programmer.createobj(salary, age, name, id));
                case "manager" -> emp.put(id, Manager.createobj(salary, age, name, id));                
             }

        }
        br.close();
    }

    private static void saveDataToFile(Map<Integer, Employee> emp) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("employees.csv"));
        for (Employee e : emp.values()) {
            String empData = Employee.toString(e);
            bw.write(empData);
        }
        bw.close();
    }
}
