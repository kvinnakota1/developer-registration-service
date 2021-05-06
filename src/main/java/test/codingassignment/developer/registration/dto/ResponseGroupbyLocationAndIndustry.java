package test.codingassignment.developer.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGroupbyLocationAndIndustry {

    private String industry;
    private String location;
    private List<DeveloperRegistration> developerRegistrations;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Industry: " + this.industry + " location: " + this.location);

        sb.append(" [");

        for(DeveloperRegistration developerRegistration: this.developerRegistrations) {
            sb.append("{ First Name: ") ;
            sb.append(developerRegistration.getFirstName()) ;
            sb.append(" ");
            sb.append(developerRegistration.getLastName());
            sb.append(", ");
            sb.append(developerRegistration.getEmailId());
            sb.append(" } ");
        }

        sb.append(" ]");

        return sb.toString();
    }
}
