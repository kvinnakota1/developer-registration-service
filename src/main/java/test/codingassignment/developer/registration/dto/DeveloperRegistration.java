package test.codingassignment.developer.registration.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeveloperRegistration {

    private String id;

    private Date registeredAt;

    private String firstName;

    private String lastName;

    private String emailId;

    private String instagramUserName;

    private String twitterUserName;

    private Long productId;

    private String productName;

    private String developerEnvironment;

    private String location;

    private String industry;


    @Override
    public String toString() {
        return "Id : " + this.id + ", Registration Date: " + this.registeredAt + ", First Name: " + this.firstName + ", Last Name: " + this.lastName +
                ", Email Id: " + this.emailId + ", Instagram User Name: " + this.instagramUserName + ", Twitter User Name: " + this.twitterUserName +
                ", Developer Environment: " + this.developerEnvironment + ", Location : " + this.location + ", Industry: " + this.industry +
                ", Product Name: " + this.productName + ", Product Id: " + this.productId;
    }
}
