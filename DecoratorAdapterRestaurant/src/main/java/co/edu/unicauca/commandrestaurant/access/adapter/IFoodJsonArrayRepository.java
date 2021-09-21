package co.edu.unicauca.commandrestaurant.access.adapter;

import co.edu.unicauca.commandrestaurant.domain.Food;
import java.util.List;

/**
 *
 * @author Kevith Felipe Bastidas
 */
public interface IFoodJsonArrayRepository {
    /**
    * Obtener una comida por identificador
    * @param id identificador
    * @return  food
    */
    public String getById(int id);
    /**
     * Obtener todas las comidas
     * @return 
     */
    public List<String> getAll();
    /**
     * Agregar una comida
     * @param food
     * @return 
     */
    public boolean add(Food food);
    /**
     * Modificar una comida
     * @param food
     * @return 
     */
    public boolean modify(Food food);

    /**
     * Elimina una comida
     * @param id 
     */
    public void remove(int id);
}
