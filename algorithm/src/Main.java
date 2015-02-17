import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by lenovo on 2/14/2015.
 */

class Node{
   public int val;
   public long numChildrens;
   public int level;
   public Node right;
   public Node left;
   public boolean inTrace = false;
   public Node leftPredEndTrace;
   public Node rightPredEndTrace;

    Node(int val) {
        this.val = val;
        this.numChildrens = 0;
        this.level = 1;
    }

}



public class Main {
    static Node root = new Node(Integer.MAX_VALUE); // WARNING
    static PrintWriter writer;
    static Node parentRootTrace;
    static Node rootTrace;
    static Node leftTraceVertex;
    static Node rightTraceVertex;
    static int numTraceVertexWas = 0;
    static Node toDelete;
    static Node parentToDelete;
    static Node afterToDelete;
    static Node parentAfterToDelete;
    static Node secondToDelete;

    public static int getLevelChildren(Node child){
        if (child == null) return 0;
        return child.level;
    }

    public static Node getEndTraceChild(Node child, Node parent){
        if (child == null) return null;
        if (getLevelChildren(child.left) > getLevelChildren(child.right) ){
            return child.leftPredEndTrace;
        }
        if (getLevelChildren(child.left) < getLevelChildren(child.right) ){
            return child.rightPredEndTrace;
        }
        if (child.numChildrens == 0 ) return parent;

        return child.leftPredEndTrace;
    }

    public static int getLengthTrace(Node node){
        int curLevel = getLevelChildren(node.left) + getLevelChildren(node.right) + 1;
        if (getLevelChildren(node.left) != 0 && getLevelChildren(node.right) != 0) --curLevel;
        return curLevel;
    }

    public static boolean theBest(Node cur, Node curRoot){
        if ( curRoot == null ) return true;
        int curLevel = getLengthTrace(cur);
        int curRootLevel = getLengthTrace(curRoot);
        if (curLevel > curRootLevel) return true;
        if (curLevel < curRootLevel) return false;
        if (findSumEnds(cur) < findSumEnds(curRoot)) return true;
        if (findSumEnds(cur) > findSumEnds(curRoot)) return false;
        return (cur.val < curRoot.val);
    }

    public static void add(Node cur, Node parent, int value){
        ++ parent.numChildrens;
        if ( cur == null ){
            if ( value < parent.val ){
                parent.left = new Node(value);
            }
            else {
                parent.right = new Node(value);
            }
            return;
        }
        if (value < cur.val){
           add(cur.left, cur, value);
        } else {
           add(cur.right, cur, value);
        }
    }

    public static void preCalc(Node cur, Node parent){
        if (cur == null || cur.numChildrens == 0 ){
            return ;
        }

        preCalc(cur.left, cur);
        preCalc(cur.right, cur);

        cur.level = Math.max( getLevelChildren(cur.left), getLevelChildren(cur.right)) + 1;
        cur.leftPredEndTrace = getEndTraceChild(cur.left, cur);
        cur.rightPredEndTrace = getEndTraceChild(cur.right, cur);

        if ( theBest(cur, rootTrace) ){
            parentRootTrace = parent;
            rootTrace = cur;
        }
    }

    public static long findSumEnds(Node node){

        long sum = 0;
        if (node.leftPredEndTrace == null && node.rightPredEndTrace == null){
            return 0;
        }
        if (node.leftPredEndTrace == null){
            sum += node.val;
            sum += getMinList(node.rightPredEndTrace, node).val;
            return sum;
        }
        if (node.rightPredEndTrace == null){
            sum += node.val;
            sum += getMinList(node.leftPredEndTrace, node).val;
            return sum;
        }

        long leftDif = node.leftPredEndTrace.val - getMinList(node.leftPredEndTrace, node).val;
        long rightDif = node.rightPredEndTrace.val - getMinList(node.rightPredEndTrace, node).val;

        if (leftDif > rightDif) {
            return getMinList(node.leftPredEndTrace, node).val + node.rightPredEndTrace.val;
        }

        return getMinList(node.rightPredEndTrace, node).val + node.leftPredEndTrace.val;
    }

    public static Node findEndTrace(Node cur, Node parents){
        if ( cur.numChildrens == 0 ){
            return parents;
        }
        cur.inTrace = true;
        if ( getLevelChildren(cur.left) >= getLevelChildren(cur.right) ){
            return findEndTrace(cur.left, cur);
        } else {
            return findEndTrace(cur.right, cur);
        }
    }

    public static Node getMinList(Node cur, Node curRoot){
        if (cur == curRoot && curRoot.leftPredEndTrace == curRoot){
            return cur.left;
        }
        if (cur == curRoot && curRoot.rightPredEndTrace == curRoot){
            return cur.right;
        }
        if ( getLevelChildren(cur.left)  > 0) return cur.left;
        return cur.right;
    }

