/**
 * Created by zhang on 2017/1/24.
 */
public class Node {
    public int value;
    public static final byte RED=1,BLACK=0;
    public Node left,right,parent;
    public byte color=RED;
    public Node(){}
    public Node(int value){
        this.value=value;
    }
    public Node setLeft(Node node){
        this.left=node;
        if(node!=null)
             node.parent=this;
        return this;
    }
    public Node setRight(Node node){
        this.right=node;
        if(node!=null)
            node.parent=this;
        return this;
    }
    public Node leftRotate(){
        if(right==null){
            System.out.println("Left rotate need a notnull rightChild!");
            return this;
        }
        //1.将节点x的父节点的对应指针指向x的右节点
        if(this.parent!=null){
            if (parent.left==this)
                parent.setLeft(this.right);
            else
                parent.setRight(this.right);
        }else
            this.right.parent=null;
        //2.将x变为其右节点的左节点
        Node temp = right.left;
        this.right.setLeft(this);
        //3.将保存的左节点转移到x的右节点处
        setRight(temp);
        return this;
    }

    public Node rightRotate(){
        if(left==null){//不应该发生
            System.out.println("Right rotate need a notnull leftChild!");
            return this;
        }
        //1.将节点x的父节点的对应指针指向x的左节点
        if(this.parent!=null){
            if (parent.left==this)
                parent.setLeft(this.left);//
            else
                parent.setRight(this.left);//
        }else
            this.left.parent=null;//
        //2.将x变为其左节点的右节点
        Node temp = left.right;//
        this.left.setRight(this);//
        //3.将保存的右节点转移到x的左节点处
        setLeft(temp);//
        return this;
    }
    public Node uncle(){
        if(parent==null||parent.parent==null){//红黑树种不可能发生
            System.out.println("This node not has uncle!");
            return null;
        }
        if(parent.parent.left==parent)
            return parent.parent.right;
        else
            return parent.parent.left;
    }

}
