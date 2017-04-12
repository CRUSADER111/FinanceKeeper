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
    private String expense;
    private String value;
    private String billingcycle;
    private String dateadded;
    
    public Utility(String id, String expense, String value, String billingcycle, String dateadded) {
        this.id = id;
        this.expense = expense;
        this.value = value;
        this.billingcycle = billingcycle;
        this.dateadded = dateadded;
    }
    
    public String getID() {
        return id;
    }
    
    public String getExpense() {
        return expense;
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
