package com.example.petStore;

import com.example.petStore.data.PetDataObject;
import com.example.petStore.pet.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static java.util.concurrent.CompletableFuture.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PetStoreRestApplicationTest
{

    @Test
    public void testPetGetEndPoint()
    {
        given().when().get( "/pets" ).then().statusCode( 200 );
    }

    @Test
    void testPetGetEndpointSuccessWithValues()
    {
        given()
                .when().get("/pets")
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petId",notNullValue() )
                .body( "petAge",equalTo( new ArrayList(){{add( 5 );add( 2 );add( 2 );}} ) )
                .body( "petName",equalTo( new ArrayList(){{add( "boola" );add( "boola" );add( "peththa" );}} ) )
                .body( "petType",equalTo( new ArrayList(){{add( "dog" );add( "cat" );add( "bird" );}} ) );

    }
    @Test
    void testPetGetEndpointUnSuccessWithValues()
    {
        given()
                .when().get("/pets")
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petId",notNullValue() )
                .body( "petAge",equalTo( new ArrayList(){{add( 5 );add( 2 );add( 2 );}} ) )
                .body( "petName",equalTo( new ArrayList(){{add( "boola" );add( "boola" );add( "peththa" );}} ) )
                .body( "petType",equalTo( new ArrayList(){{add( "dog" );add( "cat2" );add( "bird" );}} ) ); //change the value of cat type

    }

    @Test
    public void testedAPetSuccess(){
        given()
                .header( "Content-Type",  "application/json" )
                .body( "{\n" +
                               "\t\"petType\":\"Dog\",\n" +
                               "\t\"petName\":\"Rox\",\n" +
                               "\t\"petAge\":15\n" +
                               "}" )
                .when().post("/pets")
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petId",notNullValue() )
                .body( "petAge",equalTo( 15 ) )
                .body( "petName",equalTo( "Rox" ) )
                .body( "petType",equalTo( "Dog" ) );

    }
    @Test
    void testUpdatePet(){
        given()
                .header( "Content-Type",  "application/json" )
                .pathParam( "id",1 )
                .body( "{\n" +
                               "\t\"petType\":\"Dog\",\n" +
                               "\t\"petName\":\"Rox\",\n" +
                               "\t\"petAge\":15\n" +
                               "}" )
                .when().put("/pets/{id}")
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petId",notNullValue() )
                .body( "petAge",equalTo( 15 ) )
                .body( "petName",equalTo( "Rox" ) )
                .body( "petType",equalTo( "Dog" ) );

    }
    @Test
    void testUpdatePetNotValidId(){
        given()
                .header( "Content-Type",  "application/json" )
                .pathParam( "id",4 )
                .body( "{\n" +
                               "\t\"petType\":\"Dog\",\n" +
                               "\t\"petName\":\"Rox\",\n" +
                               "\t\"petAge\":15\n" +
                               "}" )
                .when().put("/pets/{id}")
                .then()
                .assertThat()
                .statusCode( 204 );

    }

    @Test
    void testDeletePet(){
        given()
                .header( "Content-Type",  "application/json" )
                .pathParam( "id",1 )
                .when().delete("/pets/{id}")
                .then()
                .assertThat()
                .statusCode( 200 );

    }


}