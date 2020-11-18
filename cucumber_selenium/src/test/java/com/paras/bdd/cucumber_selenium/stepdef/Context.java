package com.paras.bdd.cucumber_selenium.stepdef;

public class Context {
        private static Context myself = new Context();
        private Context(){ }

    public int getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(int expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    private int expectedPrice;


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
