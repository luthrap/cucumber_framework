package com.paras.bdd.cucumber_rest.stepdef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paras.bdd.cucumber_rest.model.Pet;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.paras.bdd.cucumber_rest.config.Constants.*;
import static com.paras.bdd.cucumber_rest.utils.Initialization.*;


public class Actions{

    Context context = Context.getInstance();

    @Given("^the application is running$")
    public void checkApplication()
    {
        String baseUrl = getProperty("base_url");
        context.setBaseUrl(baseUrl);
        int responseCode = get(baseUrl).statusCode();
        Assert.assertEquals(200, responseCode);
    }

    @When("^I look for the pets with status as (.+)$")
    public void queryPetWithStatus(String status){
        Response response = get(context.getBaseUrl() + PET_FIND_BY_STATUS + "?status=" + status).thenReturn();
        context.setResponse(response);
    }

    @Then("^I should be able to find all the Pets with following attributes$")
    public void findPet(DataTable table) throws Exception{
        List<Map<String,String>> expectedResult = table.asMaps();
        Response response = context.getResponse();
        List<Pet> actualResult = response.getBody().jsonPath().getList("",Pet.class);
        for(Map<String,String> expectedRow : expectedResult){
            Pet thisPet = new Pet();
            for(Map.Entry<String,String> rowEntrySet: expectedRow.entrySet()){
                Method getterMethod = Pet.class.getDeclaredMethod("get" + rowEntrySet.getKey());
                Class<?> setterParam = getterMethod.getReturnType();
                Method method = Pet.class.getDeclaredMethod("set" + rowEntrySet.getKey(), setterParam);
                method.invoke(thisPet, rowEntrySet.getValue());
            }
            boolean found = false;
            for(Pet eachActualResult : actualResult){
                if(eachActualResult.getInfo().equals(thisPet.getInfo())){
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("Verify that the Pet with attributes:" + thisPet.getInfo() + " is present", found);
        }
    }

    @When("^I Post a new Pet from the (.+) file$")
    public void createPet(String fileName) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Pet pet = objectMapper.readValue(new File(fileName), Pet.class);
        Response response = given().contentType(ContentType.JSON).body(pet).post(context.getBaseUrl() + POST_PET_INFO).thenReturn();
        Assert.assertEquals(200, response.getStatusCode());
        context.setResponse(response);
        context.setPet(pet);
    }

    @Then("^I should be able to find the Pet with the PetId (.+)$")
    public void getPetByPetId(long petId){
        Response response = get(context.getBaseUrl() + POST_PET_INFO + "/" + String.valueOf(petId)).thenReturn();
        Pet actualResult = response.getBody().as(Pet.class);
        Assert.assertEquals(actualResult.toString(),context.getPet().toString());
    }

    @When("^I update the pet from the (.+) file with the (.+) as (.+)$")
    public void putUpdatedInfo(String fileName, String entity, String value) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Pet pet = objectMapper.readValue(new File(fileName), Pet.class);
        Method getterMethod = Pet.class.getDeclaredMethod("get" + entity);
        Class<?> setterParam = getterMethod.getReturnType();
        Method method = Pet.class.getDeclaredMethod("set" + entity, setterParam);
        method.invoke(pet, value);
        Response response = given().contentType(ContentType.JSON).body(pet).put(context.getBaseUrl() + POST_PET_INFO).thenReturn();
        context.setResponse(response);
    }

    @Then("^I verify that the Pet with PetId (.+) has the (.+) as (.+)$")
    public void assertPetInfo(String petId,String entity, String expectedValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Response response = get(context.getBaseUrl() + POST_PET_INFO + "/" + String.valueOf(petId)).thenReturn();
        Pet actualResult = response.getBody().as(Pet.class);
        Method method = Pet.class.getDeclaredMethod("get" + entity);
        String actualValue = (String)method.invoke(actualResult);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @When("^I delete the Pet with PetId (.+)$")
    public void deletePet(String petId){
        Response response = delete(context.getBaseUrl() + POST_PET_INFO + "/" + String.valueOf(petId)).thenReturn();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("^I verify that the pet with PetId (.+) does not exist$")
    public void assertDeletedPet(String petId){
        Response response = get(context.getBaseUrl() + POST_PET_INFO + "/" + String.valueOf(petId)).thenReturn();
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("^I clear the data$")
    public void tearDown(){
        context.setResponse(null);
        context.setPet(null);
    }


}
