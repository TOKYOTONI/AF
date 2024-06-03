package Aula12;

import java.util.Scanner;

public class Aula12 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int pesoTotal;
        int alunos, quantidadeNotas;
        double nota;

        System.out.println("Quantos alunos deseja calcular?");
        alunos = scn.nextInt();
        scn.nextLine();
        String[] nomeAlunos = new String[alunos];
        double[] media = new double[alunos];

        System.out.println("Quantas notas você irá inserir?");
        quantidadeNotas = scn.nextInt();
        scn.nextLine();

        int[] pesos = new int[quantidadeNotas];

        // Coleta e validação dos pesos
        while (true) {
            pesoTotal = 0;

            for (int i = 0; i < quantidadeNotas; i++) {
                System.out.println("Digite o peso da nota " + (i + 1) + " (exemplo 50 para 50%)");
                pesos[i] = scn.nextInt();
                scn.nextLine();

                    pesoTotal += pesos[i];
                    if (pesoTotal > 100) {
                        System.out.println("O peso das notas ultrapassa 100%, digite novamente.");
                        break;
                    }
            }
                    if (pesoTotal <= 100) {
                        break;
                    }
        }

        // Coleta das notas e cálculo da média de cada aluno
        for (int y = 0; y < alunos; y++) {
            System.out.println("Qual o nome do aluno " + (y + 1) + "?");
            nomeAlunos[y] = scn.nextLine();
            boolean notasCorretas = false;

            while (!notasCorretas) {
                media[y] = 0; // Inicializa a média do aluno
                pesoTotal = 0; // Reinicializa pesoTotal para cada aluno
                
                for (int i = 0; i < quantidadeNotas; i++) {
                    System.out.println("Qual é a nota " + (i + 1) + " para o aluno " + nomeAlunos[y] + "?");
                    nota = scn.nextDouble();
                    scn.nextLine();

                    if (nota > 10.0) {
                        System.out.println("A nota não pode ser maior que 10. Insira novamente.");
                        i--; // Repetir esta nota
                        continue;
                    }

                    media[y] += nota * pesos[i];
                    pesoTotal += pesos[i];
                }

                // Calcular a média do aluno
                media[y] /= 100.0;

                System.out.println("A média das notas para o aluno " + nomeAlunos[y] + " é " + media[y] + ". Você quer redigitar as notas para o aluno atual? (s/n)");
                String resposta = scn.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    media[y] = 0;
                } else {
                    notasCorretas = true;
                }
            }

            // Determinação do status do aluno
            if (media[y] <= 2.0) {
                System.out.println("Aluno está reprovado.");
            } else if (media[y] >= 2.1 && media[y] <= 4.9) {
                System.out.println("O aluno precisa fazer a substitutiva.");
            } else {
                System.out.println("O aluno está aprovado.");
            }
        }

        // Cálculo da média da turma
        double mediaTurma = 0.0;
        for (int i = 0; i < alunos; i++) {
            mediaTurma += media[i];
        }
        mediaTurma /= alunos;
        System.out.println("A média da turma é: " + mediaTurma);

        scn.close();
    }
}