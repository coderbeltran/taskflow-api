package pe.edu.trentino.taskflow.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.trentino.taskflow.exception.BadRequestException;

import java.net.URI;
import java.util.*;

@RestControllerAdvice

public class ExceptionHandling {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail  handlingMethodArgumentNotValidException( MethodArgumentNotValidException ex ){
        ProblemDetail problemDetail1=ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail1.setTitle("Entidad no procesab");
        problemDetail1.setType(URI.create("https://api.todotic.pe/errors/unprocessable-entity"));
        problemDetail1.setDetail("La entidad no puede procesarse porque tiene errores.");
        List<String> errors=new ArrayList<>();
        List<FieldError> fielErrors=ex.getFieldErrors();
        for (FieldError fe: fielErrors) {
            String mensaje= messageSource.getMessage(fe, Locale.CANADA);
            errors.add(mensaje);
        }
        problemDetail1.setProperty("errors", errors);
        return problemDetail1;

    }
    @ExceptionHandler(BadRequestException.class)
    ProblemDetail handleBadRequestException(BadRequestException exception){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setTitle("Entidad no procesable");
        problemDetail.setType(URI.create("https://api.todotic.pe/errors/unprocessable-entity"));
        return  problemDetail;

    }

}
