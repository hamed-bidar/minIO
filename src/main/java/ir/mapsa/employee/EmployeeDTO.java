package ir.mapsa.employee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class EmployeeDTO {
    @ApiModelProperty(required = true, hidden = false)
    private String firstName;
    @ApiModelProperty(required = true, hidden = false)
    private String lastName;
    @ApiModelProperty(required = true, hidden = false)
    private long nationalCode;
    @ApiModelProperty(required = true, hidden = false)
    private boolean single;
    @ApiModelProperty(required = true, hidden = false)
    private int children;
    @ApiModelProperty(required = true, hidden = false)
    private Date birthday;
}
