/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pd2;

/**
 *
 * @author Andy Sampurno
 */
class Node {

    float dataAngka;
    char dataOperasi; // +, -, *, /
    Node left;
    Node right;
}

public class Calculator {

    static Node root;

    public static void main(String[] args) {
        root = new Node();
        root.dataOperasi = '*';
        root.left = new Node();
        root.left.dataOperasi = '+';
        root.left.left = new Node();
        root.left.left.dataAngka = 5;
        root.left.right = new Node();
        root.left.right.dataAngka = 4;
        root.right = new Node();
        root.right.dataOperasi = '-';
        root.right.left = new Node();
        root.right.left.dataAngka = 5;
        root.right.right = new Node();
        root.right.right.dataAngka = 4;
        System.out.println(printNodeData(root.left)); // +
        System.out.println(printNodeData(root.left.left)); // 5
        System.out.println(parsePrefix(root)); // * + 5 4 - 5 4
        System.out.println(parseSufix(root));  // 5 4 + 5 4 - *
        System.out.println(parseInfix(root));  // 5 + 4 * 5 - 4
    }

    static String printNodeData(Node node) {
        if (node.dataOperasi == '+' || node.dataOperasi == '-' || node.dataOperasi == '*' || node.dataOperasi == '/') {
            return "" + node.dataOperasi;
        }
        return Float.toString(node.dataAngka);
    }

    static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    static String parsePrefix(Node node) {
        if (isLeaf(node)) {
            return printNodeData(node);
        }
        return printNodeData(node) + " " + parsePrefix(node.left) + " " + parsePrefix(node.right);
    }

    static String parseSufix(Node node) {
        if (isLeaf(node)) {
            return printNodeData(node);
        }
        return parseSufix(node.left) + " " + parseSufix(node.right) + " " + printNodeData(node);
    }

    static String parseInfix(Node node) {
        if (isLeaf(node)) {
            return printNodeData(node);
        }
        return parseInfix(node.left) + " " + printNodeData(node) + " " + parseInfix(node.right);
    }
}
