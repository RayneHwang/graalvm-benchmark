package rocks.huanglei;

public class DispatchExample {
    static class Eat {
    }

    static class Drink {
    }

    static class Father {
        public void doSomething(Eat arg) {
            System.out.println("爸爸在吃饭");
        }

        public void doSomething(Drink arg) {
            System.out.println("爸爸在喝水");
        }
    }

    static class Child extends Father {
        public void doSomething(Eat arg) {
            System.out.println("儿子在吃饭");
        }

        public void doSomething(Drink arg) {
            System.out.println("儿子在喝水");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father child  = new Child();
        father.doSomething(new Eat());
        child.doSomething(new Drink());
    }
}
