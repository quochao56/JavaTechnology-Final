package tdtu.edu.vn.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tdtu.edu.vn.finalproject.Model.Singer;
import tdtu.edu.vn.finalproject.Repository.SingerRepository;
import tdtu.edu.vn.finalproject.Service.SingerService;
import tdtu.edu.vn.finalproject.Service.SingerServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class WebsiteHaoApplicationTests {

    @Mock
    SingerService singerService;

    @Mock
    SingerRepository singerRepository;

    @InjectMocks
    SingerServiceImpl singerServiceImpl;


    @Test
    void whenGetAll_shouldReturnList() {
        List<Singer> singers = new ArrayList<Singer>();

        for (int i = 0; i < 10; i++) {
            singers.add(new Singer(String.valueOf(i),
                    "Singer " + i,
                    "Brand " + i));
        }
        when(singerRepository.findAll()).thenReturn(singers);

//        when(singerRepository.findAll()).thenReturn(singers);
        when(singerService.getAllSingers()).thenReturn(singers);

        List<Singer> actualProducts = singerService.getAllSingers();

        // 4. assert the result
//        assertThat(actualProducts.size()).isEqualTo(singers.size());

        // 4.1 ensure repository is called
        verify(singerRepository).findAll();
//        assert actualProducts.size() == singers.size();
//        verify(singerRepository).findAll();
    }

    @BeforeEach
    public void setUp() {
        List<Singer> products = new ArrayList<>();
        products.add(new Singer("1L", "Product 1", "10.0"));
        products.add(new Singer("2L", "Product 2", "20.0"));
        products.add(new Singer("3L", "Product 3", "30.0"));
        when(singerRepository.findAll()).thenReturn(products);
    }

    @Test
    public void testGetAllProducts() {
        setUp();
        List<Singer> products = singerService.getAllSingers();
//        assertEquals("3L", products.size());
        assertEquals("Product 1", products.get(0).getSingerName());
//        assertEquals(10.0, products.get(0).getPrice());
//        assertEquals("Product 2", products.get(1).getName());
//        assertEquals(20.0, products.get(1).getPrice());
//        assertEquals("Product 3", products.get(2).getName());
//        assertEquals(30.0, products.get(2).getPrice());
    }
}
