
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

/**
 * <b>Time complexity:</b> O(nlog(n))
 *
 * <p><b>Space complexity:</b> O(n)
 *
 * <p><b>Notes:</b>
 * <ul>
 *   <li></li>
 * </ul>
 */
class Solution {

    record Car(int position, double timeToTarget){}
    
    public int carFleet(int target, int[] position, int[] speed) {

        // store cars with each timeToTarget
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            cars.add(new Car(position[i], (double)(target - position[i]) / speed[i]));
        }    
        // sort the list of cars in the order that cars closest to the target come first
        cars.sort(Comparator.comparingInt(Car::position).reversed());
        
        // use stack to keep track of car fleets
        Deque<Car> carFleets = new ArrayDeque<>();
        for (Car car : cars) {
            Car top = carFleets.peek();
            // if car is the first to be checked or it can not catch the fleet in front of it then it belongs to different fleet
            if (top == null || top.timeToTarget < car.timeToTarget) {
                carFleets.offerFirst(car);
            }
        }

        return carFleets.size();
    }
}
