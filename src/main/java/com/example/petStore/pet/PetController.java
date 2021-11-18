package com.example.petStore.pet;


import com.example.petStore.data.PetDataObject;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Path( "/pets" )
@Produces( "application/json" )
@Consumes( "application/json" )
@ApplicationScoped
public class PetController
{

    List<Pet> pets = PetDataObject.getInstance();
    int sizeOfArray = PetDataObject.getInstancePetId();

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            ) )
    } )
    @POST
    public Response addPet( @RequestBody( required = true ) PetRequest pet )
    {
        Pet pet1 = new Pet( pet );
        System.out.println( pet1.getPetAge() );
        pet1.setPetId( sizeOfArray + 1 );
        pets.add( pet1 );
        sizeOfArray++;
        return Response.ok( pet1 ).build();
    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    public Response getPets()
    {
        return Response.ok( pets ).build();
    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "Pet for id", content = @Content( mediaType = MediaType.APPLICATION_JSON ) ),
            @APIResponse( responseCode = "404", description = "Pet Id is not found" ) } )
    @PUT
    @Path( "{id}" )
    public Response updatePet( @PathParam( "id" ) int id, @RequestBody PetRequest petRequest )
    {
        try
        {
            int deleteStatus = 0;
            for( int i = 0; i < pets.size(); i++ )
            {
                if( pets.get( i ).getPetId() == id )
                {
                    Pet pet = pets.get( i );
                    if( petRequest.getPetAge() != pet.getPetAge() )
                    {
                        pet.setPetAge( petRequest.getPetAge() );
                    }
                    if( petRequest.getPetName() != pet.getPetName() )
                    {
                        pet.setPetName( petRequest.getPetName() );
                    }
                    if( petRequest.getPetType() != pet.getPetType() )
                    {
                        pet.setPetType( petRequest.getPetType() );
                    }
                    return Response.ok( pet ).build();
                }

            }

        }
        catch( Exception e )
        {
            return Response.serverError().build();
        }
        return Response.status( 204, "Not Found" ).build();

    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "pet id deleted" ),
            @APIResponse( responseCode = "404", description = "Pet Id is not found" ) } )
    @DELETE
    @Path( "{id}" )
    public Response deletePet( @PathParam( "id" ) int id )
    {

        int deleteStatus = 0;
        for( int i = 0; i < pets.size(); i++ )
        {
            if( pets.get( i ).getPetId() == id )
            {
                pets.remove( i );
                return Response.status( 200 ,"Deleted").build();
            }

        }
        return Response.ok( " {\n" +
                                    "        \"status\": Filed,\n" +
                                    "        \"Massage\": Not found id,\n" +
                                    "    }",MediaType.APPLICATION_JSON  ).build();

    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    @Path( "{id}" )
    public Response getPetsById( @PathParam( "id" ) int id )
    {
        for( int i = 0; i < pets.size(); i++ )
        {
            if( pets.get( i ).getPetId() == id )
            {
                return Response.ok( pets.get( i ) ).build();
            }

        }
        return Response.status( 404, "Not found" ).build();

    }

    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            )
            )
    } )
    @GET
    @Path( "search" )
    public Response getPetsByName(
            @DefaultValue( "false" ) @QueryParam( "name" ) String name,
            @DefaultValue( "false" ) @QueryParam( "type" ) String type,
            @DefaultValue( "-1" ) @QueryParam( "age" ) int age )
    {
        List<Pet> pets1 = new ArrayList<>();
        if( !name.equals( "false" ) && type.equals( "false" ) && age == -1 )
        {
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( name.equalsIgnoreCase( pet.getPetName() ) )
                {
                    pets1.add( pet );
                }

            }
        }
        else if( name.equals( "false" ) && !type.equals( "false" ) && age == -1 )
        {
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( type.equalsIgnoreCase( pet.getPetType() ) )
                {
                    pets1.add( pet );
                }

            }
        }
        else if( name.equals( "false" ) && type.equals( "false" ) && age != -1 )
        {
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( pet.getPetAge() == age )
                {
                    pets1.add( pet );
                }

            }
        }
        else if( !name.equals( "false" ) && !type.equals( "false" ) && age == -1 )
        {
            List<Pet> pets2 = new ArrayList<>();
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( type.equalsIgnoreCase( pet.getPetType() ) )
                {
                    pets2.add( pet );
                }

            }
            for( int i = 0; i < pets2.size(); i++ )
            {
                Pet pet = pets2.get( i );
                if( name.equalsIgnoreCase( pet.getPetName() ) )
                {
                    pets1.add( pet );
                }
            }
        }
        else if( !name.equals( "false" ) && type.equals( "false" ) && age != -1 )
        {
            List<Pet> pets2 = new ArrayList<>();
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( age == pet.getPetAge() )
                {
                    pets2.add( pet );
                }

            }
            for( int i = 0; i < pets2.size(); i++ )
            {
                Pet pet = pets2.get( i );
                if( name.equalsIgnoreCase( pet.getPetName() ) )
                {
                    pets1.add( pet );
                }
            }
        }
        else if( name.equals( "false" ) && !type.equals( "false" ) && age != -1 )
        {
            List<Pet> pets2 = new ArrayList<>();
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( age == pet.getPetAge() )
                {
                    pets2.add( pet );
                }

            }
            for( int i = 0; i < pets2.size(); i++ )
            {
                Pet pet = pets2.get( i );
                if( type.equalsIgnoreCase( pet.getPetType() ) )
                {
                    pets1.add( pet );
                }
            }
        }

        else if( !name.equals( "false" ) && !type.equals( "false" ) && age != -1 )
        {
            List<Pet> pets2 = new ArrayList<>();
            for( int i = 0; i < pets.size(); i++ )
            {
                Pet pet = pets.get( i );
                if( age == pet.getPetAge() )
                {
                    pets2.add( pet );
                }

            }
            List<Pet> pets3 = new ArrayList<>();
            for( int i = 0; i < pets2.size(); i++ )
            {
                Pet pet = pets2.get( i );
                if( type.equalsIgnoreCase( pet.getPetType() ) )
                {
                    pets3.add( pet );
                }
            }
            for( int i = 0; i < pets3.size(); i++ )
            {
                Pet pet = pets3.get( i );
                if( pet.getPetAge() ==age  )
                {
                    pets1.add( pet );
                }
            }
        }else {
            pets1.addAll( pets );
        }


        if( pets1.size() > 0 )
        {
            return Response.ok( pets1 ).build();
        }
        return Response.status( 404, "Not found" ).build();

    }


}
