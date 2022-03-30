package br.edu.ufra.vacinas.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "raca")
public class Raca extends BaseEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;

    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raca")
    private List<Animal> animalList;

}
