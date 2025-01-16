sealed class A permits B {
    private static A aobj = null;

    private A() {
       
    }

    public static A getObjA() throws ObjectException {
        if (aobj == null) {
            aobj = new A();
        } else {
            throw new ObjectException("Only one instance of A is allowed.");
        }
        return aobj;
    }
}

final class B extends A {
    private static B bobj = null;

    private B() {
        
    }

    public static B getObjB() throws ObjectException {
        if (bobj == null) {
            bobj = new B();
        } else {
            throw new ObjectException("Only one instance of B is allowed.");
        }
        return bobj;
    }
}

class ObjectException extends Exception {
    public ObjectException() {
        super();
    }

    public ObjectException(String msg) {
        super(msg);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            A obj1 = A.getObjA(); 
            A obj2 = A.getObjA(); 

            B obj3 = B.getObjB(); 
            B obj4 = B.getObjB(); 
        } catch (ObjectException e) {
            System.out.println(e.getMessage());
        }
    }
}
