package crud.model;
 
/**    The name of a person and their address.
 */
public class Address
{
    String address;
    String name;
    String city;
    String state;
    String zipcode;

    public void setName(String s)    { this.name = s; }
    public void setAddress(String s) { this.address = s; }
    public void setCity(String s)    { this.city = s; }
    public void setState(String s)   { this.state = s; }
    public void setZipcode(String s) { this.zipcode = s; }

    public String getName()    { return this.name; }
    public String getAddress() { return this.address; }
    public String getCity()    { return this.city; }
    public String getState()   { return this.state; }
    public String getZipcode() { return this.zipcode; }

    public String toString() 
    {
        return this.name + "," + this.address + "," + this.city + "," 
		    + this.state + "," + this.zipcode;
    }
}