package br.edu.ufra.vacinas.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "animal")
public class Animal extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String dono;
    private String telefone;
    private Character tipo;

    @Temporal(TemporalType.DATE)
    private Date nascimento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    private List<Vacina> vacinaList;

    @JoinColumn(name = "raca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Raca raca;

}
