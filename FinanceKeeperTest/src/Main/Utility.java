/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Vanilla
 */
public class Utility {
    private String id;
    private String utility;
    private String value;
    private String billingcycle;
    private String dateadded;
    
    public Utility(String id, String utility, String value, String billingcycle, String dateadded) {
        this.id = id;
        this.utility = utility;
        this.value = value;
        this.billingcycle = billingcycle;
        this.dateadded = dateadded;
    }
    
    public String getID() {
        return id;
    }
    
    public String getUtility() {
        return utility;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getBillingCycle() {
        return billingcycle;
    }
    
    public String getDateAdded() {
        return dateadded;
    }
}
