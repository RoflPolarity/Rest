package report.pflb.ProjectReport.Controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String MiddleName;
    private String LastName;
    private String phoneNumber;
    private String e_mail;
    private String password;
}
