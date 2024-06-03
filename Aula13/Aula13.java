package Aula13;

import java.util.Scanner;

public class Aula13 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int pesoTotal;
        int alunos, totalNotas;
        double nota;

        System.out.println("Quantos alunos deseja calcular?");
        alunos = scn.nextInt();
        scn.nextLine();
        String[] nomeAlunos = new String[alunos]; // Vetor de nomes de alunos
        
        System.out.println("Quantas notas você irá inserir?");
        totalNotas = scn.nextInt();
        scn.nextLine();
        double[][] notas = new double[alunos][totalNotas]; // Vetor para as notas
        double[] media = new double[alunos]; // Vetor da média

        int[] pesos = new int[totalNotas];

        // faz a validação dos pesos
        while (true) {
            pesoTotal = 0;

            for (int i = 0; i < totalNotas; i++) {
                System.out.println("Digite o peso da nota " + (i + 1) + " (exemplo 50 para 50%)");
                pesos[i] = scn.nextInt();
                scn.nextLine();

                pesoTotal += pesos[i];
            }

            if (pesoTotal == 100) {
                break;
            } else {
                System.out.println("O peso total das notas deve ser 100%. Digite novamente.");
            }
        }

        // Coleta das notas e cálculo da média de cada aluno
        for (int y = 0; y < alunos; y++) {
            System.out.println("Qual o nome do aluno " + (y + 1) + "?");
            nomeAlunos[y] = scn.nextLine();
            boolean notasCorretas = false;

            while (!notasCorretas) {
                media[y] = 0; // Inicializa a média do aluno
                double somaPesos = 0;

                for (int i = 0; i < totalNotas; i++) {
                    System.out.println("Qual é a nota " + (i + 1) + " para o aluno " + nomeAlunos[y] + "?");
                    nota = scn.nextDouble();
                    scn.nextLine();

                    if (nota > 10.0) {
                        System.out.println("A nota não pode ser maior que 10. Insira novamente.");
                        i--; // Repetir esta nota
                        continue;
                    }

                    // Armazena a nota e calcula a média ponderada do aluno
                    notas[y][i] = nota;
                    media[y] += nota * pesos[i];
                    somaPesos += pesos[i];
                }

                media[y] /= somaPesos;

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

        // Usar boolean e if-else para mostrar as notas de um aluno individualmente
        System.out.println("Deseja ver a nota de um aluno? (s/n)");
        String resposta2 = scn.nextLine();
        if (resposta2.equalsIgnoreCase("s")) {
            System.out.println("Qual aluno você deseja ver? (Digite o número correspondente)");
            for (int i = 0; i < alunos; i++) {
                System.out.println((i + 1) + ". " + nomeAlunos[i]);
            }
            int alunoEscolhido = scn.nextInt();
            scn.nextLine();

            System.out.println("As notas de " + nomeAlunos[alunoEscolhido - 1] + " são:");
            for (int i = 0; i < totalNotas; i++) {
                System.out.println("Nota " + (i + 1) + ": " + notas[alunoEscolhido - 1][i]);
            }
        }

        scn.close();
    }
}