package tapp.model.security;

public class MyObject { 
	   private Integer id; 
	   private String name; 

	   public MyObject(Integer id, String name){ 
	      this.id = id; 
	      this.setName(name); 
	   } 

	   public Integer getId(){ 
	      return id; 
	   } 

	   public void setId(Integer id){ 
	      this.id = id; 
	   } 

	   public void setName(String name) { 
	      this.name = name; 
	   } 

	   public String getName() { 
	      return name; 
	   } 

	   @Override 
	   public int hashCode(){ 
	      return id.hashCode(); 
	   } 

	   @Override 
	   public boolean equals(Object o){ 
	      if(o == null || !(o instanceof MyObject)){ 
	         return false; 
	      } 

	      MyObject myObject = (MyObject)o; 
	      return id.equals(myObject.getId()); 
	   } 

	   @Override 
	   public String toString(){ 
	      return name + "(" + id + ")"; 
	   } 
	} 
