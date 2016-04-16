package Arvores;

public class ArvoreRN {

    No raiz;                //onde fica armazenado a arvore
    String print = "";      //para iniciar a impressao da arvore

    class No {                //classe para cada no da arvore

        No esquerda;          //os nos da arvore, seu valor e sua cor
        No direita;
        No pai;
        int valor;
        boolean cor;

        public No(int valor, No pai, boolean cor) {    //para quando criar o no ela ter pai, um valor e uma cor
            this.valor = valor;
            this.pai = pai;
            this.cor = cor; //vermelho == true && preto == false 
        }
    }

    public void inserir(int valor) {            //metodo para inserir os nos
        int x = 0;                              //x mostra se ja colocou ou nao o no na arvore
        if (raiz == null) {                     //se a raiz tiver vazia, o no inserido sera a raiz
            raiz = new No(valor, null, false);   //como a raiz tem que ser negra, insere como cor false
        } else {                                //caso contrario
            No aux = raiz;                      //armazena-se a raiz em uma variavel auxiliar
            while (x == 0) {                    //checa se o no ja foi inserido
                if (valor > aux.valor) {     //se o no for maior que o ultimo elemento inserido, ele olha o elemento da direita
                    if (aux.direita == null) {  //se o elemento da direita nao tiver ninguem
                        No n = new No(valor, aux, true);  //para evitar desbalanceamentos, insere nos vermelhos
                        aux.direita = n;           //insere o elemento na direita
                        quemBalancear(n);          //balanceia o no
                        x = 1;                     //avisa que o no foi inserido
                    } else {                      //se o da direita nao estiver vazio, ele manda ele checar tudo isso de novo
                        aux = aux.direita;        //so que levando como referencia o no da direita do no anterior
                    }
                } else {                   //se o no for menor que o ultimo elemento inserido, ele olha o elemento da esquerda
                    if (aux.esquerda == null) {      //se o elemento da esquerda nao tiver ninguem
                        No n = new No(valor, aux, true);   //para evitar desbalanceamentos, insere nos vermelhos
                        aux.esquerda = n;            //insere o elemento na esquerda
                        quemBalancear(n);            //balanceia o no
                        x = 1;                       //avisa que o no foi inserido
                    } else {                         //se o da esquerda nao estiver vazio, ele manda ele checar tudo isso de novo
                        aux = aux.esquerda;          //so que levando como referencia o no da esquerda do no anterior
                    }
                }
            }
        }
    }

    public void quemBalancear(No n) {     //no para saber quem balancear
        if (n.pai != null) {             //o no inserido nunca estara balanceado, seu avo ou bisavo que estarao e aki
            recoloracaoJunior(n);
            if (n.pai.pai != null) {     //checa se eles estao nulos ou nao
                //e aki para ver se eles estao desbalanceados ou nao
                if (alturaEsquerda(n.pai.pai) - alturaDireita(n.pai.pai) > 1 || alturaDireita(n.pai.pai) - alturaEsquerda(n.pai.pai) > 1) {
                    balancearCores(n.pai.pai, n); //balanceia as cores
                    balancear(n.pai.pai);  //caso o avo esteja desbalanceado, o mesmo vai ser balanceado
                }
            }
        }
        if (n.pai != null) {            //o no inserido nunca estara balanceado, seu avo ou bisavo que estarao e aki
            if (n.pai.pai != null) {         //checa se eles estao nulos ou nao
                if (n.pai.pai.pai != null) {
                    //e aki para ver se eles estao desbalanceados ou nao
                    if (alturaEsquerda(n.pai.pai.pai) - alturaDireita(n.pai.pai.pai) > 1 || alturaDireita(n.pai.pai.pai) - alturaEsquerda(n.pai.pai.pai) > 1) {
                        balancearCores(n.pai.pai.pai, n.pai);  //balanceia as cores
                        balancear(n.pai.pai.pai);    //caso o bisavo esteja desbalanceado, o mesmo vai ser balanceado    
                    }
                }
            }
        }
    }

    public void recoloracaoJunior(No n) {
        if (n.esquerda == null && n.esquerda == null) {
            n.cor = false;
        }
        if (n.pai.pai != null && n.pai != null) {
            if (n.pai.pai.cor == false && n.pai.cor == false) {
                n.pai.cor = true;
            }
        }
    }

