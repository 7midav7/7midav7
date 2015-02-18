import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by lenovo on 2/17/2015.
 */

class Node{
    public long val;
    public int level;
    public int lengthTrace;
    public Node right;
    public Node left;

    public boolean inTrace = false;
    public boolean moreThanOneVariant = true;

    public Node endLeft;
    public Node endRight;

    Node(int val) {
        this.val = val;
        this.level = 1;
    }
}

public class Main {

    private static Node root = new Node(Integer.MAX_VALUE);
    private static PrintWriter writer;
    private static Node theBestRoot;

    private static int helpCount = 0;
    private static Node parentMedium;
    private static Node medium;
    private static Node afterMedium;
    private static Node parentAfterMedium;

    public static Node add(Node cur, int value){
        if ( cur == null ){
            return new Node(value);
        }
        if (value < cur.val){
            cur.left = add(cur.left, value);
        } else {
            cur.right = add(cur.right, value);
        }
        return cur;
    }

    public static void show(Node cur){
        if (cur == null) {
            return;
        }
        writer.println(cur.val /*+ " " + cur.level*/);
        show(cur.left);
        show(cur.right);
    }

    public static int getLevel(Node node){
        if (node == null) return 0;
        return node.level;
    }

    public static boolean firstBetter(Node first, Node second){
        if (second == null) return true;
        if (second.lengthTrace < first.lengthTrace) return true;
        if (second.lengthTrace > first.lengthTrace) return false;
        if (second.endLeft.val + second.endRight.val
                > first.endLeft.val + first.endRight.val) return true;
        if (second.endLeft.val + second.endRight.val
                < first.endLeft.val + first.endRight.val) return false;
        return (first.val < second.val); // ? maybe will be upgrade
    }

    public static Node getList(Node node){
        if (node.left != null) return node.left;
        return node.right;
    }

    public static Node getRightList(Node root, Node from){
        if ( root == from ) return root.right;
        return getList(from);
    }

    public static Node getLeftList(Node root, Node from){
        if (root == from) return root.left;
        return getList(from);
    }

    public static void init(Node node, Node endLeft, Node endRight){
        node.endLeft = endLeft;
        node.endRight = endRight;
    }

    public static void findTraceWithRoot(Node node, Node leftNode, Node rightNode){
        boolean moreThanOneVariant = false; // !!! warning !!!

        Node endNotRoot;
        if ( getLevel(node.left) >= getLevel(node.right) ){
            endNotRoot = getLeftList(node, leftNode);
        } else {
            endNotRoot = getRightList(node, rightNode);
        }

        if (leftNode == null || rightNode == null){
            init(node, node, endNotRoot);
            node.lengthTrace = node.level;
            node.moreThanOneVariant = false;
            return;
        }

        Node leftEnd = leftNode;
        Node rightEnd = rightNode;
        if (leftNode.val - getLeftList(node, leftNode).val >
                rightNode.val - getRightList(node, rightNode).val){
            leftEnd = getLeftList(node, leftNode);
        }
        if (leftNode.val - getLeftList(node, leftNode).val <=       //attention
                rightNode.val - getRightList(node, rightNode).val){
            rightEnd = getRightList(node, rightNode);
        }
        if (leftNode.val - getLeftList(node, leftNode).val ==
                rightNode.val - getRightList(node, rightNode).val){
            moreThanOneVariant = true;
        }

        if (leftEnd == node || rightEnd == node){
            init(node, node, endNotRoot);
            node.lengthTrace = node.level;
            node.moreThanOneVariant = false;
            return;
        }

        int length = getLevel(node.left) + getLevel(node.right);
        if (node.level > length){
            init(node, node, endNotRoot);
            node.lengthTrace = node.level;
            node.moreThanOneVariant = false;
            return;
        }
        if (node.level < length){
            init(node, leftEnd, rightEnd);
            node.lengthTrace = length;
            node.moreThanOneVariant = moreThanOneVariant;
            return;
        }

        if(node.val + endNotRoot.val < leftEnd.val + rightNode.val ){
            init(node, node, endNotRoot);
            node.lengthTrace = length;
            node.moreThanOneVariant = false;
            return;
        }
        if (node.val + endNotRoot.val == leftEnd.val + rightNode.val){
            moreThanOneVariant = true;
        }
        if (node.val + endNotRoot.val >= leftEnd.val + rightNode.val){      // attention
            init(node, leftEnd, rightNode);
            node.lengthTrace = length;
            node.moreThanOneVariant = moreThanOneVariant;
        }
    }



    public static Node preCalc(Node cur, Node parent){
        if (cur == null) return null;

        if ( cur.left == null && cur.right == null ){
            cur.level = 1;
            return parent;
        }

        Node leftNode = preCalc(cur.left, cur);
        Node rightNode = preCalc(cur.right, cur);

        cur.level = Math.max(getLevel(cur.left), getLevel(cur.right)) + 1;

        findTraceWithRoot(cur, leftNode, rightNode);
        if ( firstBetter(cur, theBestRoot) ){
            theBestRoot = cur;
        }

        if ( getLevel(cur.left) >= getLevel(cur.right)){
            return leftNode;
        } else {
            return rightNode;
        }
    }

    public static boolean markTrace(Node cur){
        if ( cur == null ) return false;
        cur.inTrace |= markTrace(cur.left);
        cur.inTrace |= markTrace(cur.right);

        if (cur == theBestRoot.endLeft || cur == theBestRoot.endRight){
            cur.inTrace = true;
        }
        return cur.inTrace;
    }

    public static void prepareToDelete(Node cur, Node parent){
        if (cur == null) return;

        prepareToDelete(cur.left, cur);

        if (cur.inTrace) {
            ++helpCount;
        }
        if (medium != null && afterMedium == null){
            afterMedium = cur;
            parentAfterMedium = parent;
        }
        if ( medium == null && helpCount > theBestRoot.lengthTrace / 2){
            medium = cur;
            parentMedium = parent;
        }

        prepareToDelete(cur.right, cur);
    }

    public static void changeLinkParent(Node parent, Node child, Node newChild){
        if (parent.left == child){
            parent.left = newChild;
        } else {
            parent.right = newChild;
        }
    }

    public static void deleteMedium(){

        if ( medium.left == null && medium.right == null){
            changeLinkParent(parentMedium, medium, null);
            return;
        }
        if ( medium.left == null ){
            changeLinkParent(parentMedium, medium, medium.right);
            return;
        }
        if ( medium.right == null ){
            changeLinkParent(parentMedium, medium, medium.left);
            return;
        }

        changeLinkParent(parentMedium, medium, afterMedium);
        afterMedium.left = medium.left;
        if (medium.right != afterMedium) {
            afterMedium.right = medium.right;
        }
        changeLinkParent(parentAfterMedium, afterMedium, null);

    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner( new File("tst.in"));
        FileWriter fw = new FileWriter("tst.out");
        writer = new PrintWriter(fw);

        while (scanner.hasNext()){
            int temp = scanner.nextInt();
            add(root, temp);
        }

        preCalc(root.left, root);

        if ( ( root.left.level != 1 ) && ( theBestRoot.lengthTrace % 2 == 1 )
                && (!theBestRoot.moreThanOneVariant) ){
            markTrace(theBestRoot);
            prepareToDelete(root.left, root);
            deleteMedium();
        }

        show(root.left);        // case with one !!!!

        writer.flush();
        writer.close();
    }
}
