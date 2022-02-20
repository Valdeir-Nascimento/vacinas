
package br.edu.ufra.vacinas.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vacina")
public class Vacina extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date data;

    @JoinColumn(name = "animal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animal;

}
