import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Aluno {
    Integer id;
    String nome;
    String dataNasc;
    String CPF;
    Curso curso;

    public Aluno(
        Integer id,
        String nome,
        String dataNasc,
        String CPF,
        Curso curso
    ) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.CPF = CPF;
        this.curso = curso;
    }

    public boolean validarCPF(String CPF) {
        // Remove caracteres não numéricos
        CPF = CPF.replaceAll("[^0-9]", "");
        
        if (CPF.length() != 11 || CPF.equals("00000000000") || CPF.equals("11111111111") || 
            CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") ||
            CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }

        // Cálculo do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (Character.getNumericValue(CPF.charAt(i)) * (10 - i));
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        // Cálculo do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (Character.getNumericValue(CPF.charAt(i)) * (11 - i));
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        // Verifica se os dígitos calculados correspondem aos do CPF
        return (primeiroDigito == Character.getNumericValue(CPF.charAt(9)) && 
                segundoDigito == Character.getNumericValue(CPF.charAt(10)));
    }

    // Método para definir o CPF, com validação
    public void setCPF(String CPF) {
        if (validarCPF(CPF)) {
            this.CPF = CPF;
        } else {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    public int calcularIdade() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(this.dataNasc, formatter);
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNascimento, dataAtual).getYears();
    }
}