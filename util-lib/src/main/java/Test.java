import com.moioio.util.MDL;
import com.moioio.util.MyLog;

public class Test {

    public static void main(String[] args)
    {
//        String str = "[page:1,size:20,go:name]";
//        MDL mdl = MDL.parse(str);
        MDL mdl = MDL.Builder.create().add("page",1).get();
        MyLog.log("mdl:"+mdl.getInt("page"));
        MyLog.log("mdl:"+mdl.getInt("size"));
        MyLog.log("mdl:"+mdl.get("go"));

    }
}
