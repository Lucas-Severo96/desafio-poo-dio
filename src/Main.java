import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.Mentoria;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Curso curso1 = new Curso();
        curso1.setTitulo("curso java");
        curso1.setDescricao("descrição curso java");
        curso1.setCargaHoraria(8);

        Curso curso2 = new Curso();
        curso2.setTitulo("curso js");
        curso2.setDescricao("descrição curso js");
        curso2.setCargaHoraria(4);

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("mentoria de java");
        mentoria.setDescricao("descrição mentoria java");
        mentoria.setData(LocalDate.now());

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");
        bootcamp.getConteudos().add(curso1);
        bootcamp.getConteudos().add(curso2);
        bootcamp.getConteudos().add(mentoria);

        Dev dev1 = new Dev();
        System.out.println("Informe o nome do 1º desenvolvedor");
        dev1.setNome(input.next());
        System.out.println("CPF do aluno " + dev1.getNome() + " é: " + dev1.gerarCPF());
        dev1.inscreverBootcamp(bootcamp);
        System.out.println("Conteúdos Inscritos de " + dev1.getNome() + ": " + dev1.getConteudosInscritos());
        dev1.progredir();
        dev1.progredir();
        System.out.println("-");
        System.out.println("Conteúdos Inscritos de " + dev1.getNome() + ": " + dev1.getConteudosInscritos());
        System.out.println("Conteúdos Concluídos de " + dev1.getNome() + ": " + dev1.getConteudosConcluidos());
        System.out.println("XP:" + dev1.calcularTotalXp());

        System.out.println("-----------------------------------");

        Dev dev2 = new Dev();
        System.out.println("Informe o nome do 2º desenvolvedor");
        dev2.setNome(input.next());
        dev2.inscreverBootcamp(bootcamp);
        System.out.println("Conteúdos Inscritos " + dev2.getNome() + " é: " + dev2.getConteudosInscritos());
        System.out.println("CPF do aluno " + dev2.getNome() + " é: " + dev2.gerarCPF());
        dev2.progredir();
        dev2.progredir();
        dev2.progredir();
        System.out.println("-");
        System.out.println("Conteúdos Inscritos " + dev2.getNome() + " é: " + dev2.getConteudosInscritos());
        System.out.println("Conteúdos Concluidos " + dev2.getNome() + " é: " + dev2.getConteudosConcluidos());
        System.out.println("XP:" + dev2.calcularTotalXp());

        input.close();

    }

}
