import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import com.vamika.bms.service.ApplicationStartupService;


public class Test {

	public static void main(String[] args) {
		final List<Class<?>> processorCandidates = ReflectionHelper.findClassesImpmenenting(ApplicationStartupService.class, ApplicationStartupService.class.getPackage());
		System.out.println("Candidate Class Size: " + processorCandidates.size());
		for(Class classObj: processorCandidates) {
			if(!Modifier.isAbstract( classObj.getModifiers())) {
				try {
					System.out.println("Class Name: " + classObj.getName());
					Object obj = classObj.newInstance();
					for(Method method: ApplicationStartupService.class.getMethods()) {
						System.out.println("Method Name: " + method.getName());
						method.invoke(obj, null);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("DatabaseInitializer init method called: Total User Size: "/* + userService.getAllUsers().size()*/);
		
//		AnimalInterface[]test = Animal.values();
//		print(test);
	}
	
	public static void print(AnimalInterface[]animalInterface) {
		for(int i=0; i < animalInterface.length; i++) {
			System.out.println(i + ": " + animalInterface[i]);
		}
	}

}


interface AnimalInterface {
	
}

enum Animal implements AnimalInterface {
	LION, TIGER, GOAT
}

enum Bird implements AnimalInterface {
	PIGON, PARROT, OWL
}