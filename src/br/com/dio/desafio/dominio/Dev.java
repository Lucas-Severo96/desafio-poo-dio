package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
    //atributos referentes a geracao randomica de CPF
    private static Set<String> cpfsGerados = new HashSet<>();
    private static Random random = new Random();

    public String gerarCPF() {
        String cpf;

        do {
            cpf = gerarNumerosBase();
            cpf += calcularDigitoVerificador(cpf);
        } while (cpfsGerados.contains(cpf));

        cpfsGerados.add(cpf);
        return formatarCPF(cpf);
    }

    public String gerarNumerosBase() {
        StringBuilder base = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            base.append(random.nextInt(10));
        }
        return base.toString();
    }

    public String calcularDigitoVerificador(String base) {
        int primeiroDigito = calcularDigito(base, 10);
        int segundoDigito = calcularDigito(base + primeiroDigito, 11);
        return "" + primeiroDigito + segundoDigito;
    }

    public int calcularDigito(String str, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += (str.charAt(i) - '0') * (pesoInicial - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public String formatarCPF(String cpf) {
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;

        /*return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();*/
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }

}
