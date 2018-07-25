package rocks.huanglei;


public class EADemo {
    public static final long LEN_IN_GO_DEMO   = 10_000_000_000L;
    public static final int  LEN_IN_THIS_DEMO = (int)LEN_IN_GO_DEMO / 10;

    static {
        System.out.println();
    }

    public static void main(String[] args) {
        //for (int i = 0; i < 100; i++) {
        long[] c = new long[LEN_IN_THIS_DEMO]; // ~7.4GB
        System.out.println(c.length);
        //}
    }
}
