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

    Node(int val) {
        this.val = val;
        this.numChildrens = 0;
        this.level = 1;
    }

}



public class Main {
    static Node root = new Node(Integer.MAX_VALUE); // WARNING
    static PrintWriter writer;
    static Node predRootTrace;
    static Node rootTrace;
    static Node leftTraceVertex;
    static Node rightTraceVertex;
    static int numTraceVertexWas = 0;
    static Node toDelete;
    static Node predToDelete;
    static Node afterToDelete;
    static Node predAfterToDelete;
    static int lengthTrace = 0;
    static Node secondToDelete;

    public static int getLevelChildren(Node children){
        if (children == null) return 0;
        return children.level;
    }

    public static boolean theBest(Node cur, Node curRoot){
        if ( curRoot == null ) return true;
        int curLevel = getLevelChildren(cur.left) + getLevelChildren(cur.right);
        int curRootLevel = getLevelChildren(curRoot.left) + getLevelChildren(curRoot.right);
        if (getLevelChildren(cur.left) != 0 && getLevelChildren(cur.right) != 0) --curLevel;
        if (getLevelChildren(curRoot.left) != 0 && getLevelChildren(curRoot.right) != 0) --curRootLevel;
        return ( curLevel > curRootLevel) || (curLevel == curRootLevel && cur.val < curRoot.val);
    }

    public static void add(Node cur, Node pred, int value){
        ++ pred.numChildrens;
        if ( cur == null ){
            if ( value < pred.val ){
                pred.left = new Node(value);
            }
            else {
                pred.right = new Node(value);
            }
            return;
        }
        if (value < cur.val){
           add(cur.left, cur, value);
        } else {
           add(cur.right, cur, value);
        }
        cur.level = Math.max( getLevelChildren(cur.left), getLevelChildren(cur.right)) + 1;

        if ( theBest(cur, rootTrace) ){
            predRootTrace = pred;
            rootTrace = cur;
        }
    }

    public static Node findEndTrace(Node cur, Node parents){
        if ( cur.numChildrens == 0 ){
            return parents;
        }
        if ( getLevelChildren(cur.left) >= getLevelChildren(cur.right) ){
            cur.inTrace = true;
            ++ lengthTrace;
            return findEndTrace(cur.left, cur);
        } else {
            cur.inTrace = true;
            ++ lengthTrace;
            return findEndTrace(cur.right, cur);
        }
    }

    public static Node getMinList(Node cur){
        if ( getLevelChildren(cur.left) > 0) return cur.left;
        return cur.right;
    }

    public static void markEndsTrace(boolean first, boolean second){
        leftTraceVertex.inTrace = first;
        rightTraceVertex.inTrace = second;
    }

    public static boolean findHalfTrace(Node cur){ // should change min keys
        cur.inTrace = true;
        lengthTrace += 2;
        if (getLevelChildren(cur.left) == 0 ){ // if the end in root
            leftTraceVertex = cur;
            rightTraceVertex = findEndTrace(cur.right, cur);
            rightTraceVertex = getMinList(rightTraceVertex);
            markEndsTrace(true, true);
            return false;
        }
        if (getLevelChildren(cur.right) == 0){
            rightTraceVertex = cur;
            leftTraceVertex = findEndTrace(cur.left, cur);
            leftTraceVertex = getMinList(leftTraceVertex);
            markEndsTrace(true, true);
            return false;
        }
        leftTraceVertex = findEndTrace(cur.left, cur);
        rightTraceVertex = findEndTrace(cur.right, cur);

        long leftDif = leftTraceVertex.val - getMinList(leftTraceVertex).val;
        long rightDif = rightTraceVertex.val - getMinList(rightTraceVertex).val;

        if (leftDif == rightDif) return true;

        if ( leftDif > rightDif){
            leftTraceVertex = getMinList(leftTraceVertex);
        } else {
            rightTraceVertex = getMinList(rightTraceVertex);
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

    public static void findMedium(Node pred, Node cur, int num){
        if (  cur == null ) return;
        findMedium(cur, cur.left, num);
        if (toDelete != null && predAfterToDelete == null) {
            predAfterToDelete = pred;
            afterToDelete = cur;
        }
        if ( cur.inTrace ) {
            ++numTraceVertexWas;
            if (numTraceVertexWas == num / 2 + 1) {
                predToDelete = pred;
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
            substitude(predToDelete, toDelete, afterToDelete);
            if ( toDelete.right != afterToDelete ){
                afterToDelete.right = toDelete.right;
            }
            afterToDelete.left = toDelete.left;
            substitude(predAfterToDelete, afterToDelete, null);
        } else {
            if ( toDelete.left != null ){
                substitude(predToDelete, toDelete, toDelete.left);
            } else {
                substitude(predToDelete, toDelete, toDelete.right);
            }
        }
    }

    public static boolean equals(){
        leftTraceVertex = getMinList(leftTraceVertex);
        markEndsTrace(true, true);
        findMedium(predRootTrace, rootTrace, lengthTrace);

        secondToDelete = toDelete;
        markEndsTrace(false, false);

        rightTraceVertex = getMinList(rightTraceVertex);
        markEndsTrace(true, true);
        findMedium(predRootTrace, rootTrace, lengthTrace);

        return ( toDelete.val == secondToDelete.val );
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(new File("tst.in"));
        writer = new PrintWriter( new FileWriter("tst.out"));

        while(scanner.hasNext()){
            int temp = scanner.nextInt();
            add(root.left, root, temp);
        }
        if (root.numChildrens == 1){
            writer.println(root.left.val);
            writer.close();
            return;
        }
        boolean isEquals = findHalfTrace(rootTrace);    // not always change position middle
        if ( !isEquals || !equals() ){
            if ( ( lengthTrace % 2 == 1 ) ) {
                findMedium(predRootTrace, rootTrace, lengthTrace);
                deleteMedium();
            }
        }
        show(root.left);
        //writer.println(leftTraceVertex.val + " " + rightTraceVertex.val + " " + toDelete.val + " " + lengthTrace);

        writer.close();
    }
}
