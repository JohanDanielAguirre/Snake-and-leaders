package model;

public class ABB {

    private Node root;

    public void addNode(Player player) {
        if (root == null) { // arbol vacio
            root = new Node(player);
        } else {
            Node newNode = new Node(player);
            addNode(root, newNode);
        }
    }
    private void addNode(Node pointer, Node newNode) {
        if (newNode.getPlayer().getScore() < pointer.getPlayer().getScore()) { // ir hacia el subarbol izquierdo
            if (pointer.getLeft() == null) {
                pointer.setLeft(newNode);
            } else {
                addNode(pointer.getLeft(), newNode); // nueva recursion hacia el nivel inferior del left
            }
        } else { // ir hacia el subarbol derecho
            if (pointer.getRight() == null) {
                pointer.setRight(newNode);
            } else {
                addNode(pointer.getRight(), newNode); // nueva recursion hacia el nivel inferior del right
            }
        }
    }
    public String inorder() {
        String message="";
        message=inorder(root);
        return message;
    }
    private String inorder(Node node) {
        String message = "";
        if (node != null) {
            message += inorder(node.getRight());
            message += (node.getPlayer().getScore() + " ");
            message += inorder(node.getLeft());
        }
        return message;
    }
    private Node getMin(Node pointer){
        if(pointer.getLeft()==null){
            return pointer;
        } else {
            return getMin(pointer.getLeft());
        }
    }

    private Node getSucesor(Node pointer) {
        return getMin(pointer.getRight());
    }
}
