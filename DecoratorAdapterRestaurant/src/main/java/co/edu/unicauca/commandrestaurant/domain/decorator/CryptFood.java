package co.edu.unicauca.commandrestaurant.domain.decorator;

import co.edu.unicauca.commandrestaurant.domain.Food;
import co.edu.unicauca.commandrestaurant.domain.FoodTypeEnum;
import co.unicauca.commandRestaurant.infra.Utilities;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que decora y extiende de Food, la cual encripta su nombre
 * 
 * @author Kevith Felipe Bastidas
 */
public class CryptFood extends Food{
    
    private Food myFoodCrypt;
    /**
     * Constructor parametrizado. Se usan los métodos 
     * @param id identificador 
     * @param name nombre
     * @param type tipo
     */
    public CryptFood(int id, String name, FoodTypeEnum type){
        try {
            String nombreEncriptado=Utilities.encriptar(name);
            myFoodCrypt=new Food(id, nombreEncriptado, type);
        } catch (Exception ex) {
            Logger.getLogger(CryptFood.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Getters and Setters
    */
    
    @Override
    public void setId(int id) {
        myFoodCrypt.setId(id);
    }

    @Override
    public int getId() {
        return myFoodCrypt.getId();
    }
    
    
    @Override
    public void setName(String name) {
        myFoodCrypt.setName(name);
    }

    @Override
    public String getName() {
        return myFoodCrypt.getName();
    }

    @Override
    public void setType(FoodTypeEnum type) {
        myFoodCrypt.setType(type);
    }

    @Override
    public FoodTypeEnum getType() {
        return myFoodCrypt.getType();
    }
    
    @Override
    public String toString() {
        return "Food{" + "id=" + myFoodCrypt.getId() + ", name=" + myFoodCrypt.getName() + ", type=" + myFoodCrypt.getType() + '}';
    }
    
}