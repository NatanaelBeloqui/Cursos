import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    // Criação das listas professores, cursos e alunos
    static ArrayList<Professor> professores = new ArrayList<Professor>();
    static ArrayList<Curso> cursos = new ArrayList<Curso>();
    static ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    
    // Abertura da MAIN
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer opcao = 0;

        while (opcao != 7) {
            System.out.println("[1] Cadastrar Professor");
            System.out.println("[2] Cadastrar Curso");
            System.out.println("[3] Cadastrar Aluno");
            System.out.println("[4] Listar Professores");
            System.out.println("[5] Listar Cursos");
            System.out.println("[6] Listar Alunos");
            System.out.println("[7] Encerrar");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao < 1 || opcao > 7) {
                System.out.println("\nOpção inválida. Selecione uma opção válida.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do professor: ");
                    Integer idProfessor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do professor: ");
                    String nomeProfessor = scanner.nextLine();
                    System.out.print("Digite o departamento do professor: ");
                    String departamento = scanner.nextLine();

                    Professor novoProfessor = new Professor(idProfessor, nomeProfessor, departamento);
                    professores.add(novoProfessor);
                    System.out.println("Professor cadastrado com sucesso!\n");
                    break;

                case 2:
                    if (professores.isEmpty()) {
                        System.out.println("Não há professores cadastrados. Cadastre um professor antes de prosseguir.");
                        break;
                    }

                    System.out.print("Digite o ID do curso: ");
                    Integer idCurso = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do curso: ");
                    String nomeCurso = scanner.nextLine();
                    System.out.print("Digite a carga horária do curso: ");
                    Integer cargaHoraria = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Professores cadastrados: ");
                    for (Professor prof : professores) {
                        System.out.println("ID: " + prof.id + ", Nome: " + prof.nome);
                    }

                    System.out.print("Digite o ID do professor responsável por este curso: ");
                    Integer idProfCurso = scanner.nextInt();
                    scanner.nextLine();

                    Professor idProfEscolhido = null;
                    for (Professor prof : professores) {
                        if (prof.id.equals(idProfCurso)) {
                            idProfEscolhido = prof;
                            break;
                        }
                    }

                    if (idProfEscolhido != null) {
                        Curso novoCurso = new Curso(idCurso, nomeCurso, cargaHoraria, idProfEscolhido);
                        cursos.add(novoCurso);
                        System.out.println("Curso cadastrado com sucesso!\n");
                    } else {
                        System.out.println("Professor com ID " + idProfCurso + " não encontrado.");
                    }
                    break;

                case 3:
                    if (cursos.isEmpty()) {
                        System.out.println("Não há cursos cadastrados. Cadastre um curso antes de prosseguir.");
                        break;
                    }
                
                    System.out.print("Digite o ID do aluno: ");
                    Integer idAluno = scanner.nextInt();
                    scanner.nextLine();
                
                    // Verifica se o ID do aluno já existe na lista
                    boolean alunoExiste = false;
                    for (Aluno a : alunos) {
                        if (a.id.equals(idAluno)) {
                            alunoExiste = true;
                            break;
                        }
                    }
                
                    if (alunoExiste) {
                        System.out.println("Aluno com ID " + idAluno + " já cadastrado.");
                        break;
                    }
                
                    System.out.print("Digite o nome do aluno: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("Digite a data de nascimento do aluno (dd/mm/aaaa): ");
                    String dataNasc = scanner.nextLine();
                    System.out.print("Digite o CPF do aluno (00000000000): ");
                    String CPF = scanner.nextLine();
                
                    System.out.println("Cursos disponíveis:");
                    for (Curso cur : cursos) {
                        System.out.println("ID: " + cur.id + ", Nome: " + cur.nome);
                    }
                
                    System.out.print("Digite o curso do aluno, pelo ID: ");
                    Integer idCursoEscolhido = scanner.nextInt();
                    Curso cursoEscolhido = null;
                    for (Curso c : cursos) {
                        if (c.id.equals(idCursoEscolhido)) {
                            cursoEscolhido = c;
                            break; // Sai do loop ao encontrar o curso
                        }
                    }
                
                    if (cursoEscolhido != null) {
                        Aluno novoAluno = new Aluno(idAluno, nomeAluno, dataNasc, CPF, cursoEscolhido);
                        alunos.add(novoAluno);
                        System.out.println("Aluno cadastrado com sucesso!");
                    } else {
                        System.out.println("Curso não encontrado. O cadastro do aluno foi cancelado.");
                    }
                    break;

                case 4:
                    System.out.println("Professores cadastrados:");
                    for (Professor prof : professores) {
                        // Calcula a carga horária total do professor
                        int cargaHorariaTotal = prof.calcularCargaHorariaTotal(cursos);
                        System.out.println("ID: " + prof.id + ", Nome: " + prof.nome + ", Departamento: " + prof.departamento + ", Carga Horária Total: " + cargaHorariaTotal);
                    }
                    break;

                case 5:
                    System.out.println("Cursos cadastrados:");
                    for (Curso curso : cursos) {
                        System.out.println("ID: " + curso.id + ", Nome: " + curso.nome + ", Carga Horária: " + curso.cargaHoraria + ", Professor: " + curso.professor.nome);
                    }
                    break;

                case 6:
                System.out.println("Alunos cadastrados:");
                    for (Aluno aluno : alunos) {
                        int idade = aluno.calcularIdade();
                        System.out.println("ID: " + aluno.id + ", Nome: " + aluno.nome + ", Idade: " + idade + ", Data de Nascimento: " + aluno.dataNasc + ", CPF: " + aluno.CPF + ", Curso: " + aluno.curso.nome);
                    }
                    break;

                case 7:
                    System.out.println("Programa encerrado.");
                    break;
            }
        }
        scanner.close();
    }
}