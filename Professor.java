import java.util.ArrayList;

public class Professor {
    Integer id;
    String nome;
    String departamento;

    public Professor(
        Integer id,
        String nome,
        String departamento
    ) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }

    // Método para calcular a carga horária total dos cursos do professor
    public int calcularCargaHorariaTotal(ArrayList<Curso> cursos) {
        int cargaTotal = 0;

        // Percorre todos os cursos e soma a carga horária dos cursos que esse professor ministra
        for (Curso curso : cursos) {
            if (curso.professor.id.equals(this.id)) {
                cargaTotal += curso.cargaHoraria;
            }
        }

        return cargaTotal;
    }
}