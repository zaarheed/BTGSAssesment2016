/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btassesment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zahid
 */
public class BTAssesment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Initalise vars for File I/O
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        ArrayList<Package> packages = new ArrayList<>();
        
        
        // while the input file has another line of text
        while(sc.hasNextLine()) {
            
            String input = sc.nextLine(); // read line from file
            String replaced = input.replace(" -> ", " "); // quick way to parse file but not good for error handling
            String[] packageName = replaced.split(" "); // split file at all spaces
            int[] packageExists = new int[packageName.length];
            
            //DEBUG: Reading file correctly?
            //for(int i=0; i<packageName.length;i++) {
            //    System.out.println(i + ". " + packageName[i]);
            //}
            //System.out.println("");
            
            
            // Check if package already exists in list
            for(int i=0;i<packageName.length;i++) {
                packageExists[i] = checkPackageExists(packageName[i], packages);
            }
            
            //DEBUG: packages exist?
            //for(int i=0;i<packageName.length;i++) {
            //    System.out.println("Package " + packageName[i] + " exists at Index " + packageExists[i]);
            //}
            //System.out.println("");
            
            
            // if package doesnt exist, create it
            for(int i=0;i<packageName.length;i++) {
                if(packageExists[i] == -1) {
                    packages.add(new Package(packageName[i]));
                }
            }
            
            //DEBUG: list through all packages in list
            //for(int i=0;i<packages.size();i++) {
            //    System.out.println("Package " + packages.get(i).getName() + " exists at Index " + i);
            //}
            //System.out.println("");
            
            
            // update package indexes
            for(int i=0;i<packageName.length;i++) {
                packageExists[i] = checkPackageExists(packageName[i], packages);
            }
            
            //DEBUG: all packages should now exist
            //for(int i=0;i<packageName.length;i++) {
            //    System.out.println("Package " + packageName[i] + " exists at Index " + packageExists[i]);
            //}
            //System.out.println("");
            
            // add dependencies
            for(int i=1;i<packageName.length;i++) {
                packages.get(packageExists[0]).addDependency(packages.get(packageExists[i]));
            }
            
        }
        
        
        //DEBUG: print list with dependencies
        //for(int i=0;i<packages.size();i++) {
        //    System.out.print(packages.get(i).getName() + " -> ");
        //    ArrayList<Package> temp = packages.get(i).getDependencies();
        //    for(int j=0;j<temp.size();j++) {
        //        System.out.print(temp.get(j).getName() + " ");
        //    }
        //    System.out.println("\n");
        //}
        //System.out.println("");
        
        //declare vars for next part
        int[] requested = new int[args.length-1];
        
        //DEBUG: Now need to check against input arguments - args[1+]
        //System.out.println("Requested: ");
        //for(int i=1;i<args.length;i++) {
        //    // Print requested
        //    System.out.println(args[i]);
        //}
        //System.out.println("");
        
        // check if requested is in ArrayList
        for(int i=1;i<args.length;i++) {
            requested[i-1] = checkPackageExists(args[i],packages);
        }
        
        //DEBUG: requested exist at
        //System.out.println("Requested exist at: ");
        //for(int i=1;i<args.length;i++) {
        //    // Print requested
        //    System.out.println(args[i] + " exists at " + requested[i-1]);
        //}
        //System.out.println("");
        
        // print with dependencies
        for(int i=0;i<requested.length;i++) {
            if(requested[i] != -1) {
                // exists, print name and dependencies
                System.out.print(packages.get(requested[i]).getName());
                System.out.print(" -> ");
                
                // Array with dependencies
                String print = printDependencies(packages.get(requested[i]),"");
                print = sortAlphabetically(print);
                System.out.println(print);
            }
            else {
                // requested package not in list
                System.out.print(args[i+1]);
                System.out.println(" -> ");
            }
        }
        
        
    }
    
    public static int checkPackageExists(String name, ArrayList<Package> packages) {
              
        for(int i=0;i<packages.size();i++) {
            if(packages.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public static String printDependencies(Package a, String b) {
        ArrayList<Package> dependencies = a.getDependencies();
        
        for(int i=0;i<dependencies.size();i++) {
            if(!(b.contains(dependencies.get(i).getName())) && !a.getName().equals(dependencies.get(i).getName())) {
                b = b + dependencies.get(i).getName() + " ";
            }
            b = printDependencies(dependencies.get(i),b); //recursive call
        }
        return b;
        
    }
    
    public static String printDependenciesLegacy(Package a, String b) {
        ArrayList<Package> dependencies = a.getDependencies();
        for(int i=0;i<dependencies.size();i++) {
            System.out.print(dependencies.get(i).getName() + " ");
            printDependencies(dependencies.get(i),b); //recursive call
        }
        return b;
        
    }
    
    public static String sortAlphabetically(String input) {
        String words[] = input.split(" ");
        
        String output = "";
        boolean flag = true;
        String temp;
        
        while(flag) {
            flag = false;
            for(int j=0; j<words.length-1;j++) {
                if( (words[j]).compareToIgnoreCase(words[j+1]) > 0) {
                    temp = words[j];
                    words[j] = words[j+1];
                    words[j+1] = temp;
                    flag = true;
                }
            }
        }
        
        for(int i=0;i<words.length;i++) {
            output = output + words[i] +" ";
        }
        
        return output;
    }
    
}



                         