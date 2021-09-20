package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.Node;
import model.Users;

public class UsersController {

  private Node start;

  public UsersController() {
    start = null;
  }

  public String addToStart(Users user) {
    Node newNode = new Node(user);
    try {
      user.setPassword(encryptPassword(user.getPassword()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    newNode.setNext(start);
    start = newNode;
    return "Usuario " + user.getUsername() + " cadastrado com sucesso!";
  }
  
  public String addToFinal(Users user) {
    if (emptyList()) {
      addToStart(user);
    } else {
      Node aux = start;
      while (aux.getNext() != null) aux = aux.getNext();
      Node newNode = new Node(user);
      try {
        user.setPassword(encryptPassword(user.getPassword()));
      } catch (Exception e) {
        e.printStackTrace();
      }
      aux.setNext(newNode);
    }
    return user.getUsername();
  }

  private boolean emptyList() {
    if (start == null) return true; else return false;
  }

  private String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
    byte MessageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

    StringBuilder sB = new StringBuilder();

    for (byte b : MessageDigest) {
      String strMD = String.format("%02X", b & 0xFF);
      //System.out.println(b + "\t==>\t" + strMD);
      sB.append(strMD);
    }
    System.out.println(sB.toString());
    return sB.toString();
  }

  public String login(Users userLogin) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    Node aux = start;
    String password = encryptPassword(userLogin.getPassword());

    while (aux != null) {
      if (aux.getUser().getUsername().equals(userLogin.getUsername())) {
        if (aux.getUser().getPassword().equals(password)) return "\nLogin efetuado com sucesso.\n";
      }
      aux = aux.getNext();
    }
    return "\nUsuario ou senha não encontrados.\n";
  }

  public void showDatabase() {
    if (emptyList()) {
      System.err.println("Lista vazia");
    } else {
      Node aux = start;
      while (aux != null) {
        System.out.println(
          "Username: " +
          aux.getUser().getUsername() +
          "\nPassword: " +
          aux.getUser().getPassword()
        );
        aux = aux.getNext();
      }
    }
  }
}
