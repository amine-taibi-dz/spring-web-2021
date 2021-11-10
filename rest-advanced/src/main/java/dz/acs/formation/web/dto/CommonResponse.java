package dz.acs.formation.web.dto;
/**
 * Common Response for any web rest call
 * @author ataibi
 *
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;
/**
 * 
 * @author ataibi
 *
 */
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse implements Serializable {

	private static final long serialVersionUID = -5158811984174174709L;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	private int statusCode;
	private HttpStatus status;
	// For error case
	private String reason;
	private String location;
	private String message;
	private String detailedMessage;
	// For error case
	private Map<?,?> data;
	
	

}
