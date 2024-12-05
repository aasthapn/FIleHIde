package view;

import com.google.protobuf.GeneratedMessage;
import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void WelcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Press 1 for login ");
        System.out.println("Press 2 for signUp ");
        System.out.println("Press 0 for exit");

        int choice = -1;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signUp();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice : please choose 1,2 or 0");

        }

    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOtp();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the otp");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)) {
               new Home(email).home();
                } else {
                    System.out.println("Invalid otp");
                }
            } else {
                System.out.println("User doesn't exits");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.nextLine();
        System.out.println("Enter your email");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOtp();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the otp");
        String otp = sc.nextLine();
        if (otp.equals(genOTP)) {
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0:
                    System.out.println("USer registered");
                    break;
                case 1:
                    System.out.println("User already exits");
                    break;
                default:
                    System.out.println("Invalid response");
            }
            System.out.println("Welcome! You have successfully signup");
        } else {
            System.out.println("Invalid otp");
        }
    }


}
