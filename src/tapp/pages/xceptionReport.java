package tapp.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ExceptionReporter;

public class xceptionReport implements ExceptionReporter
{
    @Property
    private Throwable exception;

    public void reportException(Throwable exception)
    {
        this.exception = exception;
    }
}