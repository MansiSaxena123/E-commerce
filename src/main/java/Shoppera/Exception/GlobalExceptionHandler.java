package Shoppera.Exception;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNameNotFoundException(UsernameNotFoundException ex){
        ApiError apiError = new ApiError("UserName Not Found with UserName: "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getStatus());

    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleUserNameNotFoundException(AuthenticationException ex){
        ApiError apiError = new ApiError("Authentication Failed :  "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatus());

    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleUserNameNotFoundException(JwtException ex){
        ApiError apiError = new ApiError("Invalid JWT Token :  "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatus());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUserNameNotFoundException(Exception ex){
        ApiError apiError = new ApiError("An Unexcepted error occurred :  "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError,apiError.getStatus());

    }
}
