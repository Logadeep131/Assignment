package emp.assignment;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.sql.*;

abstract class Employee implements Serializable {
    private int empid;
    private String name;
    private int age;
    private float salary;
    private String designation;
    public static Set<Integer> uniqueId = new HashSet<>();

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
            System.out.println("Are you sure you want to delete?");
            String delstr = scan.next();
            if (delstr.equalsIgnoreCase("yes")) {
                empobj.remove(deleteid);
                System.out.println("Employee deleted.");
                return true;
            } else {
                System.out.println("Employee not deleted.");
            }
        } else {
            System.out.println("Employee not found " + deleteid);
        }
        return false;
    }

    final public void display() {
        System.out.println("------Employee Details-----");
        System.out.println("Id  :" + this.empid);
        System.out.println("Name  :" + this.name);
        System.out.println("Age  :" + this.age);
        System.out.println("Salary  :" + this.salary);
        System.out.println("Designation  :" + this.designation);
    }

    public static String toString(Employee obj) {
        return obj.getid() + " , " + obj.getage() + " , " + obj.getname() + " , " + obj.getdest() + " , " + obj.getsalary() + "\r\n";
    }

    abstract public void raisesalary();
}

class Input {

    public static String getinputname() {
        PatternMatching obj = new PatternMatching();
        String str = obj.NameCheck();
        return str;
    }

    public static String getDesignation() {
       
        String str = new Scanner(System.in).next();
        return str;
    }

    public static int getinputage() {
        int ageVariable = Age.readAge();
        return ageVariable;
    }

    public static int getinputid() {
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

final class Clerk extends Employee implements Serializable {
    private Clerk(float salary, int age, String name, int id) {
        setsalary(salary);
        setdest("CLERK");
        setage(age);
        setid(id);
        setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 2000);
    }

    public static Clerk createobj() {
        return new Clerk(20000f, Input.getinputage(), Input.getinputname(), Input.getinputid());
    }

    public static Clerk createobj(float salary, int age, String name, int id) {
        return new Clerk(salary, age, name, id);
    }
}

final class Programmer extends Employee implements Serializable {
    private Programmer(float salary, int age, String name, int id) {
        setsalary(salary);
        setdest("PROGRAMMER");
        setage(age);
        setid(id);
        setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 5000);
    }

    public static Programmer createobj() {
        return new Programmer(40000f, Input.getinputage(), Input.getinputname(), Input.getinputid());
    }

    public static Programmer createobj(float salary, int age, String name, int id) {
        return new Programmer(salary, age, name, id);
    }
}

final class Others extends Employee implements Serializable {
    private Others(float salary, int age, String name, int id) {
        setsalary(salary);
        setdest("OTHERS");
        setage(age);
        setid(id);
        setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 5000);
    }

    public static Others createobj() {
        return new Others(40000f, Input.getinputage(), Input.getinputname(), Input.getinputid());
    }

    public static Others createobj(float salary, int age, String name, int id) {
        return new Others(salary, age, name, id);
    }
}

final class Manager extends Employee implements Serializable {
    private Manager(float salary, int age, String name, int id) {
        setsalary(salary);
        setdest("MANAGER");
        setage(age);
        setid(id);
        setname(name);
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 10000);
    }

    public static Manager createobj() {
        return new Manager(80000f, Input.getinputage(), Input.getinputname(), Input.getinputid());
    }

    public static Manager createobj(float salary, int age, String name, int id) {
        return new Manager(salary, age, name, id);
    }
}

final class CEO extends Employee implements Serializable {
    private static CEO obj = null;
    private static boolean flagemployee = false;

    private CEO(float salary, int age, String name, int id) {
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
            obj = new CEO(100000f, Input.getinputage(), Input.getinputname(), Input.getinputid());
        }
        return obj;
    }

    public static CEO getobj(float salary, int age, String name, int id) {
        if (obj == null) {
            obj = new CEO(salary, age, name, id);
        }
        return obj;
    }

    public void raisesalary() {
        float newval = this.getsalary();
        setsalary(newval + 50000);
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
            case 5:
                return Others.createobj();
            case 6:
                return null;
            default: 
                throw new NonExistException("Invalid choice!"); 
        }
    }
}

