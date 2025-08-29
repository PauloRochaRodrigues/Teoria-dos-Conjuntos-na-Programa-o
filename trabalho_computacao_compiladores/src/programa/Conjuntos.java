package programa;
import java.util.List;
import java.util.ArrayList;

public class Conjuntos {
	private String nome;
	public List<String> set;
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String value) {
		this.nome = value;
	}
	public Conjuntos(String nome) {
		this.nome = nome;
		set = new ArrayList<String>();
	}
	
	public void addElemento(String elemento) {
		for (String simbolo : this.set) {
			if(simbolo.equals(elemento)) {
				return;
			}
		}
		set.add(elemento);
	}
	public void exibeElemento() {
		System.out.print(this.nome + " = {");
		int i = 0;
		for (String simbolo : this.set) {
			i++;
			System.out.print(simbolo);
			if (i != this.set.size()) {System.out.print(", ");}
		}
		System.out.println("}");
	}
	
	/*OPERAÇÕES BÁSICAS*/
	public void União(Conjuntos B) {
		Conjuntos uniao = new Conjuntos(this.nome + "u" + B.getNome());
		for (String eA : this.set) {
			uniao.addElemento(eA);
		}
		for (String eB : B.set) {
			uniao.addElemento(eB);
		}
		uniao.exibeElemento();
	}
	public void Intersecao(Conjuntos B) {
		Conjuntos interseccao = new Conjuntos(this.nome + "n" + B.getNome());
		for (String eA : this.set) {
			for (String eB : B.set) {
				if(eA.equals(eB)) {
					interseccao.addElemento(eA);
				}
			}
		}
		interseccao.exibeElemento();
	}
	public void Diferenca(Conjuntos B) {
		Conjuntos diferenca = new Conjuntos(this.nome + "-" + B.getNome());
		for (String eA : this.set) {
			int ver = 0;
			for (String eB : B.set) {
				if (eA.equals(eB)) {
					ver++;
				}
			}
			if (ver == 0) {
				diferenca.addElemento(eA);
			}
		}
		diferenca.exibeElemento();
	}
	
	/*VERIFICAÇÃO DE IGUALDADE, SUBCONJUNTO E DESIGUALDADE*/
	public void Verificador(Conjuntos B) {
		if(Igualdade(B.set) && (set.size() == B.set.size())) {
			System.out.println("Os conjuntos " + this.nome + " e " + B.getNome() + " são iguais");
		}else if(Igualdade(B.set) || B.Igualdade(this.set)) {
			System.out.println(Igualdade(B.set) ? this.nome + " contém " + B.getNome() : B.getNome() + " contém " + this.nome);
		}else {
			System.out.println("Os conjuntos " + this.nome + " e " + B.getNome() + " são diferentes");
		}
	}
	private boolean Igualdade(List<String> conjunto) {
		int ver = 0;
		for (String eB : conjunto) {
			for (String eA : this.set) {
				if(eB.equals(eA)) {ver++;}
				}
			}
		if(ver == conjunto.size()) {return true;}
		return false;
	}
	
	/*PRODUTO CARTESIANO*/
	public void Cartesiano(Conjuntos B) {
		prodCart(B).exibeElemento();
	}
	private Conjuntos prodCart(Conjuntos B) {
		Conjuntos auxiliar = new Conjuntos(this.nome + "x" + B.nome);
		
		for(String eA : this.set) {
			for(String eB : B.set) {
				auxiliar.addElemento("(" + eA + "," + eB +")");
			}
		}
		return auxiliar;
	}
	
	/*VERIFICADOR DE N-UPLAS*/
	public void nUplas(Conjuntos B, Conjuntos n_upla) {
		prodCart(B).Verificador(n_upla);
	}
}