    public void balancearCores(No n, No filho) {  //metodo para recolorir a arvore
        //regra do balanceamento
        if (true) {//so pra organizar e dizer que a regra do balanceamento ta aki dentro
            //se tiver rotaçao direitaa
            if (alturaEsquerda(filho.pai) > alturaDireita(filho.pai) && alturaEsquerda(filho.pai.esquerda) > alturaDireita(filho.pai.esquerda)) {
                filho.pai.cor = false;  //o pai do no inserido fica negro e o avo rubro (regras do balanceamento)
                n.cor = true;
            } 
            //se tiver rotaçao dupla direita
            else if (alturaEsquerda(filho) > alturaDireita(filho) && alturaEsquerda(filho.esquerda) < alturaDireita(filho.esquerda)) {
                filho.cor = false;  //o no inserido fica negro e o avo rubro (regras do balanceamento)
                n.cor = true;
            }
            //se tiver rotaçao esquerda
            if (alturaEsquerda(filho.pai) < alturaDireita(filho.pai) && alturaEsquerda(filho.pai.direita) < alturaDireita(filho.pai.direita)) {
                filho.pai.cor = false;   //o pai do no inserido fica negro e o avo rubro (regras do balanceamento)
                n.cor = true;
            } 
            //se tiver rotaçao dupla esquerda
            else if (alturaEsquerda(filho.pai) < alturaDireita(filho.pai) && alturaEsquerda(filho.pai.direita) > alturaDireita(filho.pai.direita)) {
                filho.pai.cor = false;     //o no inserido fica negro e o avo rubro (regras do balanceamento)
                n.cor = true;
            }

            balancear(filho);      //balanceia os numeros, ja que as cores ja foram balanceadas
        }
             //regra das folhas
        if (filho.cor) {                 //se o no inserido ficar como folha e for rubro
            filho.cor = false;           //ele vira negro, pois todas as folhas tem que ser negras
            filho.pai.cor = true;        //o pai dele vira rubro para evitar desbalanceamento de numero de nos negros ate a folha
            filho.pai.pai.cor = false;   //o avo dele vira negro para evitar desbalanceamento de nao poder ter dois rubros seguidos
        }
              //regra do tio
        if (filho.pai.pai.esquerda == filho.pai.pai) {     //descobre se o no é filho de esquerda ou de direita
            if (n.direita != null && n.esquerda != null) {     //checa se seu tio eh vermelho, se for o tio e o pai ficam negros
                if (n.direita.cor) {
                    n.esquerda.cor = false;
                    n.direita.cor = false;
                }
            }
        } else {
            if (n.direita != null && n.esquerda != null) {     //checa se seu tio eh vermelho, se for o tio e o pai ficam negros
                if (n.esquerda.cor) {
                    n.esquerda.cor = false;
                    n.direita.cor = false;
                }
            }

        }
        //regra dos nos ate a raiz
        if (alturaEsquerdaCor(filho.pai) > alturaDireitaCor(filho.pai)) { //caso tenha mais nos negros ate a raiz pela esquerda
            if (filho.pai.direita != null) {         //transforma o no da direita do pai do no inserido em negro
                filho.pai.direita.cor = false;       //pois tem mais no negro na ramificaçao esquerda
            }
        } else {                  //caso tenha mais nos negros ate a raiz pela direita
            if (filho.pai.esquerda != null) {         //transforma o no da esquerda do pai do no inserido em negro
                filho.pai.esquerda.cor = false;       //pois tem mais no negro na ramificaçao direita
            }
        }
        //regra da raiz
        raiz.cor = false;                 //depois de balancear tudo, a raiz sempre tem que ser negra
    }

    public int alturaEsquerda(No n) { //metodo para checar a altura esquerda dos nos
        int alturaEsquerda = 0;
        if (n.esquerda != null) {        //se nao tiver alguem na esquerda soma um
            n = n.esquerda;
            alturaEsquerda++;
            if (n.esquerda != null) {     //a partir da primeira esquerda ele comeca a checar tanto na direita
                n = n.esquerda;            //quanto na esquerda pois os dois contaram como altura do no a esquerda
                alturaEsquerda++;          //ele so checa ate o terceiro filho, pois nos metodos que eh chamado
                if (n.esquerda != null || n.direita != null) {    //so eh necessario ate o terceiro, pois provavelmente o
                    alturaEsquerda++;              //proximo seria nulo
                }
            } else if (n.direita != null) {
                n = n.direita;
                alturaEsquerda++;
                if (n.esquerda != null || n.direita != null) {
                    alturaEsquerda++;
                }
            }
        }
        return alturaEsquerda;
    }

