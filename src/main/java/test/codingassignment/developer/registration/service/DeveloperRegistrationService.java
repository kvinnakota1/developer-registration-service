package test.codingassignment.developer.registration.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.codingassignment.developer.registration.dto.DeveloperRegistration;
import test.codingassignment.developer.registration.dto.ResponseGroupbyLocationAndIndustry;
import test.codingassignment.developer.registration.entity.DeveloperRegistrationEntity;
import test.codingassignment.developer.registration.helper.ProductsEnum;
import test.codingassignment.developer.registration.repository.DeveloperRegistrationRepository;

import java.util.*;

@Slf4j
@Service
public class DeveloperRegistrationService {

    @Autowired
    private DeveloperRegistrationRepository developerRegistrationRepository;

    /*
        This method is to save developer registration
    */
    public DeveloperRegistration saveDeveloperRegistration(DeveloperRegistration developerRegistration) throws RuntimeException {

        log.info("Inside Registration Service - Save Develop[er Registration");

        //Validating input
        validateRegistrationInput(developerRegistration);

        //building entity object from input
        DeveloperRegistrationEntity developerRegistrationEntity = buildDeveloperRegistrationEntity(developerRegistration);

        developerRegistrationEntity.setId(UUID.randomUUID().toString());

        return buildDeveloperRegistrationFromEntity(developerRegistrationRepository.save(developerRegistrationEntity));
    }


    /*
        This method gets all the registrations from the system
     */
    public List<DeveloperRegistration> getAllRegistrations() {

        log.info("Inside Registration Service - Get all Registration");

        List<DeveloperRegistration> developerRegistrations = new ArrayList<>();

        List<DeveloperRegistrationEntity> developerRegistrationEntities = developerRegistrationRepository.findAll();

        //converts from entity to dto
        for(DeveloperRegistrationEntity developerRegistrationEntity : developerRegistrationEntities) {
            developerRegistrations.add(buildDeveloperRegistrationFromEntity(developerRegistrationEntity));
        }

        return developerRegistrations;
    }


    /*
        This method gets the registration by id
     */
    public DeveloperRegistration getRegistrationById(String id) {

        log.info("Inside Registration Service - get registration by id: " + id );

        return buildDeveloperRegistrationFromEntity(developerRegistrationRepository.findById(id).orElse(null));

    }

    /*
        This method deletes the registration
     */
    public void deleteRegistrationById(String id) {
        developerRegistrationRepository.deleteById(id);
    }


    /*
    This method updates the registration
     */
    public DeveloperRegistration updateRegistration(DeveloperRegistration developerRegistration) throws RuntimeException {
        log.info("Inside Registration Service - Updating registration");

        //validating input
        validateRegistrationInput(developerRegistration);

        Optional<DeveloperRegistrationEntity> developerRegistrationEntity = developerRegistrationRepository.findById(developerRegistration.getId());
        if(developerRegistrationEntity.isPresent()) {

            DeveloperRegistrationEntity developerUpdatedRegistrationEntity = buildDeveloperRegistrationEntity(developerRegistration);

            developerUpdatedRegistrationEntity.setId(developerRegistration.getId());

            developerRegistrationRepository.deleteById(developerRegistration.getId());

            return buildDeveloperRegistrationFromEntity(developerRegistrationRepository.save(developerUpdatedRegistrationEntity));
        }

        return null;
    }


    public List<ResponseGroupbyLocationAndIndustry> getRegistrationsGroupByLocationAndIndustry() {

        log.info("Inside DeveloperRegistrationService - getRegistrationsGroupByLocationAndIndustry");
        List<DeveloperRegistrationEntity> developerRegistrationEntities = developerRegistrationRepository.findAll();

        //Keep Map between Industry$$$Location and  registrations belonging to that Industry and location
        Map<String, List<DeveloperRegistration>> grpIndustryAndLocationMap = new HashMap<>();

        String keyIndustryLocation;

        //This will create the map between Industry$$$Location and  registrations
        for(DeveloperRegistrationEntity developerRegistrationEntity : developerRegistrationEntities) {

            DeveloperRegistration developerRegistration = buildDeveloperRegistrationFromEntity(developerRegistrationEntity);

            keyIndustryLocation = developerRegistrationEntity.getIndustry() + "$$$" + developerRegistrationEntity.getLocation();

            log.info("Key: " + keyIndustryLocation);

            if(grpIndustryAndLocationMap.containsKey(keyIndustryLocation)) {

                grpIndustryAndLocationMap.get(keyIndustryLocation).add(developerRegistration);

            } else {
                List<DeveloperRegistration> devRegistrations = new ArrayList<>();
                devRegistrations.add(developerRegistration);
                grpIndustryAndLocationMap.put(keyIndustryLocation, devRegistrations);
            }

        }

        Set<String> keys = grpIndustryAndLocationMap.keySet();

        List<ResponseGroupbyLocationAndIndustry> responseGroupbyLocationAndIndustryList = new ArrayList<>();

        //This block creates new dto object which shows in group by format
        for(String indAndLoc: keys) {

            log.info("indAndLoc: " +  indAndLoc);
            ResponseGroupbyLocationAndIndustry responseGroupbyLocationAndIndustry = new ResponseGroupbyLocationAndIndustry();
            responseGroupbyLocationAndIndustry.setIndustry(indAndLoc.split("\\$\\$\\$")[0]);
            responseGroupbyLocationAndIndustry.setLocation(indAndLoc.split("\\$\\$\\$")[1]);
            responseGroupbyLocationAndIndustry.setDeveloperRegistrations(grpIndustryAndLocationMap.get(indAndLoc));
            responseGroupbyLocationAndIndustryList.add(responseGroupbyLocationAndIndustry);

        }

        return responseGroupbyLocationAndIndustryList;


    }

