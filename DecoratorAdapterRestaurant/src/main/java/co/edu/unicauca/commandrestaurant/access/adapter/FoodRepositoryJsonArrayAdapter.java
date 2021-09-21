package co.edu.unicauca.commandrestaurant.access.adapter;

import co.edu.unicauca.commandrestaurant.access.IFoodRepository;
import co.edu.unicauca.commandrestaurant.domain.Food;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que adapta este repositorio nuevo con la logica implementada en la interfaz de repositorio
 * 
 * @author Kevith Felipe Bastidas
 */
public class FoodRepositoryJsonArrayAdapter implements IFoodRepository {
    
    FoodJsonArrayRepository adaptedRepository;
    
    /**
     * Constructor
     */
    public FoodRepositoryJsonArrayAdapter(){
        adaptedRepository = new FoodJsonArrayRepository();
    }
    /**
     * Buscar una comida
     *
     * @param id identificador
     * @return objeto comida
     */
    @Override
    public Food findById(int id){
        return jsonToFood(adaptedRepository.getById(id));
    };

    /**
     * Busca todas las comidas
     *
     * @return lista de comidas
     */
    @Override
    public List<Food> findAll(){
        List<String> srt = adaptedRepository.getAll();
        List<Food> list = new ArrayList();
        for(String food: srt){
           list.add(jsonToFood(food));
        }
        return list;
    }

    /**
     *  Agrega una comida
     * @param food comida a agregar
     * @return true si la agrega, false en caso contrario
     */
    @Override
    public boolean create(Food food){
        return adaptedRepository.add(food);
    }

    /**
     * Modifica una comida
     *
     * @param food comida a ser modificada
     * @return true si lo modifica con exito, false en caso contrario
     */
    @Override
    public boolean update(Food food){
        return adaptedRepository.modify(food);
    }

    /**
     * Eliminar una comida
     *
     * @param id identificador de la comida
     */
    @Override
    public void delete(int id){
        adaptedRepository.remove(id);
    }
    
    /**
     * Función que convierte un string en formato json a un objeto
     * @param jsonFood String en formato json
     * @return un objeto de tipo Food
     */
    private Food jsonToFood(String jsonFood) {
        Gson gson = new Gson();
        Food food = gson.fromJson(jsonFood, Food.class);
        return food;
    }
}
