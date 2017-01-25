import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingDeque;

/**
 * Created by zhang on 2017/1/24.
 */
public class RBTree {//左边小于x 右边大于等于x
    public Node root;
    private List<Integer> sortList=null;//测试用
    private static final byte RED=Node.RED,BLACK=Node.BLACK;






    //*以下是红黑树插入
    public RBTree insert(int node){
        return insert(new Node(node));
    }
    public RBTree insert(Node node){
        if(root!=null){
            findLocation(node);//先插入
            node.color=Node.RED;
            fixUp(node);//调整
        }else
            root=node;
        root.color=BLACK;
        return this;
    }
    private void findLocation(Node node){
        Node find=root;
        while (true){
            if (node.value<find.value){
                if(find.left==null){
                    find.setLeft(node);
                    return;
                }
                else
                    find=find.left;
            }
            else {
                if(find.right==null){
                    find.setRight(node);
                    return;
                }
                else find=find.right;
            }
        }
    }
    private void fixUp(Node node) {
        if(node==root)
            return;
        if (node.parent.color == Node.RED) {
            Node uncle=node.uncle();
            if (uncle!=null&&uncle.color == Node.RED) {//case 1
                node.parent.color = BLACK;
                uncle.color = BLACK;
                node.parent.parent.color = RED;
                fixUp(node.parent.parent);
                return;
            }
            if (node.parent.parent.left == node.parent) {//父节点是左子树
                if (node.parent.right == node) {//case 2
                    node = node.parent;
                    node.leftRotate();
                }
                node.parent.color = BLACK; //case 3
                node.parent.parent.color = RED;
                if(node.parent.parent==root)
                    root=node.parent.parent.left;
                node.parent.parent.rightRotate();
            } else {                                   //父节点是右子树
                if (node.parent.left == node) {//case 2
                    node = node.parent;
                    node.rightRotate();
                }
                node.parent.color = BLACK; //case 3
                node.parent.parent.color = RED;
                if(node.parent.parent==root)
                    root=node.parent.parent.right;
                node.parent.parent.leftRotate();
            }
        }
    }






    //**以下是红黑树测试
    @Override
    public String toString(){
        return toString(root)+"\n";
    }
    private String toString(Node node){
        StringBuffer buffer=new StringBuffer();
        for(int in:sort())
            buffer.append(in).append(" ");
        return buffer.append("\n").append(sortList.size()+"\n").toString();
    }
    public boolean isBRTree(){ //判断是否为红黑树
        if(root.color!=Node.BLACK) {
            System.out.printf("root is not BLACK");
            return false;
        }
        if (!isSort()){
            System.out.println("not sorted");
            return false;
        }
        return isBRTree(root)>0;
    }
    private int isBRTree(Node node){//判断是否满足红黑树的两个特性 1. 红不连续 2. 黑长相等
        if(node==null)
            return 1;
        if(node.color==Node.RED&&node.parent.color==Node.RED){
            System.out.printf("The red node "+node.value+" has a RED parent "+node.parent.value+"\n");
            return -1;
        }
        int leftHigh=isBRTree(node.left);
        int rightHigh=isBRTree(node.right);
        if(leftHigh>0&&leftHigh==rightHigh)
            return node.color == Node.BLACK ? leftHigh + 1 : leftHigh;
        System.out.println("Not Blance in Node "+node.value);
        return -1;
    }
    public boolean isSort(){//判断是否为排序二叉树
        Integer temp=null;
        for(int in:sort()) {
            if(temp!=null&&in<temp)
                return false;
            temp=in;
        }
        return true;
    }
    public List<Integer> sort(){
        sortList=null;
        sort(this.root);
        return sortList;
    }
    private void sort(Node node){
        if(sortList==null)
            sortList=new ArrayList<>();
        if(node!=null){
            sort(node.left);
            sortList.add(node.value);
            sort(node.right);
        }
    }
}
