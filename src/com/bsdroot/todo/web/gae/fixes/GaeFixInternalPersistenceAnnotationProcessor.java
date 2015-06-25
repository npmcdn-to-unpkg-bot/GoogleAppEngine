package com.bsdroot.todo.web.gae.fixes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * GAE: Spring JPA: Needed to get around the "java.lang.NoClassDefFoundError: 
 * javax/naming/NamingException" blacklisting problem.
 * 
 * Just register this dummy impl as bean with id: org.springframework.context.annotation.internalPersistenceAnnotationProcessor
 * 
 * @author robert
 * @reference http://code.google.com/p/googleappengine/issues/detail?id=1240
 * 
 */
public class GaeFixInternalPersistenceAnnotationProcessor {

  private static final Logger log = LoggerFactory
      .getLogger(GaeFixInternalPersistenceAnnotationProcessor.class);

  public GaeFixInternalPersistenceAnnotationProcessor() {
    log
        .info("Creating fake internalPersistenceAnnotationProcessor to bypass GAE blacklist problem");

  }

}