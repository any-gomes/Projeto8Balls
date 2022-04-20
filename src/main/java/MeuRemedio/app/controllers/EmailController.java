package MeuRemedio.app.controllers;


import MeuRemedio.app.dto.EmailDto;
import MeuRemedio.app.model.EmailModel;
import MeuRemedio.app.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmailController {
@Autowired
    EmailService emailService;

@RequestMapping(value = "/sending-email", method = RequestMethod.POST)
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody EmailDto emaildto){
    EmailModel emailModel = new EmailModel();
    BeanUtils.copyProperties(emaildto, emailModel);
    emailService.sendEmail(emailModel);

    return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
}

}
