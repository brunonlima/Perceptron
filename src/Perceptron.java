 
public class Perceptron {
 
    // pesos sinápticos [0] entrada 1, [1] entrada 2,[2] entrada 3 ,[3]BIAS
    private double[] w = new double[4];
 
    // variável responsável pelo somatório(rede).
    private double NET = 0;
 
    // variavél responsável pelo número máximo de iterações
    private final int iteracaoMax = 100;
 
    // variável responsável pela contagem das iterações durante o treinamento
    private int cont = 0;
 
    // declara o vetor da matriz de aprendizado
    private int[][] matrizAprendizado = new int[3][4];
 
    // declara o valor do BIAS
    private static final float BIAS = 1;
    
    // declara o valor da taxa de aprendizagem
    private final float TAXA_APRENDIZAGEM = 0.3f;
    
    // MÉTODO DE RETORNO DO CONTADOR
    public int getCont(){
 
      return this.cont;
 
    }
 // metodo de inicialização inicia o vetor da matriz de aprendizado
  Perceptron() {
 
    // Primeiro valor
    this.matrizAprendizado[0][0] = 20; // entrada 1
    this.matrizAprendizado[0][1] = 10; // entrada 2
    this.matrizAprendizado[0][2] = 10; // entrada 3
    this.matrizAprendizado[0][3] = 0; // valor esperado
 
    // Segundo Valor
    this.matrizAprendizado[1][0] = 40; // entrada 1
    this.matrizAprendizado[1][1] = 30; // entrada 2
    this.matrizAprendizado[1][2] = 20; // entrada 3
    this.matrizAprendizado[1][3] = 1; // valor esperado
 
    // terceiro valor
    this.matrizAprendizado[2][0] = -20; // entrada 1
    this.matrizAprendizado[2][1] = 10; // entrada 2
    this.matrizAprendizado[2][2] = 30; // entrada 3
    this.matrizAprendizado[2][3] = 1; // valor esperado
 
    
 
    // inicialização dos pesos sinápticos
 
    // Peso sináptico para primeira entrada.
    w[0] = 0.2;
    // Peso sináptico para segunda entrada.
    w[1] = 0.2;
    // Peso sináptico para terceira entrada
    w[2]= 0.2;
    // Peso sináptico para o BIAS
    w[3]= 0.2;
    
    
    
 
}
 
  // Método responsávelpelo somatório e a função de ativação.
    public int executar(int x1, int x2, int x3) {
       
        // Somatório (NET)
        NET = (x1 * w[0]) + (x2 * w[1]) + (x3 * w[2])+ (BIAS * w[3]);
 
        // Função de Ativação
        if (NET > 0) {
            return 1;
        }
        return 0;
    }
 
    // Método para treinamento da rede
    public void treinar() {
 
        // variavel utilizada responsável pelo controle de treinamento 
        boolean treinou= true;
        // variável responsável para receber o valor da saída (y)
        int saida;
 
        // laço usado para fazer todas as entradas
        for (int i = 0; i < 3; i++) {
            // A saída recebe o resultado da rede que no caso é 1 ou 0
            saida = executar(matrizAprendizado[i][0], matrizAprendizado[i][1],matrizAprendizado[i][2]);
 
 
            if (saida != matrizAprendizado[i][3]) {
                // Caso a saída seja diferente do valor esperado
 
                // os pesos sinápticos serão corrigidos
                corrigirPeso(i, saida);
                // a variavél responsável pelo controle de treinamento recebe falso
                treinou = false;
 
            }
        }
        // acrescenta uma iteração
        this.cont++;
 
        // teste se houve algum erro durante o treinamento e o número de iterações
        //é menor que o definido
        if((treinou == false) && (this.cont < this.iteracaoMax)) {
            // chamada recursiva do método
            treinar();
 
        }
 
    }    // fim do método para treinamento
 
    // Método para a correção de pesos
    void corrigirPeso(int i, int saida) {
 
        w[0] = w[0] + (TAXA_APRENDIZAGEM * (matrizAprendizado[i][3] - saida) * matrizAprendizado[i][0]);
        w[1] = w[1] + (TAXA_APRENDIZAGEM * (matrizAprendizado[i][3] - saida) * matrizAprendizado[i][1]);
        w[2] = w[2] + (TAXA_APRENDIZAGEM * (matrizAprendizado[i][3] - saida) * matrizAprendizado[i][2]);
        w[3] = w[3] + (TAXA_APRENDIZAGEM * (matrizAprendizado[i][3] - saida) * (BIAS));
        
        
    }
 
    void testar() {
 
    	int i=0;
    	System.out.printf("Pesos definidos após aprendizagem\n");
    	while( i < 4){
    		if(i == 3)
    			System.out.printf("wBias\n--------\n");
    		else
    	System.out.printf("w%d\n--------\n",i+1);
        
    	for(int j =0;j < 4; j++){
        	
        	System.out.printf("%f\t",w[j]);
        }
        System.out.printf("\n");
        i++;
    	}
    	
    	 System.out.printf("\n");
        
        System.out.println(" Teste 01 para E1: " + executar(20, 10,10));
 
        System.out.println(" Teste 02 para E2: " + executar(40, 30,20));
        
        System.out.println(" Teste 03 para E3: " + executar(-20, 10,30));
 
        System.out.println(" Teste 04 para E4: " + executar(30, 10,30));
 
        System.out.println(" Teste 05 para E5: " + executar(30,20,30));
        
        System.out.println(" Teste 06 para E6: " + executar(20, 10,30));
        
        System.out.println(" Teste 07 para E7: " + executar(30,25,30));
 
    }
 
    public static void main(String[] args) {
 
        Perceptron p = new Perceptron();
 
        p.treinar();
 
        System.out.println("Para aprender o neurônio treinou " + p.getCont() + " iterações! \n ");
 
        p.testar();
 
    }
}