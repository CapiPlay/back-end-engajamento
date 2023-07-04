package br.senai.sc.engajamento.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.UUID;
import java.io.File;
import lombok.Data;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TUsuario")
public class Usuario {
    @Id
    private UUID uuid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private File foto_perfil;
}
