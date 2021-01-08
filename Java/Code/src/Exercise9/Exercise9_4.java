package Exercise9;

public class Exercise9_4 {
    public static void main(String[] args) {
        printGraph(new int[]{3,7,1,4},'*');
    }

    public static void printGraph(int[] dataArr, char ch)
    {
        for(int i=0;i<dataArr.length;i++)
        {
            for(int j=0;j<dataArr[i];j++)
                System.out.print('*');
            System.out.println(dataArr[i]);
        }
    }
}
