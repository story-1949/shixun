class Single{
    private static Single s;
    private Single(){
    }

    public static Single getSingle(){
        if(s==null){
            s=new Single();
        }
        return s;
    }
public class SingleTest{
        public static void main (String[]args){
            Single single1=Single.getSingle();
            Single single2=Single.getSingle();
            System.out.println("s1:"+single1.toString() + "s2:"+single2.toString());
    }
}
}