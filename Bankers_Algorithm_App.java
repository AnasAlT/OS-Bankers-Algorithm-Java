/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bankers_Algorithm;
import java.util.Scanner;
/**
 *
 * @author Asura
 */
public class Bankers_Algorithm_App {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Processes");
        int p = sc.nextInt();
        System.out.println("Enter the number of Resources");
        int r = sc.nextInt();
        Bankers_Algorithm BA = new Bankers_Algorithm(p,r);
        BA.insertingValues();
        BA.isSafe();
        System.out.println("is there a process request you want to test? (y/n)");
        char y = sc.next().charAt(0);
        if(y == 'y'){
            BA.requestCheck();
        }
    }
}
