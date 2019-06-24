package myList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    @Test
    void size_should_return_0_when_list_is_empty() {
        //given
        MyList<String> list = new MyList<String>();

        //when

        //then
        assertEquals(0, list.size());
    }

    @Test
    public void size_should_get_list_size() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        int result = list.size();

        //then
        assertEquals(5, result);
    }

    @Test
    void should_add_object_to_empty_list() {
        //given
        MyList<String> list = new MyList<>();

        //when
        list.add("pierwszy element");

        //then
        assertEquals(1, list.size());
        assertEquals("pierwszy element", list.get(0));
    }

    @Test
    void should_add_element_to_not_empty_list() {
        //given
        MyList<String> list = new MyList<>();

        //when
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //then
        assertEquals(5, list.size());
        assertEquals("czwarty element", list.get(3));
    }

    @Test
    void should_add_element_to_not_empty_list_with_middle_index() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.add(2, "kolejny element dodany do środka listy");

        //then
        assertEquals(6, list.size());
        assertEquals("kolejny element dodany do środka listy", list.get(2));
    }

    @Test
    void should_add_element_to_not_empty_list_with_0_index() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.add(0, "kolejny element dodany na początku listy");

        //then
        assertEquals(6, list.size());
        assertEquals("kolejny element dodany na początku listy", list.get(0));
    }

    @Test
    void should_add_element_to_not_empty_list_with_last_index() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.add(5, "kolejny element");

        //then
        assertEquals(6, list.size());
        assertEquals("kolejny element", list.get(5));
    }

    @Test
    void should_add_element_to_not_empty_list_with_before_last_index() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.add(4, "kolejny element");

        //then
        assertEquals(6, list.size());
        assertEquals("kolejny element", list.get(4));
        assertEquals("piąty element", list.get(5));
    }

    @Test
    void should_remove_element_by_index() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.remove(2);

        //then
        assertEquals(4, list.size());
        assertEquals("czwarty element", list.get(2));
        assertEquals("piąty element", list.get(3));
    }

    @Test
    void should_remove_element_by_index_0() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.remove(0);

        //then
        assertEquals(4, list.size());
        assertEquals("drugi element", list.get(0));
    }

    @Test
    void should_remove_element_by_last_index() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.remove(4);

        //then
        assertEquals(4, list.size());
        assertEquals("czwarty element", list.get(list.size() - 1));
    }

    @Test
    void remove_by_object_should_remove_element_in_middle() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.remove("drugi element");

        //then
        assertEquals(4, list.size());
        assertEquals("trzeci element", list.get(1));
        assertEquals("pierwszy element", list.get(0));
        assertEquals("piąty element", list.get(3));
    }

    @Test
    void remove_by_object_should_remove_couple_elements_in_middle() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("drugi element");
        list.add("piąty element");

        //when
        list.remove("drugi element");

        //then
        assertEquals(4, list.size());
        assertEquals("trzeci element", list.get(1));
    }

    @Test
    void remove_by_object_should_remove_last_object() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.remove("piąty element");

        //then
        assertEquals(4, list.size());
        assertEquals("czwarty element", list.get(list.size()-1));
    }


    @Test
    void remove_by_object_should_remove_first_object() {
        //given
        MyList<String> list = new MyList<>();
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("czwarty element");
        list.add("piąty element");

        //when
        list.remove("pierwszy element");

        //then
        assertEquals(4, list.size());
        assertEquals("drugi element", list.get(0));
    }


    @Test
    void remove_by_object_should_remove_first_middle_and_last_object() {
        //given
        MyList<String> list = new MyList<>();
        list.add("element do usunięcia");
        list.add("pierwszy element");
        list.add("drugi element");
        list.add("trzeci element");
        list.add("element do usunięcia");
        list.add("czwarty element");
        list.add("piąty element");
        list.add("element do usunięcia");

        //when
        list.remove("element do usunięcia");

        //then
        assertEquals(5, list.size());
        assertEquals("pierwszy element", list.get(0));
        assertEquals("piąty element", list.get(4));
    }

    @Test
    public void remove_should_remove_all_elements() {
        //given
        MyList<String> list = new MyList<>();
        list.add("element do usunięcia");
        list.add("element do usunięcia");
        list.add("element do usunięcia");
        list.add("element do usunięcia");

        //when
        list.remove("element do usunięcia");

        //then
        assertEquals(0, list.size());

    }

//    @Test
//    void remove_should_throw_exception_when_index_is_incorrect() {
//        //given
//        MyList<String> list = new MyList<>();
//        list.add("pierwszy element");
//        list.add("drugi element");
//        list.add("trzeci element");
//        list.add("czwarty element");
//        list.add("piąty element");
//
//        //when
//        list.remove(7);
//
//        //then
//        assertThrows(NullPointerException, )
//    }
}