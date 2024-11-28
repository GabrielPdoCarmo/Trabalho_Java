package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;

import java.util.Date;

public class AlunoDto {

    private Integer id;

    @JsonProperty("nome")
    @NotBlank(message = "campo nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "campo email não pode ser nulo")
    @Email(message = "o campo precisa ser preenchido com um email")
    private String email;

    @JsonProperty("matricula")
    @NotBlank(message = "campo matricula não pode ser nula")
    private String matricula;

    @JsonProperty("data_nascimento")
    @NotNull(message = "campo data_nascimento não pode ser nulo")
    @PastOrPresent(message = "data_nascimento não pode ser uma data futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataNascimento;

    public AlunoDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
