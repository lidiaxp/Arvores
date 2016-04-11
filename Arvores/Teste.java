package Arvores;

public class Teste {
    public static void main (String[] args){
        System.out.println("Árvore AVL");
        ArvoreAvl a = new ArvoreAvl();
        a.inserir(8);
        a.inserir(6);
        a.inserir(9);
        a.inserir(2);
        a.inserir(10);
        a.inserir(7);
        a.inserir(11);
        System.out.println(a.printar());
        System.out.println("---------------------------------");
        System.out.println("Árvore Rubro Negra");
        ArvoreRN b = new ArvoreRN();
        b.inserir(2);
        b.inserir(3);
        b.inserir(1);
        b.inserir(4);
        b.inserir(5);
        b.inserir(6);
        System.out.println(b.printar());
        System.out.println("---------------------------------");
    }
}
