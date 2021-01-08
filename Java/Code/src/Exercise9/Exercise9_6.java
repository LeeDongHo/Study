package Exercise9;

public class Exercise9_6 {
    public static void main(String[] args) {
        String src = "12345";
        System.out.println(fillZero(src,10));
        System.out.println(fillZero(src,-1));
        System.out.println(fillZero(src,3));
    }

    public static String fillZero(String src, int length)
    {
        if(src == null || src.length() == length)
            return src;

        if(length <= 0)
            return "";

        if(src.length() >= length)
            return src.substring(length);

        char[] buffer = new char[length];
        for(int i=0;i<src.length();i++)
            buffer[i] = '0';
        try {
            System.arraycopy(src.toCharArray(),0, buffer, length - src.length(), src.length());
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("msg : "+e.toString()+" src.length() : " + src.length());
        }
        return new String(buffer);
    }
}
