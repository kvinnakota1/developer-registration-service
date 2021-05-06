package test.codingassignment.developer.registration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.codingassignment.developer.registration.dto.DeveloperRegistration;
import test.codingassignment.developer.registration.dto.ResponseGroupbyLocationAndIndustry;
import test.codingassignment.developer.registration.service.DeveloperRegistrationService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/developerregistrations")
public class DeveloperRegistrationController {

    @Autowired
    private DeveloperRegistrationService developerRegistrationService;

    /*
        This method saves developer registration
        POST /developerregistrations
        returns DeveloperRegistration dto back in JSON format with status cod as 201
    */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperRegistration saveRegistration(@RequestBody DeveloperRegistration developerRegistration) throws RuntimeException{

        log.info("Inside Registration Controller - Creating Registration");
        return developerRegistrationService.saveDeveloperRegistration(developerRegistration);


    }

    /*
        This method gets all registrations in system
        GET /developerregistrations
        return all registrations as List of DeveloperRegistration dto objects with status code 200
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<DeveloperRegistration> getAllRegistrations() {
        log.info("Inside Registration Controller - Get all Registrations");
        return developerRegistrationService.getAllRegistrations();
    }


    /*
        This method gets registration by id
        GET /developerregistrations/{id}
        returns DeveloperRegistration dto object with status code 200
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DeveloperRegistration getRegistrationById(@PathVariable String id) {

        log.info("Inside Registration Controller - Get Registration by Id");
        return developerRegistrationService.getRegistrationById(id);
    }

    /*
        This method deletes registration by id
        GET /developerregistrations/{id}
        returns no content with status code as 204
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegistrationById(@PathVariable String id) {

        developerRegistrationService.deleteRegistrationById(id);

    }

    /*
        This methods updates the registration
        PUT /developerregistrations
        returns DeveloperRegistration dto object with updated content with status code as 201
     */

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperRegistration updateRegistration(@RequestBody DeveloperRegistration developerRegistration) {
        return developerRegistrationService.updateRegistration(developerRegistration);
    }

    /*
        This method gets all the registration group by location and industry of the developer
        GET /developerregistrations/groupbyIndustryAndLocation
        Returns list of ResponseGroupbyLocationAndIndustry dto objects with status code 200
     */
    @GetMapping(path = "/groupbyIndustryAndLocation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseGroupbyLocationAndIndustry> getRegistrationsGroupByLocationAndIndstry() {

        return developerRegistrationService.getRegistrationsGroupByLocationAndIndustry();
    }

}