    public static void markEndsTrace(boolean first, boolean second){
        leftTraceVertex.inTrace = first;
        rightTraceVertex.inTrace = second;
    }

    public static boolean findHalfTrace(Node cur){ // should change min keys
        cur.inTrace = true;
        if (getLevelChildren(cur.left) == 0 ){ // if the end in root
            leftTraceVertex = cur;
            rightTraceVertex = findEndTrace(cur.right, cur);
            rightTraceVertex = getMinList(rightTraceVertex, rootTrace);
            markEndsTrace(true, true);
            return false;
        }
        if (getLevelChildren(cur.right) == 0){
            rightTraceVertex = cur;
            leftTraceVertex = findEndTrace(cur.left, cur);
            leftTraceVertex = getMinList(leftTraceVertex, rootTrace);
            markEndsTrace(true, true);
            return false;
        }
        leftTraceVertex = findEndTrace(cur.left, cur);
        rightTraceVertex = findEndTrace(cur.right, cur);

        long leftDif = leftTraceVertex.val - getMinList(leftTraceVertex, rootTrace).val;
        long rightDif = rightTraceVertex.val - getMinList(rightTraceVertex, rootTrace).val;

        //if (leftDif == rightDif) return true;

        if ( leftDif > rightDif){
            leftTraceVertex = getMinList(leftTraceVertex, rootTrace);
        } else {
            rightTraceVertex = getMinList(rightTraceVertex, rootTrace);
        }

        markEndsTrace(true, true);
        return false;
    }

    public static void show(Node cur){
        if (cur == null) {
            return;
        }
        writer.println(cur.val /*+ " " + cur.level*/);
        show(cur.left);
        show(cur.right);
    }

    public static void findMedium(Node parent, Node cur, int num){
        if (  cur == null ) return;
        findMedium(cur, cur.left, num);
        if (toDelete != null && parentAfterToDelete == null) {
            parentAfterToDelete = parent;
            afterToDelete = cur;
        }
        if ( cur.inTrace ) {
            ++numTraceVertexWas;
            if (numTraceVertexWas == num / 2 + 1) {
                parentToDelete = parent;
                toDelete = cur;
            }
        }
        findMedium(cur, cur.right, num);
    }

    public static void substitude(Node root, Node toDel, Node cur){
        if ( root.left == toDel ){
            root.left = cur;
        } else {
            root.right = cur;
        }
    }

    public static void deleteMedium(){
        if ( toDelete.left != null && toDelete.right != null ) {
            substitude(parentToDelete, toDelete, afterToDelete);
            if ( toDelete.right != afterToDelete ){
                afterToDelete.right = toDelete.right;
            }
            afterToDelete.left = toDelete.left;
            substitude(parentAfterToDelete, afterToDelete, null);
        } else {
            if ( toDelete.left != null ){
                substitude(parentToDelete, toDelete, toDelete.left);
            } else {
                substitude(parentToDelete, toDelete, toDelete.right);
            }
        }
    }

    public static boolean equals(){
        Node lastLeft = leftTraceVertex;
        leftTraceVertex =  getMinList(leftTraceVertex, rootTrace);
        markEndsTrace(true, true);
        findMedium(parentRootTrace, rootTrace, getLengthTrace(rootTrace));

        secondToDelete = toDelete;
        toDelete = null;
        parentAfterToDelete = null;
        numTraceVertexWas = 0;
        markEndsTrace(false, false);

        leftTraceVertex = lastLeft;
        rightTraceVertex = getMinList(rightTraceVertex, rootTrace);
        markEndsTrace(true, true);
        findMedium(parentRootTrace, rootTrace, getLengthTrace(rootTrace));

        return ( toDelete.val == secondToDelete.val );
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(new File("tst.in"));
        writer = new PrintWriter( new FileWriter("tst.out"));

        while(scanner.hasNext()){
            int temp = scanner.nextInt();
            add(root.left, root, temp);
        }
        preCalc(root.left, root);
        if (root.numChildrens == 1){
            writer.println(root.left.val);
            writer.close();
            return;
        }
        boolean isEquals = findHalfTrace(rootTrace);    // not always change position middle
//        if ( !isEquals || !equals() ){
            int lengthTrace = getLengthTrace(rootTrace);
            if ( lengthTrace % 2 == 1 ) {
                findMedium(parentRootTrace, rootTrace, lengthTrace);
                deleteMedium();
            }
 //       }
        show(root.left);
        //writer.println(leftTraceVertex.val + " " + rightTraceVertex.val + " " + toDelete.val + " " + lengthTrace);

        writer.close();
    }
}
