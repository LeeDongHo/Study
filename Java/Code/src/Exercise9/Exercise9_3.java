package Exercise9;

public class Exercise9_3 {
    public static void main(String[] args) {
        String fullPath = "C:\\jdk1.5\\work\\PathSeparateTest.java";
        String path = "";
        String fileName = "";

        int lastPosition = fullPath.lastIndexOf("\\");
        path = fullPath.substring(0,lastPosition);
        fileName = fullPath.substring(lastPosition+1);
        System.out.println("full path : " + fullPath);
        System.out.println("path : " + path);
        System.out.println("fileName : " + fileName);
    }
}
