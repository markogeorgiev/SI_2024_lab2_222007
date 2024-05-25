# SI_2024_lab2_222007

## Марко Георгиев 222007

### Група на код: 

###  Control Flow Graph

![SI_Lab2_CFG_Image_v4](https://github.com/markogeorgiev/SI_2024_lab2_222007/assets/132363580/ae9fddda-1165-41f1-be12-5b906565bd77)

### Цикломатска комплексност

Цикломатската комплексност е 9. Начинот на кој го добив тоа е со броење на регионите во CFG. (Регионите се означени со Rn, т.ш. n = n-тиот по ред регион.

### Тест случаи според критериумот  Every Branch 
#### Test Case 1: allItems is null

```
@Test(expected = RuntimeException.class)
public void testAllItemsNull() {
    SILab2.checkCart(null, 100);
}
```

#### Test Case 2: item.getName() == null and item.getName().length() == 0

```
@Test
public void testItemNameNullOrEmpty() {
    // Test for null
    List<Item> items = Arrays.asList(new Item(null, "123456", 100, 0));
    boolean result = SILab2.checkCart(items, 100);
    assertTrue(result);
    assertEquals("unknown", items.get(0).getName());
    // Test for lenght == 0
    items = Arrays.asList(new Item("", "123456", 100, 0));
    result = SILab2.checkCart(items, 100);
    assertTrue(result);
    assertEquals("unknown", items.get(0).getName());
}
```

#### Test Case 3: item.getBarcode() != null and valid barcode

```
@Test
public void testValidBarcode() {
    List<Item> items = Arrays.asList(new Item("item1", "123456", 100, 0));
    boolean result = SILab2.checkCart(items, 100);
    assertTrue(result);
}
```

#### Test Case 4: Invalid barcode character

```
@Test(expected = RuntimeException.class)
public void testInvalidBarcodeCharacter() {
    List<Item> items = Arrays.asList(new Item("item1", "12345a", 100, 0));
    SILab2.checkCart(items, 100);
}
```

#### Test Case 5: item.getDiscount() > 0

```
@Test
public void testItemWithDiscount() {
    List<Item> items = Arrays.asList(new Item("item1", "123456", 100, 0.1f));
    boolean result = SILab2.checkCart(items, 100);
    assertTrue(result);
}
```

#### Test Case 6: item.getPrice() > 300 and item.getDiscount() > 0 and barcode starts with '0'

```
@Test
public void testAdditionalDiscount() {
    List<Item> items = Arrays.asList(new Item("item1", "0123456", 400, 0.1f));
    boolean result = SILab2.checkCart(items, 100);
    assertTrue(result);
}
```

#### Test Case 7: sum <= payment

```
@Test
public void testSumLessThanOrEqualToPayment() {
    List<Item> items = Arrays.asList(new Item("item1", "123456", 50, 0), new Item("item2", "789012", 30, 0));
    boolean result = SILab2.checkCart(items, 100);
    assertTrue(result);
}
```

#### Test Case 8: sum > payment

```
@Test
public void testSumGreaterThanPayment() {
    List<Item> items = Arrays.asList(new Item("item1", "123456", 100, 0), new Item("item2", "789012", 50, 0));
    boolean result = SILab2.checkCart(items, 100);
    assertFalse(result);
}
```

### Тест случаи според критериумот Multiple Condition за условот if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

```
// Тест случај 1: Сите услови се исполнети
    public void testAllConditionsMet() {
        List<Item> items = Arrays.asList(new Item("item1", "012345", 400, 0.1f));
        boolean result = SILab2.checkCart(items, 500);
        assert result == true;
    }

    // Тест случај 2: Цена > 300 и попуст > 0, но прв знак на баркодот не е '0'
    public void testFirstCharNotZero() {
        List<Item> items = Arrays.asList(new Item("item1", "112345", 400, 0.1f));
        boolean result = SILab2.checkCart(items, 500);
        assert result == false;
    }

    // Тест случај 3: Цена > 300, но попустот е 0, и првиот знак на баркодот е '0'
    public void testNoDiscount() {
        List<Item> items = Arrays.asList(new Item("item1", "012345", 400, 0));
        boolean result = SILab2.checkCart(items, 500);
        assert result == false;
    }

    // Тест случај 4: Сите услови се исполнети, но цената е под 300
    public void testPriceBelowThreshold() {
        List<Item> items = Arrays.asList(new Item("item1", "012345", 200, 0.1f));
        boolean result = SILab2.checkCart(items, 300);
        assert result == false;
    }

    // Тест случај 5: Сите услови се исполнети, но нема попуст
    public void testNoDiscountApplied() {
        List<Item> items = Arrays.asList(new Item("item1", "012345", 400, 0));
        boolean result = SILab2.checkCart(items, 500);
        assert result == false;
    }

    // Тест случај 6: Нема попуст и првиот знак на баркодот не е '0', цената не е битна
    public void testNoDiscountAndFirstCharNotZero() {
        List<Item> items = Arrays.asList(new Item("item1", "112345", 400, 0));
        boolean result = SILab2.checkCart(items, 500);
        assert result == false;
    }

    // Тест случај 7: Нема попуст и првиот знак на баркодот е '0', цената не е битна
    public void testNoDiscountAndFirstCharZero() {
        List<Item> items = Arrays.asList(new Item("item1", "012345", 400, 0));
        boolean result = SILab2.checkCart(items, 500);
        assert result == false;
    }
```

### Објаснување на напишаните unit tests

#### Тест за проверка на кошничка со валидна уплата:
Овој тест проверува дали методата checkCart враќа true кога вкупната цена на предметите во кошничката е помала или еднаква на дадената уплата. Во овој тест се додаваат неколку предмети во листата items, секој со свое име, баркод, цена и попуст. Потоа се повикува методата checkCart со листата на предмети и уплатата од 400. Овој тест се очекува да биде успешен бидејќи вкупната цена на предметите во кошничката е 450 (100 со попуст од 10%, 200 со попуст од 5%, и 150 без попуст), што е помало од уплатата од 400.

#### Тест за проверка на кошничка со невалидна уплата:
Овој тест проверува дали методата checkCart враќа false кога вкупната цена на предметите во кошничката е поголема од дадената уплата. Исто како и претходниот тест, се додаваат предмети во items, потоа се повикува методата checkCart со листата на предмети и уплатата од 250. Овој пат тестот се очекува да се фрли како неуспешен, бидејќи вкупната цена на предметите во кошничката (450) е поголема од дадената уплата (250).

