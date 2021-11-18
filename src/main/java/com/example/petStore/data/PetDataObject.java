package com.example.petStore.data;

import com.example.petStore.PetType.PetType;
import com.example.petStore.pet.Pet;

import java.util.ArrayList;
import java.util.List;


public class PetDataObject
{
    public static List<Pet> pets = new ArrayList<>(){{
        add( new Pet(1,"dog","boola",5) );
        add( new Pet(2,"cat","boola",2) );
        add( new Pet(3,"bird","peththa",2) );
    }};

    public static List<PetType> petTypes = new ArrayList<>(){{
        add(new PetType(1,"dog")  );
        add(new PetType(2,"cat")  );
        add(new PetType(3,"bird")  );
    }};

    public static int petId = pets.size();
    public static int petTypeId = petTypes.size();

    private PetDataObject(){
    }

    public static List<Pet> getInstance(){
        System.out.println(pets);
        return pets;
    }
    public static List<PetType>getPetTypesInstance(){
        return petTypes;
    }

    public static int getInstancePetId(){
        return petId;
    }

    public static int getInstanceTypeId(){
        return petTypeId;
    }


}
