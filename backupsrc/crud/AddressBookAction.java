package crud; 
  
import crud.model.Address; 
import com.opensymphony.xwork2.ActionSupport; 
  
  
/** 
 * Address form handler.
 * 
 * http://www.rkcole.com/articles/struts/crudTutorial/step3.html
 */ 
public class AddressBookAction extends ActionSupport  
{ 
    static final long serialVersionUID = -726287915382955298L; 
 
    /**    adddress information is stored in this object. 
      *    @see crud.model.Address 
      */ 
    Address address; 
 
    public String execute() throws Exception  
    { 
        setMessage(getText(MESSAGE)); 
        return SUCCESS; 
    } 
  
    /**   Get the address entry object. 
     *  @see crud.model.Address 
     */ 
    public Address getAddress() 
    { 
        return this.address; 
    } 
  
    /**   Set the address entry object. 
     *  @see crud.model.Address 
     */ 
    public void setAddress( Address ae ) 
    { 
        this.address = ae; 
    } 
  
    /**    reset (clear) the current Address. 
     */ 
    public String reset() throws Exception  
    { 
        super.clearErrorsAndMessages(); 
 
        this.address = new Address(); 
 
        setMessage( getText( MESSAGE ) ); 
        return SUCCESS; 
    } 
  
    /**   update (save) the current Address.  
     *    No-Operation in this example. 
     */ 
    public String update() throws Exception  
    { 
        setMessage( getText(ADDRESS_SAVED) ); 
        return SUCCESS; 
    } 
 
  
    /**    Default 'ready' message. 
     */ 
    public static final String MESSAGE = "addressbook.default.message"; 
 
  
    /**    Operation 'successful' message. 
     */ 
    public static final String ADDRESS_SAVED = "address.updated.message"; 
 
  
    /** 
     * Field for Message property. 
     */ 
    private String message; 
 
  
    /**    Accessor for  the Message property. 
     *    @return Message property 
     */ 
    public String getMessage() { return message; } 
 
  
    /**    Accessor for the Message property. 
     *    @param message Text to display on the AddressForm page. 
     */ 
    public void setMessage(String s) { this.message = s; } 
}