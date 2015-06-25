package com.appspot.cloudserviceapi.service.tapestry;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresLogin {

}
