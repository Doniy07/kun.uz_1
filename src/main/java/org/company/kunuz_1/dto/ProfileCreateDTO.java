package org.company.kunuz_1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.company.kunuz_1.enums.ProfileRole;
import org.company.kunuz_1.enums.ProfileStatus;

@Getter
@Setter
public class ProfileCreateDTO {
    @NotBlank(message = "Name required")
    private String name;
    @NotBlank(message = "Surname required")
    private String surname;
    @NotBlank(message = "Email required")
    private String email;
    @NotBlank(message = "Phone required")
    private String phone;
    @NotBlank(message = "Password required")
    private String password;
    @Enumerated
    private ProfileStatus status;
    @Enumerated
    private ProfileRole role;
}
