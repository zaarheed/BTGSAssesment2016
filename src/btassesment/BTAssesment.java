/*
 * Name: BTGSAssesment2016
 * Description: BT Graduate Scheme Coding Test 2015/16
 * Author: Zahid Mahmood
 * Author URL: http://www.zahidmahmood.co.uk
 */
package btassesment;

// import libraries required program
// avoid .* suffixes to reduce program size at compile time
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zahid
 */
public class BTAssesment {

    public static void main(String[] args) throws FileNotFoundException {
        
        // Initalise vars for File I/O
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        ArrayList<Package> packages = new ArrayList<Package>();
        
        
        // while the input file has another line of text
        readFile(sc, packages);
        
        // determine packages requested
        int[] requested = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            requested[i - 1] = checkPackageExists(args[i],packages);
        }
        
        // print output
        printOutput(requested, packages, args);    
    }
    
    public static int checkPackageExists(String name, ArrayList<Package> packages) {
              
        for (int i = 0; i < packages.size(); i++) {
            if(packages.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public static String printDependencies(Package a, String b) {
        ArrayList<Package> dependencies = a.getDependencies();
        
        for (int i = 0; i < dependencies.size(); i++) {
            if (!(b.contains(dependencies.get(i).getName())) && !a.getName().equals(dependencies.get(i).getName())) {
                b = b + dependencies.get(i).getName() + " ";
            }
            b = printDependencies(dependencies.get(i),b); //recursive call
        }
        return b;
        
    }
    
    public static String printDependenciesLegacy(Package a, String b) {
        ArrayList<Package> dependencies = a.getDependencies();
        for (int i = 0; i < dependencies.size(); i++) {
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
        
        while (flag) {
            flag = false;
            for (int j = 0; j<words.length - 1; j++) {
                if( (words[j]).compareToIgnoreCase(words[j + 1]) > 0) {
                    temp = words[j];
                    words[j] = words[j+1];
                    words[j + 1] = temp;
                    flag = true;
                }
            }
        }
        
        for (int i = 0; i < words.length; i++) {
            output = output + words[i] +" ";
        }
        
        return output;
    }
    
    public static void readFile(Scanner sc, ArrayList<Package> packages) {
        while (sc.hasNextLine()) {
            
            String input = sc.nextLine(); // read line from file
            
            // if line doesnt contain ' X ->' terminate program
            // should use RegEx but I dont understand
            if (!input.contains("->")) {
                System.exit(-1);
            }
            
            String replaced = input.replace(" -> ", " "); // quick way to parse file but not good for error handling
            String[] packageName = replaced.split(" "); // split file at all spaces
            int[] packageExists = new int[packageName.length];            
            
            // Check if package already exists in list
            for (int i = 0; i < packageName.length; i++) {
                packageExists[i] = checkPackageExists(packageName[i], packages);
            }            
            
            // if package doesnt exist, create it
            for (int i = 0; i < packageName.length; i++) {
                if(packageExists[i] == -1) {
                    packages.add(new Package(packageName[i]));
                }
            }
            
            // update package indexes
            for (int i = 0; i < packageName.length; i++) {
                packageExists[i] = checkPackageExists(packageName[i], packages);
            }
            
            // add dependencies
            for (int i = 1; i < packageName.length; i++) {
                packages.get(packageExists[0]).addDependency(packages.get(packageExists[i]));
            }
            
        }
    }
    
    public static void printOutput(int[] requested, ArrayList<Package> packages, String[] args) {
        for (int i = 0; i < requested.length; i++) {
            if (requested[i] != -1) {
                // exists, print name and dependencies
                System.out.print(packages.get(requested[i]).getName());
                System.out.print(" -> ");
                
                // Array with dependencies
                String print = printDependencies(packages.get(requested[i]),"");
                print = sortAlphabetically(print);
                System.out.println(print);
            } else {
                // requested package not in list
                System.out.print(args[ i + 1]);
                System.out.println(" -> ");
            }
        }
    }
    
}    