/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btassesment;

import java.util.ArrayList;

/**
 *
 * @author zahid
 */
public class Package {
    private String name;
    private ArrayList<Package> dependency;
    
    public Package(String inputName) {
        name = inputName;
        dependency = new ArrayList<Package>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addDependency(Package inputDependency) {
        dependency.add(inputDependency);
    }
    
    public ArrayList getDependencies() {
        return dependency;
    }
}
