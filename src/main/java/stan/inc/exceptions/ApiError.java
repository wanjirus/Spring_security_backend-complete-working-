package stan.inc.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private int status;
    @JsonIgnore
    private HttpStatus httpStatus;
//    private Boolean success;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.status=httpStatus.value();
    }

    public ApiError(HttpStatus httpStatus, String message) {
        this(httpStatus);
        this.message = message;
        this.status=httpStatus.value();
    }

    public ApiError(HttpStatus httpStatus, String message, List<String> errors) {
        this(httpStatus, message);
        this.errors = errors;
        this.status=httpStatus.value();
    }

    public ApiError(HttpStatus httpStatus, String message, String error) {
        this(httpStatus, message);
        this.status=httpStatus.value();
        errors = Collections.singletonList(error);
    }
}
