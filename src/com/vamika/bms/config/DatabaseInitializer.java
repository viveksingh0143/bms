package com.vamika.bms.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.vamika.bms.helper.ReflectionHelper;
import com.vamika.bms.service.ApplicationStartupService;
import com.vamika.bms.service.UserService;
import com.vamika.bms.view.FullPermission;

public class DatabaseInitializer implements BeanFactoryAware {
	
	private BeanFactory beanFactory;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
    public void init(){
		List<String>permissions = new ArrayList<String>();
		final List<Class<?>> processorCandidates = ReflectionHelper.findClassesImpmenenting(ApplicationStartupService.class, ApplicationStartupService.class.getPackage());
		for(Class classObj: processorCandidates) {
			if(Modifier.isAbstract( classObj.getModifiers())) {
				try {
					for(Class interfaceObj: classObj.getInterfaces()) {
						if(!interfaceObj.equals(ApplicationStartupService.class)) {
							Object obj = getBeanFactory().getBean(interfaceObj);
							if(obj != null) {
								try {
									Field field = interfaceObj.getField("PERMISSIONS"); //Note, this can throw an exception if the field doesn't exist.
								    List<String> classPermissions = (List<String>)field.get(obj);
								    permissions.addAll(classPermissions);
								} catch(Exception e) {}
								
							    for(Method method: ApplicationStartupService.class.getMethods()) {
									try {
										method.invoke(obj, null);
									} catch(Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				} catch(Exception e) {
					System.out.println("Error Found");
					e.printStackTrace();
				}
			}
		}

		List<FullPermission>fullPermissions = new ArrayList<FullPermission>();
		for(String permission: permissions) {
			FullPermission fullPermission = userService.loadPermission(permission);
			if(fullPermission == null) {
				fullPermission = new FullPermission();
				fullPermission.setName(permission);
				fullPermissions.add(fullPermission);
				userService.savePermission(fullPermission);
			}
		}
    }
     
    public DatabaseInitializer(){
    }
     
    @PreDestroy
    public void destory(){
        System.out.println("DatabaseInitializer destroy method called");
    }

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
}