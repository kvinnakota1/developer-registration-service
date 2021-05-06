package test.codingassignment.developer.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEVELOPER_REGISTRATION")
public class DeveloperRegistrationEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "REGISTERED_AT", nullable = false)
    private Date registeredAt;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL_ID", unique = true, nullable = false)
    private String emailId;

    @Column(name = "INSTAGRAM_USER_NAME")
    private String instagramUserName;

    @Column(name = "TWITTER_USER_NAME")
    private String twitterUserName;

    @Column(name = "PRODUCT_ID", nullable = false, length = 4)
    private Long productId;

    @Column(name = "DEVELOPER_ENVIRONMENT", nullable = false)
    private String developerEnvironment;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "INDUSTRY", nullable = false)
    private String industry;



}
