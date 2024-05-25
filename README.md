/*
*
*	Драгана Трифунова 223044
*
*/



public static boolean checkCart(List<Item> allItems, int payment){
        if (allItems == null){ //1
            throw new RuntimeException("allItems list can't be null!"); //2
        }

        float sum = 0; //3

        for (int i = 0; i < allItems.size(); i++){  //4
            Item item = allItems.get(i);  //5
            if (item.getName() == null || item.getName().length() == 0){   //6
                item.setName("unknown");  //7
            }
            if (item.getBarcode() != null){   //8
                String allowed = "0123456789";  //9
                char chars[] = item.getBarcode().toCharArray();  //10
                for (int j = 0; j < item.getBarcode().length(); j++){  //11
                    char c = item.getBarcode().charAt(j);  //12
                    if (allowed.indexOf(c) == -1){   //13
                        throw new RuntimeException("Invalid character in item barcode!");  //14
                    }
                } //15
                if (item.getDiscount() > 0){   //15
                    sum += item.getPrice()*item.getDiscount();  //16
                }
                else {
                    sum += item.getPrice(); //17
                }
            }
            else {
                throw new RuntimeException("No barcode!");  //18
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){  //19
                sum -= 30;  //20
            }
        } //21
        if (sum <= payment){  //21
            return true;  //22
        }
        else {
            return false;  //23
        }
    } //24

//////////////////////////////////////////////////////////
Ova e kodot oznacen so reden broj na sekoj red, za nego podolu e daden Control Flow Grafot. 
![softversko_datagram](https://github.com/draganatrifunova/SI_2024_lab2_223044/assets/138613966/2165d271-89fe-4113-a8e9-4933a03a8bc1)

///////////////////////////////////////////////////////
Ciklomatskata kompleksnost e:

ciklomatska_kompleksnost = broj na rebra - broj na jazli + 2
ciklomatska_kompleksnost = 35 - 28 + 2 = 9 
Ciklomatskata kompleksnost e 9
/////////////////////////////////////////////////////////


Every Branch
Podolu se dadeni site rebra koi se vo Every Branch grafot

1-2  Test 1
1-3   Test2   Test 3   Test 4   Test 5
3-4.1  Test 2   Test 3   Test 4   Test 5
4.1-4.2   Test 2   Test 3    Test 4   Test 5
4.2-5    Test 2    Test 3   Test 4    Tets 5
4.2-21   Test 4   Test 5
2-24  Test 1
5-6   Test 2    Test 3  Test 4  Test 5
6-8   Test 3  Test 4   Test 5
6-7   Test 2
7-8   Test 2
8-18   Test 2
8-9    Test 3    Test 4   Test 5
9-10   Test 3    Test 4    Test 5
18-24   Test 2
10-11.1  Test 3   Test 4   Test 5
11.1-11.2   Test 3   Test 4   Test 5 
11.2-12   Test 3  Test 4   Test 5
12-13   Test 3   Test 4  Test 5
13-14   Test 3
13-15  Test 3   Test 4   Test 5
15-11.3   Test 3  Test 4   Test 5
11.3-11.2   Test 3   Test 4  Test 5
11.2-15    Test 4   Test 5
15-16    Test  4
15-17    Test 5
17-19    Test 5
16-19   Test 4
19-20   Test 4
19-21   Test 5
20-21   Test 4
21-4.3   Test 4  Test 5
21-23    Test 5
21-22    Test 4
23-24    Test 5
22-24    Test 4
4.3-4.2    Test 4   Test 5
14-24   Test 3



Ova se testovite za metodot od klasata. Ovoj metot mora da oma najmalku 5 testovi spored kriteriumot za Every Branch za da pomine niz sekoj Branch.
Objasnuvanje: Imame 3 isklucoci posle koj programata terminira. (Za sekoj od niv mora da napiseme poseben test primer Test 1, Test 2, Test 3)

Test 1 se spravuva koga listata e null.
Test 2 se spravuva koga nemame zadadeno ime na Item (no ovde NE frla isklucok pa prodolzuvame ponatamu) i koga barkodot na Itemot e null (Frla isklucok)
Test 3 frla isklucok koga imame nedozvoleni karakteri vo barkodot (Barkotot mora da se sostoi samo od cifri)

Posle ovie 3 isklucoci imame struktura if-else koja e napravena na takov nacin sto prvo mora da gi izbegneme site isklucoci (da ne vlezeme vo niv) za da moze 
da vlezeme vo if-else statement-ot:
if (sum <= payment){  //21
   return true;  //22
}
else {
   return false;  //23
}

Sega za da vlezeme vo if-ot ni treba Test 4 a, za da vlezeme vo else-ot ni treba Test 5.

So ova go pokrivme minimalniot broj na testovi vo Every Branch Metodot.
================================
Test 1
allItems = null
payment = anything

Test 2
Item 1
Name = null
barcode = null
ostanatite vrednosti mozat da bidat bilo koi

Test 3
name = thirdItem
barcode = 1a9b8
ostanatite vrednosti mozat da bidat bilo koi

Test 4
name = fourthItem
barcode = 018654
getDiscount = 5
getPrice = 400
sum <= payment
sum =1970
payment = 5000

Test5
name = FifthItem
barcode = 8435455
discount = -5
sum > payment
sum = 100
payment = 50
getPrice = 100
================================

////////////////////////////////////////////////////
Multiple Condition

if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

Mozni se 4 test slucai:

F X X (prvoto da e netocno a, drugite 2 da se bilo sto)   item.getPrice() = 200, item.getDiscount() = 5,  item.getBarcode().charAt(0) == '4'    --->	FALSE
T F X (prvoto tocno, vtoroto netocno a, tretoto bilo sto)  item.getPrice() = 500, item.getDiscount() = -5,  item.getBarcode().charAt(0) == '4'  --->	FALSE
T T F (prvite 2 tocni a poslednoto netocno)  item.getPrice() = 500, item.getDiscount() = 5,  item.getBarcode().charAt(0) == '4'            ---->     	FALSE
T T T (site tocni)  item.getPrice() = 700, item.getDiscount() = 5,  item.getBarcode().charAt(0) == '0'                           ------>               	TRUE
