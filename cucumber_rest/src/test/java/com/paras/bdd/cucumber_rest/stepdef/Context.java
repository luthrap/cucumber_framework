package com.paras.bdd.cucumber_rest.stepdef;

import com.paras.bdd.cucumber_rest.model.Pet;
import io.restassured.response.Response;

public class Context {
        private static Context myself = new Context();
        private Context(){ }
        private Response response = null;
        private String baseUrl = "";
        private Pet pet;

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        public void setResponse(Response response){
            this.response = response;
        }
        public Response getResponse(){
            return response;
        }
        public void setBaseUrl(String baseUrl){
            this.baseUrl = baseUrl;
        }
        public String getBaseUrl(){
            return baseUrl;
        }

        public static Context getInstance(){
            if(myself == null){
                myself = new Context();
            }

            return myself;
        }

        public void clearTestData(){
            myself = new Context();
        }




}
