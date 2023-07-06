package br.senai.sc.engajamento.usuario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private File foto;
}
