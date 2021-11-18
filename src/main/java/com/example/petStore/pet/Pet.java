package com.example.petStore.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.security.IdentityAttribute;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.annotation.Generated;

@Schema(name = "Pet")
public class Pet
{
    @Schema(required = true, description = "Pet id")
    @JsonProperty("pet_id")
    private Integer petId;

    @Schema(required = true, description = "Pet type")
    @JsonProperty("pet_type")
    private String petType;

    @Schema(required = true, description = "Pet name")
    @JsonProperty("pet_name")
    private String petName;

    @JsonProperty("pet_age")
    private Integer petAge;

    public Pet()
    { }

    public Pet( Integer petId, String petType, String petName, Integer petAge )
    {
        this.petId = petId;
        this.petType = petType;
        this.petName = petName;
        this.petAge = petAge;
    }

    public Pet( PetRequest petRequest )
    {
        this.petType = petRequest.getPetType();
        this.petName = petRequest.getPetName();
        this.petAge = petRequest.getPetAge();
    }

    public Integer getPetId()
    {
        return petId;
    }

    public void setPetId( Integer petId )
    {
        this.petId = petId;
    }

    public String getPetType()
    {
        return petType;
    }

    public void setPetType( String petType )
    {
        this.petType = petType;
    }

    public String getPetName()
    {
        return petName;
    }

    public void setPetName( String petName )
    {
        this.petName = petName;
    }

    public Integer getPetAge()
    {
        return petAge;
    }

    public void setPetAge( Integer petAge )
    {
        this.petAge = petAge;
    }
}