    public int alturaDireita(No n) {     //metodo para checar a altura direita dos nos
        int alturaDireita = 0;
        if (n.direita != null) {
            n = n.direita;
            alturaDireita++;
            if (n.esquerda != null) {    //a partir da primeira esquerda ele comeca a checar tanto na direita
                n = n.esquerda;           //ele so checa ate o terceiro filho, pois nos metodos que eh chamado
                alturaDireita++;          //ele so checa ate o terceiro filho, pois nos metodos que eh chamado
                if (n.esquerda != null || n.direita != null) {    //so eh necessario ate o terceiro, pois provavelmente o
                    alturaDireita++;              //proximo seria nulo
                }
            } else if (n.direita != null) {
                n = n.direita;
                alturaDireita++;
                if (n.esquerda != null || n.direita != null) {
                    alturaDireita++;
                }
            }
        }
        return alturaDireita;
    }

    public int alturaEsquerdaCor(No n) {//metodo conta quantos nos negros tem ate o "final" segundo a logica desse codigo (lado esquerdo)
        int alturaEsquerdaCor = 0;
        if (n.esquerda == null) {
            return 0;
        } else {
            if (n.esquerda != null && !n.esquerda.cor) { //como nesse codigo so eh necessario ver a partir do pai do no inserido
                n = n.esquerda;    //checa so uma vez se o no da esquerda eh negro e se for acrescenta em um
                alturaEsquerdaCor++;
                if ((n.esquerda != null && !n.esquerda.cor) || (n.direita != null && !n.direita.cor)) {
                    alturaEsquerdaCor++;
                }
            }     //so eh preciso checar uma vez pois os outros metodos ja garamtem o balanceamtento das cores nos outros
        }     //nos, e porque o n nesse caso é o pai do no inserido
        return alturaEsquerdaCor;
    }

    public int alturaDireitaCor(No n) {//metodo conta quantos nos negros tem ate o "final" segundo a logica desse codigo (lado direito)
        int alturaDireitaCor = 0;
        if (n.direita == null) {
            return 0;
        } else {
            if (n.direita != null && !n.direita.cor) {  //como nesse codigo so eh necessario ver a partir do pai do no inserido
                n = n.direita;           //checa so uma vez se o no da direita eh negro e se for acrescenta em um
                alturaDireitaCor++;
                if ((n.esquerda != null && !n.esquerda.cor) || (n.direita != null && !n.esquerda.cor)) {
                    alturaDireitaCor++;
                }
            }   //so eh preciso checar uma vez pois os outros metodos ja garamtem o balanceamtento das cores nos outros
        }  //nos, e porque o n nesse caso é o pai do no inserido
        return alturaDireitaCor;
    }

    public void balancear(No n) { //diferencia qual tipo de balanceamento o no vai ter de acordo com as regras de balanceamento
        if (alturaEsquerda(n) > alturaDireita(n) && alturaEsquerda(n.esquerda) > alturaDireita(n.esquerda)) {
            rotacaoDireita(n);
        } else if (alturaEsquerda(n) < alturaDireita(n) && alturaEsquerda(n.direita) < alturaDireita(n.direita)) {
            rotacaoEsquerda(n);
        } else if (alturaEsquerda(n) > alturaDireita(n) && alturaEsquerda(n.esquerda) < alturaDireita(n.esquerda)) {
            rotacaoDuplaDireita(n);
        } else if (alturaEsquerda(n) < alturaDireita(n) && alturaEsquerda(n.direita) > alturaDireita(n.direita)) {
            rotacaoDuplaEsquerda(n);
        }
    }

