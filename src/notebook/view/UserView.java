package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String newUserfirstName = prompt("Имя: ");
                    String newUserlastName = prompt("Фамилия: ");
                    String newUserphone = prompt("Номер телефона: ");
                    User u = userController.createUser(newUserfirstName,newUserlastName,newUserphone);
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    String updateFirstName = prompt("Имя: ");
                    String updateLastName = prompt("Фамилия: ");
                    String updatePhone = prompt("Номер телефона: ");
                    userController.updateUser(userId, userController.createUser(updateFirstName,updateLastName,updatePhone));
                case DELETE:
                    String userIdDelete = prompt("Enter user id: ");
                    userController.deleteUser(userIdDelete);
                case LIST:
                    System.out.println(userController.readAll());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
