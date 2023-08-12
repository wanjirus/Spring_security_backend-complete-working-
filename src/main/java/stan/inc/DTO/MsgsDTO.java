package stan.inc.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MsgsDTO {
    private long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String message;

    @NotBlank
    @Size(min = 3, max = 20)
    private String sender;
}
