package com.appspot.cloudserviceapi.eo.test;

import java.util.ArrayList;

import javax.persistence.Entity;

//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
//import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * Source: http://stackoverflow.com/questions/1413190/hibernate-mapping-package
 */
public class MyAnnotationSessionFactoryBean /*extends AnnotationSessionFactoryBean*/ {

//	@Override
//	protected SessionFactory buildSessionFactory() throws Exception {
//	  ArrayList<Class> classes = new ArrayList<Class>();
//
//	  // the following will detect all classes that are annotated as @Entity
//	  ClassPathScanningCandidateComponentProvider scanner =
//	    new ClassPathScanningCandidateComponentProvider(false);
//	  scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
//
//	  // only register classes within "com.fooPackage" package
////	  for (BeanDefinition bd : scanner.findCandidateComponents("tapp.model.eo")) {
//	  for (BeanDefinition bd : scanner.findCandidateComponents("com.appspot.cloudserviceapi.eo.test")) {
//	    String name = bd.getBeanClassName();
//	    try {
//	      classes.add(Class.forName(name));
//	    } catch (Exception E) {
//	      // TODO: handle exception - couldn't load class in question
//	    }
//	  } // for
//
//	  // register detected classes with AnnotationSessionFactoryBean
//	  setAnnotatedClasses(classes.toArray(new Class[classes.size()]));
//	  return super.buildSessionFactory();
//	}
}
