package co.edu.unicauca.commandrestaurant.access.adapter;

import co.edu.unicauca.commandrestaurant.domain.Food;
import co.edu.unicauca.commandrestaurant.domain.FoodTypeEnum;
import co.edu.unicauca.commandrestaurant.domain.decorator.CryptFood;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevith Felipe Bastidas
 */
public class FoodJsonArrayRepository implements IFoodJsonArrayRepository{
    /**
     * Lista de comidas
     */
    private static List<String> foods;
    
    /**
     * Constructor
     */
    public FoodJsonArrayRepository(){
        if(foods == null){
            foods = new ArrayList<>();
            InicializarPlatos();
        }
    }
    /**
     * inicializamos algunas comidas
     */
    private void InicializarPlatos(){
        foods.add(convertToJson(new CryptFood(1, "Fríjoles", FoodTypeEnum.PRINCIPIO)));
        foods.add(convertToJson(new CryptFood(2, "Sopa de verduras", FoodTypeEnum.ENTRADA)));
        foods.add(convertToJson(new CryptFood(3, "Jugo de maracuyá", FoodTypeEnum.JUGO)));
   }
    /**
     * Buscar una comida
     *
     * @param id identificador
     * @return objeto comida si lo encuentra. En caso contrario retorna nulo
     */
    @Override 
    public String getById(int id){
        int i;
        String result =null;
        for(i=0;i<foods.size();i++){
            String a =foods.get(i);
            if(a.contains("\"id\":"+id+",")){
                result=foods.get(i);
            }  
        }
        return result;
    }

    /**
     * Busca todas las comidas. 
     *
     * @return lista de comidas
     */
    @Override
    public List<String> getAll(){
        return foods;
    }

    /**
     * Agrega una comida
     * @return true si la agrega, false en caso contrario.
     */
    @Override
    public boolean add(Food food){
         if (getById(food.getId()) == null) {
            foods.add(this.convertToJson(food));
            return true;
        }
        return false;
    }

    /**
     * Modifica una comida
     *
     * @return true si lo modifica con exito, false en caso contrario
     */
    @Override
    public boolean modify(Food food){
        
        String auxJson = this.getById(food.getId());
        Food aux = jsonToFood(auxJson);
        if (aux != null) {
            this.remove(aux.getId());
            this.add(food);
            return true;
        }
        return false;
    }

    /**
     * Eliminar una comida
     *
     * @param id identificador de la comida
     */
    @Override
    public void remove(int id){
        int i;
         for(i=0;i<foods.size();i++){
            String a =foods.get(i);
            if(a.contains("\"id\":"+id+",")){
                foods.remove(i);
            }  
        }  
    }

    /**
     * Función que convierte un objeto a un formato json 
     * @param food objeto
     * @return String en formato Json
     */
    private String convertToJson(Food food) {
        Gson gson = new Gson();
        Food aux = new Food(food.getId(), food.getName(), food.getType());
        String json = gson.toJson(aux);
        return json;
    }
    
    /**
     * Función que convierte un string en formato json a un objeto
     * @param jsonFood String en formato json
     * @return un objeto de tipo Food
     */
    private Food jsonToFood(String jsonFood){
        Gson gson = new Gson();
        Food food = gson.fromJson(jsonFood, Food.class);
        return food;
    }
}
