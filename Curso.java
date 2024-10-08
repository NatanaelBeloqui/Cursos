public class Curso {
    Integer id;
    String nome;
    Integer cargaHoraria;
    Professor professor;

    public Curso(
        Integer id,
        String nome,
        Integer cargaHoraria,
        Professor professor
    ) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }
}
