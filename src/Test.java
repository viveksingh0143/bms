
public class Test {

	public static void main(String[] args) {
		AnimalInterface[]test = Animal.values();
		print(test);
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