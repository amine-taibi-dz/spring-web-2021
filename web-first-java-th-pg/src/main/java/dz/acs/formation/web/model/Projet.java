package dz.acs.formation.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@XmlRootElement
@Table(name = "t_projets")
public class Projet implements Serializable{

	private static final long serialVersionUID = 6921840068288700708L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotNull
	@Length(max = 30,min=3)
	@Column(unique = true)
	private String name;
	
	private String repoName;
	
	@URL	
	private String url;
	
	private boolean visible;
    // conctactEmail
	// conctactPhone
	
}
