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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "animal")
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "dono")
    private String dono;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "telefone")
    private String telefone;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private Character tipo;
    
    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    private List<Vacina> vacinaList;
    @JoinColumn(name = "raca", referencedColumnName = "id")
    
    @ManyToOne(optional = false)
    private Raca raca;


    
}