public class Main {
    public static void main(String args[]) {
        Map<Integer, Employee> emp = new HashMap<>();

        try {

            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","tiger");

            PreparedStatement pstmt=con.prepareStatement("insert into employee values(?,?,?,?,?)");
        
            Statement stmt=con.createStatement();

            ResultSet rs=null;

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
                      
                        Integer val = 0;

                        while(val!=6){
                   
                        System.out.println("------------");
                        System.out.println("1.CEO");
                        System.out.println("2.clerk");
                        System.out.println("3.Programmer");
                        System.out.println("4.Manager");
                        System.out.println("5.Others");
                        System.out.println("6.Exit");
                        System.out.println("------------");

                        val = Menu.readChoice(6);

                        try {
                            Employee newEmp = factory.createEmployee(val);
                            pstmt.setInt(1,newEmp.getid());
                            pstmt.setString(2,newEmp.getname());
                            pstmt.setInt(3,newEmp.getage());
                            pstmt.setString(4,newEmp.getdest());
                            pstmt.setFloat(5,newEmp.getsalary());

                            pstmt.execute();
                        } catch (NonExistException e) {
                            System.out.println(e.getMessage());
                        }

                        }
                        break;
                    }
                    case 2: {
                       
                       
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
                                rs=stmt.executeQuery("select * from employee order by eid");
                                break;
                            case 2:
                                rs=stmt.executeQuery("select * from employee order by name");
                                break;
                            case 3:
                                 rs=stmt.executeQuery("select * from employee order by age");
                                break;
                            case 4:
                                 rs=stmt.executeQuery("select * from employee order by designation");
                                break;
                            case 5:
                                  rs=stmt.executeQuery("select * from employee order by salary");
                                break;
                        }

                      while(rs.next())
                      {
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                      }

                        break;
                    }
                    case 3: {

                    System.out.println("enter id of the employee for appraisal :");
                    int id=new Scanner(System.in).nextInt();
                    System.out.println("enter the amount for appraisal :");
                    Float amount=new Scanner(System.in).nextFloat();
                    rs=stmt.executeQuery("select * from employee where eid="+id);
                    if(rs.next())
                      {
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                        System.out.println("confirm it finally yes/no : ");
                        String app=new Scanner(System.in).next();
                        if(app.equals("yes"))
                        {
                                CallableStatement cstmt= con.prepareCall("CALL appraisal(?,?)");
                                cstmt.setInt(1,id);
                                cstmt.setFloat(2,amount);
                                cstmt.execute();

                                cstmt.close();          
                        }
                      }

                        break;
                    }
                    case 4: {

                        System.out.println("enter the emloyee id to delete :");
                        int id=new Scanner(System.in).nextInt();

                        rs=stmt.executeQuery("select * from employee where eid="+id);
                        while(rs.next())
                        {
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                        }
                        System.out.println("are you sure want to delete (yes/no) :");
                        String str=new Scanner(System.in).next();
                        if(str.equals("yes")){
                        stmt.executeUpdate("delete from employee where eid="+id);
                        }
                        break;
                    }
                    case 5: {
    
                        System.out.println("------------");
                        System.out.println("1. search by ID");
                        System.out.println("2. search by Name");
                        System.out.println("3. Search by Age");
                        System.out.println("4. search by Designation");
                        System.out.println("5. search by Salary");
                        System.out.println("------------");
                        int numDisplay = Menu.readChoice(5);
                        rs=null;
                       
                    switch (numDisplay) {
                        case 1:
                            System.out.println("Enter the employee id to search:");
                            int id = scan.nextInt();
                            rs = stmt.executeQuery("select * from employee where eid ="+id);
                            break;
                        case 2:
                            System.out.println("Enter the employee name to search:");
                         //   scan.nextLine();
                            String ename = scan.nextLine();
                    
                            rs = stmt.executeQuery("select * from employee where name ='"+ename+"'");
                            break;
                        case 3:
                            System.out.println("Enter the employee age to search:");
                            int eage = scan.nextInt();
        
                            rs = stmt.executeQuery("select * from employee where age ="+eage);
                            break;
                        case 4:
                            System.out.println("Enter the employee designation to search:");
                           // scan.nextLine();
                            String edesg = scan.nextLine();
                    
                            rs = stmt.executeQuery("select * from employee where designation ='"+edesg+"'");
                            break;
                        case 5:
                            System.out.println("Enter the employee salary to search:");
                            float esal = scan.nextFloat();
                        
                            rs = stmt.executeQuery("select * from employee where salary ="+esal);
                            break;
                    }

                    if(rs!=null){
                      while(rs.next())
                      {
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                      }
                    }
                    else System.out.println("no employee found on your request ");
                        break;
                    }

                    case 6: {
                         System.out.println("Data saved successfully");
                        break;
                    }

                    default: {
                        System.out.println("Enter the choice from 1 to 6");
                        break;
                    }
                }
            }
            con.close();
            pstmt.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

  
}
