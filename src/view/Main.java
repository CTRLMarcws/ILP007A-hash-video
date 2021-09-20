package view;

import controller.UsersController;
import model.Users;
import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    UsersController uc = new UsersController();

    int opc = 0;

    while (opc != 9) {
      System.out.println(
        "1 - Para cadastrar novo usuario" +
        "\n2 - Para fazer login" +
        "\n3 - Para visualizar usuarios cadastrados" +
        "\n9 - Para encerrar"
      );
      opc = Integer.parseInt(scan.nextLine());

      switch (opc) {
        case 1:
          Users user = new Users();

          System.out.print("Digite o nome do novo usuario: ");
          user.setUsername(scan.nextLine());

          System.out.print("Digite a senha do novo usuario: ");
          user.setPassword(scan.nextLine());

          System.out.println(uc.addToStart(user));
          break;
        case 2:
          Users userLogin = new Users();
          System.out.print("Digite o nome do usuario: ");
          userLogin.setUsername(scan.nextLine());

          System.out.print("Digite a senha do usuario: ");
          userLogin.setPassword(scan.nextLine());
          try {
            System.out.println(uc.login(userLogin));
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case 3:
          uc.showDatabase();
          break;
        case 9:
          System.exit(0);
        default:
          break;
      }
    }
    scan.close();
  }
}
