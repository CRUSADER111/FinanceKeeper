package Main;

/**
 *
 * @author Alex Morrison
 */
public class Expense {
    private String id;
    private String expense;
    private String value;
    private String dateadded;
    
    public Expense(String id, String expense, String value, String dateadded) {
        this.id = id;
        this.expense = expense;
        this.value = value;
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
    
    public String getDateAdded() {
        return dateadded;
    }
}
