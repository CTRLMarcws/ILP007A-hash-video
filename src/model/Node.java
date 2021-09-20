package model;

public class Node {
    private Users user;
    private Node next;

    public Node(Users user)
    {
        this.user = user;
        next = null;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
