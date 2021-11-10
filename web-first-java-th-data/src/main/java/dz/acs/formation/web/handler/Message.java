package dz.acs.formation.web.handler;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * for web socket
 * @author ataibi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable{
	
	private static final long serialVersionUID = -8346988136693133971L;
	
	private String value;
}
