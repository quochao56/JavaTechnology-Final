package tdtu.edu.vn.finalproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseObject {
    private String status;
    private String message;
    private Object data;


}
