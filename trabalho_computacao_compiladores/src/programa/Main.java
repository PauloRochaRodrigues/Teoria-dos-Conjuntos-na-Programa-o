package programa;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static String nome1;
	static String nome2;
	
	static List<Conjuntos> conjuntos = new ArrayList<Conjuntos>();
	public static void main(String[] args) {
		Menu();
	}
	
	/*1-INTERFACE*/
	public static void Opcoes() {
		System.out.println(
				  "\n1-Criar conjunto						2-Exibir conjuntos e seus elementos\n"
				+ "3-União de conjuntos						4-Interseção de conjuntos\n"
				+ "5-Diferença entre conjuntos					6-Verificador de igualdade, subconjunto ou desigualdade\n"
				+ "7-Produto cartesiano						8-Verificador de n-ário\n"
				+ "0-Encerrar programa");
	}
	public static void Menu() {
		System.out.println("Faça sua escolha:");
		Opcoes();
		
		while(true) {
			Integer escolha = scan.nextInt(); scan.nextLine();
			switch(escolha) {
				case 1: CriarConjunto(); break;
				case 2: Exibe(); break;
				case 3: Uniao(); break;
				case 4: Intersecao(); break;
				case 5: Diferenca(); break;
				case 6: Verificador(); break;
				case 7: Cartesiano(); break;
				case 8: Relacao(); break;
				case 0: System.out.println("Programa encerrado"); return;
				default: System.out.println("Não existe esta escolha"); break;
			}
			Opcoes();
		}
	}
	
	/*2.1-FUNÇÕES*/
	public static void CriarConjunto() {
		System.out.print("Digite o nome do conjunto (apenas uma letra): ");
		String nome = String.valueOf(scan.nextLine().charAt(0)).toUpperCase();
		
		for (Conjuntos conjunto : conjuntos) {
			if(nome.equals(conjunto.getNome())) {
				System.out.println("Esse conjunto já existe, retornando...");
				return;
			}
		}
		
		Conjuntos teste = new Conjuntos(nome);
		System.out.print("Quantos elementos deseja adcionar no conjunto\n(se for um elemento repetido o programa irá considerar como mais um): ");
		Integer contador = scan.nextInt(); scan.nextLine();
		
		for (int i = 0; i < contador; i++) {
			System.out.print("Digite o " + (1 + i) + "o elemento: ");
			teste.addElemento(scan.nextLine());
		}
		conjuntos.add(teste);
	}
	public static void Exibe() {
		for (Conjuntos conjunto : conjuntos) {
			conjunto.exibeElemento();
		}
	}
	
	/*2.2-FUNÇÕES DE CONJUNTOS*/
	public static void Uniao() {
		setNomes();
		
		Conjuntos A = getConjunto(nome1);
		Conjuntos B = getConjunto(nome2);

		if (nullVerify(A, B)) {return;}
		
		A.União(B);
	}
	public static void Intersecao() {
		setNomes();
		
		Conjuntos A = getConjunto(nome1);
		Conjuntos B = getConjunto(nome2);

		if (nullVerify(A, B)) {return;}
		
		A.Intersecao(B);
	}
	public static void Diferenca() {
		setNomes();
		
		Conjuntos A = getConjunto(nome1);
		Conjuntos B = getConjunto(nome2);

		if (nullVerify(A, B)) {return;}
		
		A.Diferenca(B);
	}
	public static void Verificador() {
		setNomes();
		
		Conjuntos A = getConjunto(nome1);
		Conjuntos B = getConjunto(nome2);

		if (nullVerify(A, B)) {return;}
		
		A.Verificador(B);
	}
	public static void Cartesiano() {
		setNomes();
		
		Conjuntos A = getConjunto(nome1);
		Conjuntos B = getConjunto(nome2);

		if (nullVerify(A, B)) {return;}
		
		A.Cartesiano(B);
	}
	
	/*2.3-N-UPLAS*/
	public static void Relacao() {
		System.out.println("Primeiramente:");
		setNomes();
		Conjuntos A = getConjunto(nome1);
		Conjuntos B = getConjunto(nome2);
		if (nullVerify(A, B)) {return;}
		
		Conjuntos auxiliar = new Conjuntos("[r]");
		
		System.out.println("Deseja verificar um subconjunto ou um par?"
				+ "\n1-Subconjunto			2-Par"
				+ "\n0-Retornar");
		Integer escolha = scan.nextInt(); scan.nextLine();
		
		switch(escolha) {
			case 1:
				System.out.print("Quantos elementos deseja adcionar no conjunto\n(se for um elemento repetido o programa irá considerar como mais um): ");
				Integer contador = scan.nextInt(); scan.nextLine();
				
				for (int i = 0; i < contador; i++) {
					System.out.print("Digite o " + (1 + i) + "o elemento"
							+ "(digite os elementos separados por vírgula e sem espaço como no exemplo: 'x,y', para que não haja erro): ");
					auxiliar.addElemento("("+ scan.nextLine() + ")");
				}
				
				A.Cartesiano(B); auxiliar.exibeElemento();
				A.nUplas(B, auxiliar);
				break;
			case 2:
				System.out.print("Digite a dupla separados por vírgula e sem espaço como no exemplo: 'x,y', para que não haja erro: ");
				auxiliar.addElemento("("+ scan.nextLine() + ")");
				
				A.Cartesiano(B); auxiliar.exibeElemento();
				A.nUplas(B, auxiliar);
				break;
			case 0: System.out.println("Retornando..."); return;
			default: System.out.println("Não existe esta escolha, retornando..."); return;
		}
	}
	
	/*3-TESTE*/
	public static Conjuntos getConjunto(String nome) {
		for (Conjuntos conjunto : conjuntos) {
			if(nome.equalsIgnoreCase(conjunto.getNome())) {
				return conjunto;
			}
		}
		return null;
	}
	public static void setNomes() {
		System.out.print("Digite o nome do primeiro conjunto: ");
		nome1 = scan.nextLine();
		System.out.print("Digite o nome do segundo conjunto: ");
		nome2 = scan.nextLine();
	}
	public static boolean nullVerify(Conjuntos A, Conjuntos B) {
		if ((A == null) || (B == null)) {
			System.out.println((A == null) ? "O conjunto " + nome1 + " não existe" : "O conjunto " + nome2 + " não existe");
			System.out.println("Retornando...");
			return true;
		}
		return false;
	}
}