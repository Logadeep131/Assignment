sealed class A permits B
{
	private static A obj=null;
	
	A()
	{
		if(this instanceof A)
		{
			A=this;	
		}
		else if(this instanceof B)
		{
			System.out.println("instance b is created");
			
		}
		else throw new AlreadyExistException();
	}
	public static A getobj()
	{
		if(obj==null)
		{
		 obj=new A();
		}
		return obj;
	}

}
final class B extends A
{
	private static final B obj=new B();
	private B()
	{
	}
	public static B getobjB()
	{
		return obj;
	}
}

class AlreadyExistException extends Exception
{
	AlreadyExistException()
	{
		super();
	}
	AlreadyExistException(string msg)
	{
		super(msg);
	}
}
class solution
{
	public static void main(String args[])
	{
		try	
		{
		   A obj1= new A();
		   B obj2=getobjB();
		   System.out.println(obj1);
		   System.out.println(obj2);
		   A obj3=getobjA();
		   B obj4=getobjB();
		   System.out.println(obj3);
		   System.out.println(obj4);
		   
		}
		catch(AlreadyExistException )
		{
		System.out.println("only one object can be created");
		}
	}
}


