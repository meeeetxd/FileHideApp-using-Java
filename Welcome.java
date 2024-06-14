package views;

import DAO.UserDAO;
import model.User;
import service.GenerateOTP;
import service.sendOTPService;
import service.userService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----Welcome to the Application----");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 3 to Exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        }catch(IOException e) {
            e.printStackTrace();
        }
        switch(choice){
            case 1 : login();
            case 2 : signup();
            case 3 : System.exit(0);
        }
    }

    private Object signup() {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = sc.nextLine();

        System.out.println("Enter email:");
        String email = sc.nextLine();

        String generateOTP = GenerateOTP.getOTP();
        sendOTPService.sendOTP(email,generateOTP);
        System.out.println("Enter OTP: ");

        String otp = sc.nextLine();
        if(otp.equals(generateOTP)){
            User user = new User(name, email);
            int response = userService.saveUser(user);
            switch(response){
                case 0 :
                    System.out.println("User registered.");
                case 1:
                    System.out.println("User already Exists.");
            }
        }
        return null;
    }

    private Object login() {
        // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Email: ");
    String email = sc.nextLine();
    try{
        if(UserDAO.isExists(email)){
            String generatedOTP = GenerateOTP.getOTP();
            sendOTPService.sendOTP(email,generatedOTP);
            System.out.println("Enter OTP: ");
            String otp = sc.nextLine();

            if(otp.equals(generatedOTP)){
                new UserView(email).home();
//                System.out.println("Welcome!!!");
            }else {
                System.out.println("Wrong OTP. Try again");
            }
        }else{
            System.out.println("User not found....");
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
        return null;
    }
}