    public void rotacaoEsquerda(No n) {       //faz a rotaçao esquerda do no
        if (n.pai.esquerda == n) {           //se o no for o filho da esquerda
            No aux = n.pai.esquerda;
            No aux2 = n.pai.esquerda.direita.esquerda;
            n.pai.esquerda = n.pai.esquerda.direita;
            n.pai.esquerda.esquerda = aux;
            n.pai.esquerda.esquerda.direita = aux2;
        } else {                              //se o no for o filho da direita
            No aux = n.pai.direita;
            No aux2 = n.pai.direita.direita.esquerda;
            n.pai.direita = n.pai.direita.direita;
            n.pai.direita.esquerda = aux;
            n.pai.direita.esquerda.direita = aux2;
        }
    }

    public void rotacaoDireita(No n) {    //faz a rotaçao direita do no
        if (n.pai.esquerda == n) {        //se o no for o filho da esquerda
            No aux = n.pai.esquerda;
            No aux2 = n.pai.esquerda.esquerda.direita;
            n.pai.esquerda = n.pai.esquerda.esquerda;
            n.pai.esquerda.direita = aux;
            n.pai.esquerda.direita.esquerda = aux2;
        } else {                          //se o no for o filho da direita
            No aux = n.pai.direita;
            No aux2 = n.pai.direita.esquerda.direita;
            n.pai.direita = n.pai.direita.esquerda;
            n.pai.direita.direita = aux;
            n.pai.direita.direita.esquerda = aux2;
        }
    }

    public void rotacaoDuplaEsquerda(No n) {        //faz a rotaçao dupla esquerda do no
        rotacaoDireita(n.direita);                  //faz uma rotaçao de direita com o no da direita(o bisavo)
        rotacaoEsquerda(n);                         //e depois uma rotaçao de esquerda com o avo
    }

    public void rotacaoDuplaDireita(No n) {         //faz a rotaçao dupla direita do no
        rotacaoEsquerda(n.esquerda);                //faz uma rotaçao de direita com o no da esquerda(o bisavo)
        rotacaoDireita(n);                          //e depois uma rotaçao de esquerda com o avo
    }

    public String printar() {       //metodo para printar a arvore
        if (raiz == null) {
            return "";                  //se nao tiver arvore, nao printa nada
        } else {
            No n = this.raiz;                //n recebe raiz
            print = print + Integer.toString(n.valor) + " - " + n.cor + " ";   //e printa o valor dela
            if (n.esquerda != null) {         //se tiver algo na esquerda
                print = print + "(" + printaraux(n.esquerda) + " - " + n.esquerda.cor + ")";  //printa todos os valores de la dentro do primeiro parenteses
            }
            if (n.direita != null) {          //se tiver algo na direita
                print = print + "(" + printaraux(n.direita) + " - " + n.direita.cor + ")";   //printa todos os valores de la dentro do segundo parenteses
            }
            String pr = print;        // guarda tudo o que salvo durante esse loop numa variavel auxiliar
            print = " ";              //zera a variavel print para poder imprimir novas arvores
            return pr;                //e retorna a string que contem a arvore
        }
    }

    private String printaraux(No n) {             //metodo para auxiliar o print da arvore
        print = Integer.toString(n.valor) + " ";   //transforma o valor do no em uma string
        if (n.esquerda != null) {               //se tiver algo na esquerda
            print = print + "( " + printaraux(n.esquerda) + " - " + n.esquerda.cor + ")";  //printa todos os valores de la dentro do primeiro parenteses
        }
        if (n.direita != null) {      //se tiver algo na direita
            print = print + "(" + printaraux(n.direita) + " - " + n.direita.cor + ")"; //printa todos os valores de la dentro do segundo parenteses
        }
        String pr = print;        // guarda tudo o que salvo durante esse loop numa variavel auxiliar
        print = " ";              //zera a variavel print para poder imprimir novas arvores
        return pr;                //e retorna a string que contem a arvore
    }
    /* a ideia desses dois metodos e printar a raiz e dois parenteses com suas cores exemplo:8 - false(4 - flase)(10 - false),
     fora do parentese esta a raiz, o primeiro parentese é o no da esquerda e o segundo o no da direita, caso os nos da direita 
     ou esquerda tenham filhos vao seguir a mesma logica, so que com a cor da "raiz da vez" so mostra depois de mostrar todos
     os filhos exceto da raiz original exemplo:8 - false(4(3 - false)(5 - false) - true)(10(9 - false)(11 - false) - true),
     3 é filho da esquerda de quatro e 5 é filho da direita de 4. */
}
