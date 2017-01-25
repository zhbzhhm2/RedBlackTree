import java.lang.reflect.Method;
import java.util.Random;

/**
 * Created by zhang on 2017/1/24.
 */
public class Test {
    public static void main(String[] args) {
        int n=10000000;
        int ins[]=new int[n];

        Random random=new Random();
        for (int i=0;i<ins.length;i++)
            ins[i]=random.nextInt(n*10);
        RBTree tree=new RBTree();
        for(int in:ins) {
            tree.insert(in);
        }
        System.out.println(tree);
        if(tree.isBRTree())
            System.out.println("检测通过！");
        else
            System.out.println("检测未通过！！！！");

    }
}
