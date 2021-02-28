import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;


    //REFACTORING ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup(){
        restaurant= new Restaurant("foodhub","thane",LocalTime.parse("10:30:00"),LocalTime.parse("23:50:00"));
        restaurant.addToMenu("Sweet corn soup",120);
        restaurant.addToMenu("Vegetable lasagne", 250);

    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime currentTime= LocalTime.parse("22:50:00") ;
        Assertions.assertEquals(restaurant.isRestaurantOpen(),currentTime.isAfter(restaurant.openingTime)&& currentTime.isBefore(restaurant.closingTime));


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime currentTime= LocalTime.parse("06:00:00") ;
        Assertions.assertNotEquals(restaurant.isRestaurantOpen(),currentTime.isBefore(restaurant.openingTime)&& currentTime.isAfter(restaurant.closingTime));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//<<<<<<<<<<<<<<<<<<<<<<<PRICE>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void selecting_items_from_a_particular_restaurant_should_return_total_order_value() {
        List<String> myOrders = new ArrayList<>();
        myOrders.add("Sweet corn soup");
        myOrders.add("Vegetable lasagne");
        Assertions.assertEquals(370,restaurant.getTotalOrderValue(myOrders));
    }
    @Test
    public void selecting_non_existent_items_from_a_particular_restaurant_should_throw_NullPointerException_error()  {
        List<String> myOrders = new ArrayList<>();
        myOrders.add("juice");
        myOrders.add("milkshake");

        assertThrows(NullPointerException.class,
                ()->restaurant.getTotalOrderValue(myOrders));
    }


}