    /*
        This is small private utility method which converts from dto object to entity object
     */

    private DeveloperRegistration buildDeveloperRegistrationFromEntity(DeveloperRegistrationEntity developerRegistrationEntity){

        log.info(" Inside Registration service - buildDeveloperRegistrationFromEntity");

        DeveloperRegistration developerRegistration = new DeveloperRegistration();

        if(developerRegistrationEntity != null) {
            developerRegistration.setId(developerRegistrationEntity.getId());
            developerRegistration.setFirstName(developerRegistrationEntity.getFirstName());
            developerRegistration.setLastName(developerRegistrationEntity.getLastName());
            developerRegistration.setEmailId(developerRegistrationEntity.getEmailId());
            developerRegistration.setRegisteredAt(developerRegistrationEntity.getRegisteredAt());
            developerRegistration.setInstagramUserName(developerRegistrationEntity.getInstagramUserName());
            developerRegistration.setTwitterUserName(developerRegistrationEntity.getTwitterUserName());
            developerRegistration.setProductId(developerRegistrationEntity.getProductId());
            developerRegistration.setProductName(ProductsEnum.getById(developerRegistrationEntity.getProductId()).getName());
            developerRegistration.setDeveloperEnvironment(developerRegistrationEntity.getDeveloperEnvironment());
            developerRegistration.setLocation(developerRegistrationEntity.getLocation());
            developerRegistration.setIndustry(developerRegistrationEntity.getIndustry());
        }

        return developerRegistration;


    }

    /*
        This is small private utility method which builds entity objects from dto object
     */
    private DeveloperRegistrationEntity buildDeveloperRegistrationEntity(DeveloperRegistration developerRegistration) {

        log.info(" Inside Registration service - buildDeveloperRegistrationEntity");

        DeveloperRegistrationEntity developerRegistrationEntity = new DeveloperRegistrationEntity();

        developerRegistrationEntity.setRegisteredAt(developerRegistration.getRegisteredAt());
        developerRegistrationEntity.setFirstName(developerRegistration.getFirstName());
        developerRegistrationEntity.setLastName(developerRegistration.getLastName());
        developerRegistrationEntity.setEmailId(developerRegistration.getEmailId());
        developerRegistrationEntity.setRegisteredAt(developerRegistration.getRegisteredAt());
        developerRegistrationEntity.setInstagramUserName(developerRegistration.getInstagramUserName());
        developerRegistrationEntity.setTwitterUserName(developerRegistration.getTwitterUserName());
        developerRegistrationEntity.setProductId(developerRegistration.getProductId());
        developerRegistrationEntity.setDeveloperEnvironment(developerRegistration.getDeveloperEnvironment());
        developerRegistrationEntity.setLocation(developerRegistration.getLocation());
        developerRegistrationEntity.setIndustry(developerRegistration.getIndustry());

        return developerRegistrationEntity;

    }

    /*
        This is small private utility method which validates the input
    */

    private void validateRegistrationInput(DeveloperRegistration developerRegistration) {

        log.info(" Inside Registration service - validateRegistrationInput");

        if((developerRegistration.getFirstName() == null) || (developerRegistration.getFirstName().trim().length() == 0)) {
            throw new RuntimeException("Developer's First name cannot be null");
        }

        if((developerRegistration.getLastName() == null) || (developerRegistration.getLastName().trim().length() == 0)) {
            throw new RuntimeException("Developer's Last name cannot be null");
        }

        if((developerRegistration.getEmailId() == null) || (developerRegistration.getEmailId().trim().length() == 0)) {
            throw new RuntimeException("Developer's Last name cannot be null");
        }

        if((developerRegistration.getLocation() == null) || (developerRegistration.getLocation().trim().length() == 0)) {
            throw new RuntimeException("Developer's Location cannot be null");
        }

        if((developerRegistration.getIndustry() == null) || (developerRegistration.getIndustry().trim().length() == 0)) {
            throw new RuntimeException("Developer's Industry cannot be null");
        }

        if((developerRegistration.getDeveloperEnvironment() == null) || (developerRegistration.getDeveloperEnvironment().trim().length() == 0)) {
            throw new RuntimeException("Developer's Developement environment cannot be null");
        }


    }

}
