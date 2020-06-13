import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Hello {

    private static int[] nums = {3, 5, 7, 2, 9, 4, 1, 0, 6, 2, 8, 4};

    public static void main(String[] args) {
        Node root = init();
        //System.out.println(Arrays.toString(nums).substring(1));
        //level(root);
        System.out.println();
        preDigui(root);
        System.out.println();
        pre(root);
    }

    public static void postDigui(Node root){
        if (null == root) {
            return ;
        }
        postDigui(root.left);
        postDigui(root.right);
        System.out.print(root.value + ", ");
    }

    public static void post(Node root){
        if (null == root) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Node last = root;
        while (!stack.isEmpty() || null != root) {
            while (null != root) {
                stack.add(root);
                root = root.left;
            }
            root = stack.peek();
            if (last == root.right || null == root.right) {
                System.out.print(root.value + ", ");
                stack.pop();
                last = root;
                root = null;
            } else {
                root = root.right;
            }
        }
    }

    public static void inDigui(Node root){
        if (null == root) {
            return ;
        }
        inDigui(root.left);
        System.out.print(root.value + ", ");
        inDigui(root.right);
    }

    public static void in(Node root){
        if (null == root) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || null != root) {
            while (null != root) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.value + ", ");
            root = root.right;
        }
    }

    public static void preDigui(Node root){
        if (null == root) {
            return ;
        }
        System.out.print(root.value + ", ");
        preDigui(root.left);
        preDigui(root.right);
    }

    public static void pre(Node root){
        if (null == root) {
            return ;
        }
        Stack<Node> stack = new Stack();
        while (!stack.isEmpty() || null != root) {
            while (null != root) {
                System.out.print(root.value + ", ");
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }


    public static void level(Node root){
        if (null == root) {
            return ;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.print(root.value + ", ");
            if (null != root.left) {
                queue.add(root.left);
            }
            if (null != root.right) {
                queue.add(root.right);
            }
        }
    }

    public static Node insert(Node root, Node node){
        if (null == root) {
            return node;
        }
        if (root.value >= node.value) {
            root.left = insert(root.left, node);
        } else {
            root.right = insert(root.right, node);
        }
        return root;
    }

    public static Node init(){
        Node root = new Node(3);
        for (int i = 1; i < nums.length; i++) {
            insert(root, new Node(nums[i]));
        }
        return root;
    }


    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
