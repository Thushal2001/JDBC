package lk.ijse.singleton;

/*
    @author DanujaV
    @created 3/14/23 - 4:36 PM   
*/

public class Main {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        A a3 = new A();

        System.out.println("a1: " + a1);
        System.out.println("a2: " + a2);
        System.out.println("a3: " + a3);

        System.out.println("----------------");
//        new B();
        B b1 = B.getInstance();
        B b2 = B.getInstance();
        B b3 = B.getInstance();

        System.out.println("b1: " + b1);
        System.out.println("b2: " + b2);
        System.out.println("b3: " + b3);
    }
}
