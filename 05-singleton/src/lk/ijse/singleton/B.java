package lk.ijse.singleton;

/*
    @author DanujaV
    @created 3/14/23 - 4:38 PM   
*/

public class B {
    private static B b;

    private B() {

    }

    public static B getInstance() {
        if(b == null) {
            b = new B();
            return b;
        } else {
            return b;
        }
    }
}
