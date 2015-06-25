package crud.model;
import java.util.ArrayList;
import java.io.Serializable;
public class StatesList extends ArrayList implements Serializable
{
    private static final long serialVersionUID = -718976040813408212L;
 
    public StatesList()
    {
        super();
 
        add( new State("Alabama", "AL"));
        add( new State("Alaska", "AK"));
        add( new State("Arizona", "AZ"));
        add( new State("Arkansas", "AR"));
        add( new State("Wyoming", "WY"));
    }
}
