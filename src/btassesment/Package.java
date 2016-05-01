/*
 * Name: BTGSAssesment2016
 * Description: BT Graduate Scheme Coding Test 2015/16
 * Author: Zahid Mahmood
 * Author URL: http://www.zahidmahmood.co.uk
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
    
    public ArrayList<Package> getDependencies() {
        return dependency;
    }
}
