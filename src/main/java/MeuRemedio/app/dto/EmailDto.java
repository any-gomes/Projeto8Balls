package MeuRemedio.app.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Email;

public class EmailDto {

//    @NotBlank
    private String ownerRef;
 //   @NotBlank
 ///   @Email
    private String emailFrom;
  //  @NotBlank
   // @Email
    private String emailTo;
  //  @NotBlank
    private String subject;
  //  @NotBlank
    private String text;

}
