package crud.model; 
 
import java.io.Serializable; 
 
/** 
 * 	State name and abbreviation. 
 */ 
public class State implements Serializable 
{ 
    static final long serialVersionUID = 192412677248171422L; 
 
    private String name; 
    private String id; 
     
    public State( String name, String id ) 
    { 
        this.name = name; 
        this.id = id; 
    } 
     
    public String getName() { return this.name; } 
    public String getId() { return this.id; } 
} 