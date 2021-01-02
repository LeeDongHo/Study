public class CloneTest {
    public static void main(String[] args) {
        Point original = new Point(3,5);
        Point copy = (Point)original.clone();
        System.out.println(original);
        System.out.println(copy);
    }
}

class Point implements Cloneable {
    int x;
    int y;
    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x="+x+", y="+y;
    }

    public Object clone() {
        Object obj = null;
        try {
            // clone 메서드에는 CloneNotSupportedException이 선언되어 있다.
            obj = super.clone();

            // 배열의 복사
            /*
            int[] arr1  = arr.clone();
            System.arrayCopy(arr,0,arr1,0,arr.length);
             */
        }catch (CloneNotSupportedException e) {}
        return obj;
    }
}
