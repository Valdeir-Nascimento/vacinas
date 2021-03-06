package br.edu.ufra.vacinas.api.controller.exceptionhandler;

import br.edu.ufra.vacinas.api.exception.EntidadeNaoEncontradaException;
import br.edu.ufra.vacinas.api.exception.NegocioException;
import br.edu.ufra.vacinas.api.util.MessagesUtil;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();
        StandardError standardError = createStandardErrorBuilder(status, errorType, detail).build();
        return handleExceptionInternal(ex, standardError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.ERRO_NEGOCIO;
        String detail = ex.getMessage();
        StandardError standardError = createStandardErrorBuilder(status, errorType, detail).build();
        return handleExceptionInternal(ex, standardError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.DADOS_INVALIDOS;
        String detail = MessagesUtil.MSG_DADOS_INVALIDOS;
        List<Field> fieldList = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }
                    return Field.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                }).collect(Collectors.toList());

        StandardError standardError = createStandardErrorBuilder(status, errorType, detail)
                .userMessage(detail)
                .fields(fieldList)
                .build();

        return handleExceptionInternal(ex, standardError, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.PARAMETRO_INVALIDO;
        String detail = String.format("O par??metro de URL '%s' recebeu o valor '%s', que ?? do tipo inv??lido. " +
                "Corrija e informe um valor compat??vel com o tipo '%s'.", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        StandardError standardError = createStandardErrorBuilder(status, errorType, detail).build();
        return handleExceptionInternal(ex, standardError, headers, status, request);

    }
    private StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, ErrorType errorType, String errorMessage) {
        return StandardError.builder()
                .statusCode(status.value())
                .title(errorType.getUri())
                .errorMessage(errorMessage)
                .timestamp(LocalDateTime.now())
                .userMessage(errorMessage);

    }

}
