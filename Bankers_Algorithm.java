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
class Bankers_Algorithm {

    private int P;
    private int R;
    private int[] ava;
    private int[][] claim;
    private int[][] need;
    private int[][] alloc;
    private int[] work;
    private int[] ins;
    private int[] safeSeq;
    private int[] totalalloc;
    private boolean[] f;
    private int[] reqValues;

    public Bankers_Algorithm(int p, int r) {
        P = p;
        R = r;
        need = new int[P][R];
        safeSeq = new int[P];
        alloc = new int[P][R];
        claim = new int[P][R];
        ava = new int[R];
        work = new int[R];
        ins = new int[R];
        totalalloc = new int[R];
        f = new boolean[P];
        reqValues = new int[R];
    }

    public void insertingValues() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Allocation table values (Leave a space after each value)");
        for (int j = 0; j < alloc.length; j++) {
            for (int k = 0; k < alloc[0].length; k++) {
                alloc[j][k] = sc.nextInt();
            }
        }
        System.out.println("Enter Claim table values (Leave a space after each value)");
        for (int j = 0; j < claim.length; j++) {
            for (int k = 0; k < claim[0].length; k++) {
                claim[j][k] = sc.nextInt();
            }
        }
        System.out.println("Enter each instances value");
        for (int j = 0; j < ins.length; j++) {
            ins[j] = sc.nextInt();
        }
        //total allocation
        for (int j = 0; j < totalalloc.length; j++) {
            for (int k = 0; k < P; k++) {
                totalalloc[j] += alloc[k][j];
            }
        }
        for (int j = 0; j < ava.length; j++) {
            ava[j] = ins[j] - totalalloc[j];
        }
        //need table calc
        for (int j = 0; j < P; j++) {
            for (int k = 0; k < R; k++) {
                need[j][k] = claim[j][k] - alloc[j][k];
            }
        }
    }

    public boolean isSafe() {
        for (int i = 0; i < work.length; i++) {
            work[i] = ava[i];
        }
        int counter = 0;

        while (counter < P) {
            boolean SysSafe = false;
            for (int i = 0; i < P; i++) {
                if (f[i] == false) {
                    
                    int j;
                    for (j = 0; j < R; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }
                    if (j == R) {
                        for (int k = 0; k < R; k++) {
                            work[k] += alloc[i][k];
                        }
                        safeSeq[counter++] = i;

                        f[i] = true;

                        SysSafe = true;
                    }
                }
            }
            if (SysSafe == false) {
                System.out.println("The System is UnSafe! ");
                return false;
            }
        }
        System.out.println("The given System is Safe");
        System.out.println("the SAFE Sequence is: ");
        for (int i = 0; i < P; i++) {
            System.out.print("P" + safeSeq[i]);
            if (i != P - 1) {
                System.out.print(",");
            }
        }
        System.out.println("");
        return true;
    }

    public boolean requestCheck() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of the process that requested (0,1,2,3,4)");
        int p = sc.nextInt();
        System.out.println("Enter the requested values");
        for (int j = 0; j < reqValues.length; j++) {
            reqValues[j] = sc.nextInt();
        }
        boolean f = false;
        for (int j = 0; j < R; j++) {
            if (reqValues[j] <= need[p][j] && reqValues[j] <= ava[j]) {
                f = true;
            }
        }
        if (f == true) {
            for (int j = 0; j < ava.length; j++) {
                alloc[p][j] += reqValues[j];
                ava[j] -= reqValues[j];
                need[p][j] -= reqValues[j];
            }
            if (isSafe() == true) {
                System.out.println("Request can be granted");
                return true;
            } else {
                System.out.println("Request cannot be granted and P" + p + " is blocked");
                return false;
            }
        } else {
            System.out.println("Request cannot be granted and P" + p + " is blocked");
            return false;
        }
    }
}
