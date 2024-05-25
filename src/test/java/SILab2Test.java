import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    private List<Item> createList(Item... elements){
        return new ArrayList<>(Arrays.asList(elements));
    }

    @Test
    void everyBranchTest(){
        /*za sekoi od testovite od everyBranch kriteriumot imame napisano po 1 test
        * Prvite 3 testa se za isklucocite soodvetno. Prvo gledame dali ima frleno isklucok (assertThrows)
        * A, potoa odreduvame dali e frlen soodvetniot isklucok (preku ispituvanje na porakata)
        * Testovite 4 i 5 se za ostanatiot del od kodot koi ne vleguva vo isklucocite
        * Dokolku Test 4 e tocen programata vraka tocno (Proveruvame so assertTrue)
        * Dokolku Test 5 e tocen programata vraka false (Proveruvame so assertFalse)
        */
        RuntimeException ex;
        //Test 1
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 600));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

          /* Discount e float broj pomegu 0-100*/
        //Test 2
        Item secondItem = new Item(null, null, 45, 54.0f);

        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.checkCart(createList(secondItem), 600));

        assertTrue(ex.getMessage().contains("No barcode!"));


        //Test 3
        Item thirdItem = new Item("thirdItem", "1a9b8", 43,43.0f);

        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(createList(thirdItem), 76));

        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));


        //Test 4
        Item fourthItem = new Item("fourthItem", "018654", 400, 5.0f);
        boolean result = SILab2.checkCart(createList(fourthItem), 5000);
        assertTrue(result);

        //Test 5
        Item fifthItem = new Item("fifthItem", "8435455", 100, -5.0f);
        result = SILab2.checkCart(createList(fifthItem), 50);
        assertFalse(result);

    }



    @Test
    void multipleConditionTest(){

        //F X X (prvoto da e netocno a, drugite 2 da se bilo sto)       --->	FALSE
        Item itemOne = new Item("itemOne", "4243322", 200, 5.0f);
        assertTrue(SILab2.checkCart(createList(itemOne), 1000));
        //sumata treba da e 1000, neka i payment e 1000, da vidime dali ke vrati tocno programata


        // T F X (prvoto tocno, vtoroto netocno a, tretoto bilo sto)    --->	FALSE
        Item itemTwo = new Item("itemTwo", "4657677", 500, -5.0f);
        //sumate treba da e 500, neka payment  e 499. Programata treba da vrati netocno
        assertFalse(SILab2.checkCart(createList(itemTwo), 499));


        //T T F (prvite 2 tocni a poslednoto netocno)          ---->     	FALSE
        Item itemThree = new Item("itemThree", "4657778", 500, 5.0f);
        //sumata e 1000, neka payment e 5000 da vidime dali ke vrati tocno
        assertTrue(SILab2.checkCart(createList(itemThree), 5000));


        //T T T (site tocni)                   ------>               	TRUE
        Item itemFour = new Item("itemFour", "0877789", 700, 5.0f);
        //sumata e 3470, dokolku stavime paymentot da ni e 1469 treba da vrati netocno
        assertFalse(SILab2.checkCart(createList(itemFour), 1469));
    }
}
