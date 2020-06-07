import com.model.Employee;
import com.model.Ministry;
import com.model.Organization;
import com.model.Unit;
import com.structures.CircularList;
import com.structures.ListIterator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Employee emp1 = new Employee.Builder()
//                .name("Vasya")
//                .surname("Pupkin")
//                .build();
//        Employee emp2 = new Employee.Builder()
//                .name("Zhenya")
//                .surname("Mihaylov")
//                .build();
//
//        Unit taxiUnit = new Unit.Builder()
//                .name("Taxi")
//                .employees(Arrays.asList(emp1, emp2))
//                .build();
//
//        Organization infrostructureOrganization = new Organization.Builder()
//                .name("Infrostructure")
//                .units(Arrays.asList(taxiUnit))
//                .build();
//
//        Ministry economyMinistry = new Ministry.Builder()
//                .name("Ministry of economy")
//                .organizations(Arrays.asList(infrostructureOrganization))
//                .build();
//
//        System.out.println(economyMinistry.toJson());

        CircularList<Integer> list = new CircularList<>();
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);

        ListIterator iterator = list.iterator();
        for(int i=0;i<4;i++) {
            System.out.println(iterator.get());
            iterator.next();
        }

        System.out.println("del");
        System.out.println(iterator.get());

        iterator.remove();

        System.out.println(iterator.get());

    }
}
