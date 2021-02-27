import javax.swing.plaf.synth.SynthTextAreaUI;

public class WapperTest {
    public static void main(String[] args) {
        //Example 1
        //Integer i = new Integer(100);
        Integer i = Integer.valueOf(100);
        Integer i2=  new Integer(100);

        System.out.println("i==i2? "+(i==i2));
        System.out.println("i.equals(i2)? " + i.equals(i2));
        System.out.println("i.toString() = " + i.toString());

        System.out.println("MAX_VALUE="+Integer.MAX_VALUE);
        System.out.println("MIN_VALUE="+Integer.MIN_VALUE);
        System.out.println("SIZE="+Integer.SIZE+"bits");
        System.out.println("TYPE="+Integer.TYPE);

        //Example 2
        System.out.println("100(2) -> "+Integer.parseInt("100",2));
        System.out.println("100(8) -> "+Integer.parseInt("100",8));
        System.out.println("100(16) -> "+Integer.parseInt("100",16));
        System.out.println("FF(16) -> "+Integer.parseInt("FF",16));
    }
}
