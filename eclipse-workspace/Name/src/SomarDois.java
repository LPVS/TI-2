import java.util.*;

class SomarDois {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main (String args[]) {
		int n1,n2,soma;
		
		System.out.println("Digite o primeiro: ");
		n1 = sc.nextInt();
		
		System.out.println("Próximo número: ");
		n2 = sc.nextInt();
		
		soma = n1 + n2;
		
		System.out.println("Soma: " + soma);
	}

	public static Scanner getSc() {
		return sc;
	}

	public static void setSc(Scanner sc) {
		SomarDois.sc = sc;
	}

}
