package tapp.pages;

public class Fail
{
    void onActionFromFail()
    {
        throw new RuntimeException("Failure inside action event handler.");
    }
}